package parrotdronelib.commands;

/**
 * Class SumoCommand is the whole set of commands specific to Jumping Sumo.
 * Containing lambdas only.
 *
 * @author Joseph Gremaud <a><mailto:gremaudj@studentfr.ch></a>
 * @version 1.3
 * @date 22.01.2016 à 14:30
 */
@FunctionalInterface
public interface SumoCommand {

    byte[] getCommand(int counter, int... args);

    //CLASS 0, Piloting
    CommonCommand PCMD = (counter, args) -> new byte[]{2, 11, (byte) counter, 14, 0, 0, 0, 3, 0, 0, 0, (byte) args[0], (byte) args[1], (byte) args[2]};
    CommonCommand Posture = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 0, 1, 0, (byte) args[0], 0, 0, 0};
    CommonCommand addCapOffset = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 0, 2, 0, (byte) args[0]};
    //CLASS 1, PilotingState
    CommonCommand PostureChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 1, 0, 0, (byte) args[0], 0, 0, 0};
    CommonCommand AlertStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 1, 1, 0, (byte) args[0], 0, 0, 0};
    CommonCommand SpeedChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 3, 1, 2, 0, (byte) args[0], (byte) args[1]};
    //CLASS 2, Animations
    CommonCommand JumpStop = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 2, 0, 0};
    CommonCommand JumpCancel = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 2, 1, 0};
    CommonCommand JumpLoad = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 2, 2, 0};
    CommonCommand Jump = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 2, 3, 0, (byte) args[0], 0, 0, 0};
    CommonCommand SimpleAnimation = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 2, 4, 0, (byte) args[0], 0, 0, 0};
    //CLASS 3, AnimationsState
    CommonCommand JumpLoadChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 3, 0, 0, (byte) args[0], 0, 0, 0};
    CommonCommand JumpTypeChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 3, 1, 0, (byte) args[0], 0, 0, 0};
    CommonCommand JumpMotorProblemChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 3, 2, 0, (byte) args[0], 0, 0, 0};
    //CLASS 4, Settings
    //CLASS 5, SettingsState
    CommonCommand ProductGPSVersionChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 3, 5, 0, 0, (byte) args[0], (byte) args[1]};
    //CLASS 6, MediaRecord
    CommonCommand Picture = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 6, 0, 0, (byte) args[0]};
    CommonCommand Video = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 6, 1, 0, (byte) args[0], 0, 0, 0, (byte) args[1]};
    CommonCommand PictureV2 = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 6, 2, 0};
    CommonCommand VideoV2 = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 6, 3, 0, (byte) args[0], 0, 0, 0};
    //CLASS 7, MediaRecordState
    CommonCommand PictureStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 3, 7, 0, 0, (byte) args[0], (byte) args[1]};
    CommonCommand VideoStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 7, 1, 0, (byte) args[0], 0, 0, 0, (byte) args[1]};
    CommonCommand PictureStateChangedV2 = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 7, 2, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0};
    CommonCommand VideoStateChangedV2 = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 7, 3, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0};
    //CLASS 8, NetworkSettings
    CommonCommand WifiSelection = (counter, args) -> new byte[]{2, 11, (byte) counter, 17, 0, 0, 0, 3, 8, 0, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0, (byte) args[2]};
    //CLASS 9, NetworkSettingsState
    CommonCommand WifiSelectionChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 17, 0, 0, 0, 3, 9, 0, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0, (byte) args[2]};
    //CLASS 10, Network
    CommonCommand WifiScan = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 10, 0, 0, (byte) args[0], 0, 0, 0};
    CommonCommand WifiAuthChannel = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 10, 1, 0};
    //CLASS 11, NetworkState
    CommonCommand WifiScanListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 18, 0, 0, 0, 3, 11, 0, 0, (byte) args[0], (byte) args[1], (byte) args[2], 0, 0, 0, (byte) args[3]};
    CommonCommand AllWifiScanChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 11, 1, 0};
    CommonCommand WifiAuthChannelListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 17, 0, 0, 0, 3, 11, 2, 0, (byte) args[0], 0, 0, 0, (byte) args[1], (byte) args[2]};
    CommonCommand AllWifiAuthChannelChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 11, 3, 0};
    CommonCommand LinkQualityChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 11, 4, 0, (byte) args[0]};
    //CLASS 12, AudioSettings
    CommonCommand MasterVolume = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 12, 0, 0, (byte) args[0]};
    CommonCommand Theme = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 12, 1, 0, (byte) args[0], 0, 0, 0};
    //CLASS 13, AudioSettingsState
    CommonCommand MasterVolumeChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 13, 0, 0, (byte) args[0]};
    CommonCommand ThemeChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 13, 1, 0, (byte) args[0], 0, 0, 0};
    //CLASS 14, RoadPlan
    CommonCommand AllScriptsMetadata = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 14, 0, 0};
    CommonCommand ScriptUploaded = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 3, 14, 1, 0, (byte) args[0], (byte) args[1]};
    CommonCommand ScriptDelete = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 14, 2, 0, (byte) args[0]};
    CommonCommand PlayScript = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 14, 3, 0, (byte) args[0]};
    //CLASS 15, RoadPlanState
    CommonCommand ScriptMetadataListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 15, 0, 0, (byte) args[0], (byte) args[1], (byte) args[2], (byte) args[3], (byte) args[4]};
    CommonCommand AllScriptsMetadataChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 3, 15, 1, 0};
    CommonCommand ScriptUploadChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 15, 2, 0, (byte) args[0], 0, 0, 0};
    CommonCommand ScriptDeleteChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 15, 3, 0, (byte) args[0], 0, 0, 0};
    CommonCommand PlayScriptChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 15, 4, 0, (byte) args[0], 0, 0, 0};
    //CLASS 16, SpeedSettings
    CommonCommand Outdoor = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 16, 0, 0, (byte) args[0]};
    //CLASS 17, SpeedSettingsState
    CommonCommand OutdoorChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 17, 0, 0, (byte) args[0]};
    //CLASS 18, MediaStreaming
    CommonCommand VideoEnable = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 18, 0, 0, (byte) args[0]};
    //CLASS 19, MediaStreamingState
    CommonCommand VideoEnableChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 3, 19, 0, 0, (byte) args[0], 0, 0, 0};
    //CLASS 20, MediaRecordEvent
    CommonCommand PictureEventChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 20, 0, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0};
    CommonCommand VideoEventChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 3, 20, 1, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0};
    //CLASS 21, VideoSettings
    CommonCommand Autorecord = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 21, 0, 0, (byte) args[0]};
    //CLASS 22, VideoSettingsState
    CommonCommand AutorecordChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 3, 22, 0, 0, (byte) args[0]};

}
