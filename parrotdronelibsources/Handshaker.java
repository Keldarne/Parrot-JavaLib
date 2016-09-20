package parrotdronelib;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class used to start connection with robot. Checking if we are allowed to work
 * with robot.
 *
 * @author Joseph Gremaud <a><mailto:gremaudj@studentfr.ch></a>
 * @version 1.3
 * @date 22.01.2016 à 14:30
 */
public class Handshaker extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private final DeviceController parentDevice;

    /**
     * Handshaker constructor.
     *
     * @param parentController Device we are going to try connecting to.
     */
    public Handshaker(DeviceController parentController) {
        this.parentDevice = parentController;
        this.setName("handShaker");
        init();

    }

    /**
     * Handshaker initialisation.
     */
    private void init() {
        try {
            socket = new Socket(parentDevice.getIp(), 44444);
            socket.setSoTimeout(10000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            out = new PrintWriter(socket.getOutputStream());
            this.start();
        } catch (IOException ex) {
            System.out.println("Impossible de se connecter à l'adresse spécifiée !");
        }
    }

    /**
     * Thread of class. Reading on TCP for Handshake answer.
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            if (!parentDevice.isReadyForUDP()) {
                read();
            } else {
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println("Interrompu !");
                }
            }

        }
    }

    /**
     * Reading TCP response of connected Device.
     */
    public void read() {
        JsonObject json = null;
        String str;
        try {
            if ((str = in.readLine()) != null) {
                JsonParser jsonParser = new JsonParser();
                json = (JsonObject) jsonParser.parse(str.trim());
                parentDevice.setPortCtoD(Integer.parseInt(json.get("c2d_port").toString()));
                parentDevice.setStreamSize(Integer.parseInt(json.get("arstream_fragment_size").toString()));
                parentDevice.setReadyForUDP(true);
                parentDevice.startUDP(this);
            }
        } catch (IOException ex) {
        }
    }

    /**
     * Sending connection request to device.
     */
    public void send() {
        String str = "{'controller_name':'parrot-java-lib','controller_type':'" + System.getProperty("os.name")
                + "','d2c_port':" + parentDevice.getPortDtoC() + "}";
        if (out != null) {
            out.println(str);
            out.flush();
        } else {
            System.out.println("Veuillez attendre l'initialisation de la connexion au serveur !");
        }
    }

    /**
     * Kill handshaker Thread.
     */
    public void shutdown() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erreur lors de la fermeture de la connexion !");
        } catch (NullPointerException ex) {
        }
    }

    /**
     * Setter of running
     *
     * @param running boolean used to know if thread is running or not.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}
