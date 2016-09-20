package parrotdronelib;

import java.awt.image.BufferedImage;
import java.net.InetAddress;
import static parrotdronelib.commands.CommonCommand.Disconnect;

/**
 * Class DeviceController. Used to link physical robot to code.
 *
 * @author Joseph Gremaud <a><mailto:gremaudj@studentfr.ch></a>
 * @version 1.3
 * @date 22.01.2016 à 14:30
 */
public class DeviceController {

    /**
     * Class constructor. Initialisation of some attributes.
     */
    public DeviceController() {

        this.readyForUDP = false;
        this.lastImage = new BufferedImage(640, 360, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * ACK Counter for Command requesting ACK.
     */
    protected int ACKCounter;

    /**
     * nonACK Counter for Command which doesnt request ACK.
     */
    protected int nonACKCounter;
    /**
     * Device IP.
     */
    protected InetAddress ip;

    /**
     * Device battery level.
     */
    protected double batteryLevel;

    /**
     * Last image from device Stream.
     */
    protected BufferedImage lastImage;

    /**
     * Port used for TCP Handshake with the device.
     */
    protected int portTCP;

    /**
     * Port from device to controller.
     */
    protected int portDtoC;

    /**
     * Port from controller to device.
     */
    protected int portCtoD;

    /**
     * StreamSize for UDP received by Handshaker response.
     */
    protected int streamSize;

    /**
     * Boolean to check if device is ready to start UDP communication.
     */
    protected boolean readyForUDP;

    /**
     * UDP Controller of the device.
     */
    protected ControllerUDP controllerUDP;

    public void disconnect() {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(Disconnect.getCommand(this.nonACKCounter++));

            try {
                controllerUDP.setPause(true);
                controllerUDP.setRunning(false);
                controllerUDP.join();
                controllerUDP.killSockets();
            } catch (InterruptedException ex) {
            }

        }
    }

    //Getter and setters from attributes.
    public BufferedImage getLastImage() {
        BufferedImage bi = null;
        synchronized (lastImage) {
            bi = lastImage;
        }
        return bi;
    }

    public void setLastImage(BufferedImage lastImage) {
        synchronized (this.lastImage) {
            this.lastImage = lastImage;
        }
    }

    public void startUDP(Handshaker handshaker) {
        this.controllerUDP = new ControllerUDP(this, handshaker);
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPortDtoC() {
        return portDtoC;
    }

    public void setPortDtoC(int portDtoC) {
        this.portDtoC = portDtoC;
    }

    public int getPortCtoD() {
        return portCtoD;
    }

    public void setPortCtoD(int portCtoD) {
        this.portCtoD = portCtoD;
    }

    public int getStreamSize() {
        return streamSize;
    }

    public void setStreamSize(int streamSize) {
        this.streamSize = streamSize;
    }

    public boolean isReadyForUDP() {
        return readyForUDP;
    }

    public void setReadyForUDP(boolean readyForUDP) {
        this.readyForUDP = readyForUDP;
    }

}
