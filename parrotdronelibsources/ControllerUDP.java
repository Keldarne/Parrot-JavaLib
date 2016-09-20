package parrotdronelib;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 * Class ControllerUDP. Used to send and receive data from/to robot.
 *
 * @author Joseph Gremaud <a><mailto:gremaudj@studentfr.ch></a>
 * @version 1.3
 * @date 22.01.2016 à 14:30
 */
public class ControllerUDP extends Thread {

    /**
     * Socket used to send data to Robot.
     */
    private DatagramSocket controllerToDeviceSocket;

    /**
     * Socket used to receive data from Robot.
     */
    private DatagramSocket deviceToControllerSocket;

    /**
     * Packet configured to receive data from Robot.
     */
    private DatagramPacket packetReceiver;

    /**
     * Buffer of packetReceiver.
     */
    private byte[] buffer;

    /**
     * Boolean used by receiving thread of this class.
     */
    private boolean running, pause;

    /**
     * Reference to connected robot.
     */
    private DeviceController deviceController;

    /**
     * Counter used to count ping done by Robot and to avoid time-outs.
     */
    private int pingCounter;

    /**
     * ControllerUDP constructor.
     *
     * @param deviceController device to we are going to connect to.
     * @param handshaker handshaker going to be shutdowned.
     */
    public ControllerUDP(DeviceController deviceController, Handshaker handshaker) {
        this.deviceController = deviceController;
        handshaker.setRunning(false);
        handshaker.shutdown();
        this.setName("ControllerUDP");
        init();
    }

    /**
     * Initialisation of UDP controller.
     */
    private void init() {
        try {
            //OUT va vers Sumo
            controllerToDeviceSocket = new DatagramSocket();

            //IN vient du Sumo
            deviceToControllerSocket = new DatagramSocket(deviceController.getPortDtoC());
            controllerToDeviceSocket.setSoTimeout(10000);
            buffer = new byte[this.deviceController.getStreamSize()];
            packetReceiver = new DatagramPacket(buffer, buffer.length);
            this.start();
        } catch (SocketException ex) {
        }
    }

    /**
     * Running method of class thread. This thread is listening for data from
     * Robot.
     */
    @Override
    public void run() {
        running = true;
        pause = false;
        pingCounter = 0;
        while (running) {
            if (!pause) {
                receiveData();
                waitMS(10);
            } else {
                waitMS(10);
            }
        }
    }

    /**
     * Method used to wait a time in millis class thread.
     *
     * @param millis
     */
    private void waitMS(int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * This method sends command through UDP.
     *
     * @param packetAsBytes command byte array.
     */
    public void sendCommand(byte[] packetAsBytes) {
        try {
            DatagramPacket packet = new DatagramPacket(packetAsBytes, packetAsBytes.length, deviceController.getIp(), deviceController.getPortCtoD());
            synchronized (controllerToDeviceSocket) {
                controllerToDeviceSocket.send(packet);
            }
            System.out.println("Command sent to device");
        } catch (SocketException exSocket) {
            System.out.println(exSocket.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is used to listen if we received data.
     */
    public void receiveData() {
        byte[] data = null;
        try {
            deviceToControllerSocket.receive(packetReceiver);
            data = packetReceiver.getData();
            if (!isPing(data)) {
                //On vérifie si c'est une image.
                if (data[0] == (byte) 3) {
                    //On enlève le header de l'image
                    data = Arrays.copyOfRange(data, 12, data.length);
                    //Conversion de byte[] bufferedImage.
                    InputStream in = new ByteArrayInputStream(data);
                    BufferedImage buffImage = ImageIO.read(in);
                    if (buffImage != null) {
                        //On met a jour la dernière image reçu dans le deviceController.
                        deviceController.setLastImage(buffImage);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method verify if data is a ping done by Robot.
     *
     * @param data data used to verify if this is a ping.
     *
     * @return true if ping | false if anything else.
     */
    private boolean isPing(byte[] data) {
        boolean ok = false;
        data = Arrays.copyOfRange(data, 0, 8);
        if (data[0] == 2 && data[1] == 0) {
            if (pingCounter++ % 4 == 0) {
                data[1] = 1;
                data[2] = (byte) deviceController.nonACKCounter++;
                this.sendCommand(data);
                ok = true;
            }
        }
        return ok;
    }

    /**
     * Method used to kill controllerUDP at the end of programm. Notice : not
     * fully tested.
     */
    public void killSockets() {
        this.controllerToDeviceSocket.close();
        this.controllerToDeviceSocket = null;
        this.deviceToControllerSocket.close();
        this.deviceToControllerSocket = null;
        this.packetReceiver = null;
    }

    //Setters and Getters
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

}
