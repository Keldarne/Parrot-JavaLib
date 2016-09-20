package parrotdronelib;

import java.net.InetAddress;
import java.net.UnknownHostException;
import static parrotdronelib.commands.CommonCommand.AllSettings;
import static parrotdronelib.commands.SumoCommand.Jump;
import static parrotdronelib.commands.SumoCommand.PCMD;
import static parrotdronelib.commands.SumoCommand.PictureV2;
import static parrotdronelib.commands.SumoCommand.Posture;
import static parrotdronelib.commands.SumoCommand.VideoEnable;
import static parrotdronelib.commands.SumoCommand.VideoV2;

/**
 * Class Sumo used for basic moves that can be done with Jumping Sumo. Extends
 * DeviceController (mother Class).
 *
 * @author Joseph Gremaud <a><mailto:gremaudj@studentfr.ch></a>
 * @version 1.3
 * @date 22.01.2016 à 14:30
 */
public class Sumo extends DeviceController {

    /**
     * Handshaker attribute.
     */
    private Handshaker handShaker;

    /**
     * Sumo constructor.
     *
     * @param ip Sumo's IP.
     * @param portTCP Port device to controller chosen by user.
     */
    public Sumo(String ip, int portTCP) {
        try {
            this.ip = InetAddress.getByName(ip);
            this.portTCP = portTCP;
            this.portDtoC = 2618;
            this.handShaker = new Handshaker(this);
            this.nonACKCounter = 0;
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * This method is used to connect to the sumo correctly.
     */
    public void handshake() {
        if (handShaker != null) {
            handShaker.send();
        }
    }

    /**
     * This method inits the sumo.
     */
    public void initSumoDevice() {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(AllSettings.getCommand(this.nonACKCounter++));
        }
    }

    /**
     * This method makes the sumo roll forward.
     *
     * @param speed range from -100 to 100.
     * @param turn range from -100 to 100.
     */
    public void forward(int speed, int turn) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(PCMD.getCommand(this.nonACKCounter++, 1, speed, turn));
        }
    }

    /**
     * This method makes the sumo roll backward.
     *
     * @param speed range from 100.
     * @param turn range from 100.
     */
    public void backward(int speed, int turn) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(PCMD.getCommand(this.nonACKCounter++, 1, (speed * -1), turn));
        }
    }

    /**
     * This method makes the sumo turn right backward.
     *
     * @param turn range from 100.
     */
    public void turnRight(int turn) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(PCMD.getCommand(this.nonACKCounter++, 1, 0, turn));
        }
    }

    /**
     * This method makes the sumo turn left backward.
     *
     * @param turn range from 100.
     */
    public void turnLeft(int turn) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(PCMD.getCommand(this.nonACKCounter++, 1, 0, (turn * -1)));
        }
    }

    /**
     * This method makes the sumo Jump high or long.
     *
     * @param isHighJump True = highJump, false = longJump.
     */
    public void jump(boolean isHighJump) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(Jump.getCommand(this.nonACKCounter++, (isHighJump) ? 1 : 0));
        }
    }

    /**
     * This method changes the Sumo posture.
     *
     * @param postureNumber 0 = standing, 1 = jumper, 2 = kicker
     */
    public void posture(int postureNumber) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(Posture.getCommand(this.nonACKCounter++, postureNumber));
        }
    }

    /**
     * This method start/stop mediaStreaming.
     *
     * @param setEnabled 1 to start, 0 to stop.
     */
    public void activateCamera(int setEnabled) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(VideoEnable.getCommand(this.nonACKCounter, setEnabled));
        }
    }

    /**
     * This method is used to start/stop recording a video that will be stored
     * on the Sumo memory.
     *
     * @param setRecording 1 to start, 0 to stop.
     */
    public void videoRecording(int setRecording) {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(VideoV2.getCommand(this.nonACKCounter, setRecording));
        }
    }

    /**
     * This method is used to take a picture that will be stored on the Sumo
     * memory.
     */
    public void takeAPicture() {
        if (readyForUDP && this.ip != null && this.portCtoD != 0 && controllerUDP != null && controllerUDP.isAlive()) {
            controllerUDP.sendCommand(PictureV2.getCommand(this.nonACKCounter));
        }
    }

}
