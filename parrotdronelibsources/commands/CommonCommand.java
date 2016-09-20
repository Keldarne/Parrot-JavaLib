package parrotdronelib.commands;

/**
 *
 * @author Joseph Gremaud <a><mailto:gremaudj@studentfr.ch></a>
 */
@FunctionalInterface
public interface CommonCommand {

    byte[] getCommand(int counter, int... args);

    //CLASS 0, Network
    CommonCommand Disconnect = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 0, 0, 0};
    //CLASS 1, NetworkEvent
    CommonCommand Disconnection = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 1, 0, 0, (byte) args[0], 0, 0, 0};
    //CLASS 2, Settings
    CommonCommand AllSettings = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 2, 0, 0};
    CommonCommand Reset = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 2, 1, 0};
    CommonCommand ProductName = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 2, 2, 0, (byte) args[0]};
    CommonCommand Country = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 2, 3, 0, (byte) args[0]};
    CommonCommand AutoCountry = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 2, 4, 0, (byte) args[0]};
    //CLASS 3, SettingsState
    CommonCommand AllSettingsChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 3, 0, 0};
    CommonCommand ResetChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 3, 1, 0};
    CommonCommand ProductNameChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 3, 2, 0, (byte) args[0]};
    CommonCommand ProductVersionChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 0, 3, 3, 0, (byte) args[0], (byte) args[1]};
    CommonCommand ProductSerialHighChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 3, 4, 0, (byte) args[0]};
    CommonCommand ProductSerialLowChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 3, 5, 0, (byte) args[0]};
    CommonCommand CountryChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 3, 6, 0, (byte) args[0]};
    CommonCommand AutoCountryChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 3, 7, 0, (byte) args[0]};
    //CLASS 4, Common
    CommonCommand AllStates = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 4, 0, 0};
    CommonCommand CurrentDate = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 4, 1, 0, (byte) args[0]};
    CommonCommand CurrentTime = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 4, 2, 0, (byte) args[0]};
    CommonCommand Reboot = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 4, 3, 0};
    //CLASS 5, CommonState
    CommonCommand AllStatesChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 5, 0, 0};
    CommonCommand BatteryStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 5, 1, 0, (byte) args[0]};
    CommonCommand MassStorageStateListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 0, 5, 2, 0, (byte) args[0], (byte) args[1]};
    CommonCommand MassStorageInfoStateListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 17, 0, 0, 0, 0, 5, 3, 0, (byte) args[0], (byte) args[1], (byte) args[2], (byte) args[3], (byte) args[4], (byte) args[5]};
    CommonCommand CurrentDateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 5, 4, 0, (byte) args[0]};
    CommonCommand CurrentTimeChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 5, 5, 0, (byte) args[0]};
    CommonCommand MassStorageInfoRemainingListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 14, 0, 0, 0, 0, 5, 6, 0, (byte) args[0], (byte) args[1], (byte) args[2]};
    CommonCommand WifiSignalChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 5, 7, 0, (byte) args[0]};
    CommonCommand SensorsStatesListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 0, 5, 8, 0, (byte) args[0], 0, 0, 0, (byte) args[1]};
    CommonCommand ProductModel = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 5, 9, 0, (byte) args[0], 0, 0, 0};
    CommonCommand CountryListKnown = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 0, 5, 10, 0, (byte) args[0], (byte) args[1]};
    //CLASS 6, OverHeat
    CommonCommand SwitchOff = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 6, 0, 0};
    CommonCommand Ventilate = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 6, 1, 0};
    //CLASS 7, OverHeatState
    CommonCommand OverHeatChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 7, 0, 0};
    CommonCommand OverHeatRegulationChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 7, 1, 0, (byte) args[0]};
    //CLASS 8, ControllerState
    CommonCommand isPilotingChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 8, 0, 0, (byte) args[0]};
    //CLASS 9, WifiSettings
    CommonCommand OutdoorSetting = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 9, 0, 0, (byte) args[0]};
    //CLASS 10, WifiSettingsState
    CommonCommand outdoorSettingsChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 10, 0, 0, (byte) args[0]};
    //CLASS 11, Mavlink
    CommonCommand Start = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 0, 11, 0, 0, (byte) args[0], (byte) args[1], 0, 0, 0};
    CommonCommand Pause = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 11, 1, 0};
    CommonCommand Stop = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 11, 2, 0};
    //CLASS 12, MavlinkState
    CommonCommand MavlinkFilePlayingStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 17, 0, 0, 0, 0, 12, 0, 0, (byte) args[0], 0, 0, 0, (byte) args[1], (byte) args[2], 0, 0, 0};
    CommonCommand MavlinkPlayErrorStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 12, 1, 0, (byte) args[0], 0, 0, 0};
    //CLASS 13, Calibration
    CommonCommand MagnetoCalibration = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 13, 0, 0, (byte) args[0]};
    //CLASS 14, CalibrationState
    CommonCommand MagnetoCalibrationStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 14, 0, 0, (byte) args[0], (byte) args[1], (byte) args[2], (byte) args[3]};
    CommonCommand MagnetoCalibrationRequiredState = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 14, 1, 0, (byte) args[0]};
    CommonCommand MagnetoCalibrationAxisToCalibrateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 14, 2, 0, (byte) args[0], 0, 0, 0};
    CommonCommand MagnetoCalibrationStartedChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 14, 3, 0, (byte) args[0]};
    //CLASS 15, CameraSettingsState
    CommonCommand CameraSettingsChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 0, 15, 0, 0, (byte) args[0], (byte) args[1], (byte) args[2], (byte) args[3], (byte) args[4]};
    //CLASS 16, GPS
    CommonCommand ControllerPositionForRun = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 0, 16, 0, 0, (byte) args[0], (byte) args[1]};
    //CLASS 17, FlightPlanState
    CommonCommand AvailabilityStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 17, 0, 0, (byte) args[0]};
    CommonCommand ComponentStateListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 0, 17, 1, 0, (byte) args[0], 0, 0, 0, (byte) args[1]};
    //CLASS 18, ARLibsVersionsState
    CommonCommand ControllerLibARCommandsVersion = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 18, 0, 0, (byte) args[0]};
    CommonCommand SkyControllerLibARCommandsVersion = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 18, 1, 0, (byte) args[0]};
    CommonCommand DeviceLibARCommandsVersion = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 18, 2, 0, (byte) args[0]};
    //CLASS 19, FlightPlanEvent
    CommonCommand StartingErrorEvent = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 19, 0, 0};
    CommonCommand SpeedBridleEvent = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 19, 1, 0};
    //CLASS 20, Audio
    CommonCommand ControllerReadyForStreaming = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 20, 0, 0, (byte) args[0]};
    //CLASS 21, AudioState
    CommonCommand AudioStreamingRunning = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 21, 0, 0, (byte) args[0]};
    //CLASS 22, Headlights
    CommonCommand intensity = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 0, 22, 0, 0, (byte) args[0], (byte) args[1]};
    //CLASS 23, HeadlightsState
    CommonCommand intensityChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 13, 0, 0, 0, 0, 23, 0, 0, (byte) args[0], (byte) args[1]};
    //CLASS 24, Animations
    CommonCommand StartAnimation = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 24, 0, 0, (byte) args[0], 0, 0, 0};
    CommonCommand StopAnimation = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 24, 1, 0, (byte) args[0], 0, 0, 0};
    CommonCommand StopAllAnimations = (counter, args) -> new byte[]{2, 11, (byte) counter, 11, 0, 0, 0, 0, 24, 2, 0};
    //CLASS 25, AnimationsState
    CommonCommand List = (counter, args) -> new byte[]{2, 11, (byte) counter, 17, 0, 0, 0, 0, 25, 0, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0, (byte) args[2], 0, 0, 0};
    //CLASS 26, Accessory
    CommonCommand Config = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 26, 0, 0, (byte) args[0], 0, 0, 0};
    //CLASS 27, AccessoryState
    CommonCommand SupportedAccessoriesListChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 27, 0, 0, (byte) args[0], 0, 0, 0};
    CommonCommand AccessoryConfigChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 0, 27, 1, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0};
    CommonCommand AccessoryConfigModificationEnabled = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 27, 2, 0, (byte) args[0]};
    //CLASS 28, Charger
    CommonCommand SetMaxChargeRate = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 28, 0, 0, (byte) args[0], 0, 0, 0};
    //CLASS 29, ChargerState
    CommonCommand MaxChargeRateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 29, 0, 0, (byte) args[0], 0, 0, 0};
    CommonCommand CurrentChargeStateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 16, 0, 0, 0, 0, 29, 1, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0};
    CommonCommand LastChargeRateChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 15, 0, 0, 0, 0, 29, 2, 0, (byte) args[0], 0, 0, 0};
    CommonCommand ChargingInfo = (counter, args) -> new byte[]{2, 11, (byte) counter, 18, 0, 0, 0, 0, 29, 3, 0, (byte) args[0], 0, 0, 0, (byte) args[1], 0, 0, 0, (byte) args[2], (byte) args[3]};
    //CLASS 30, RunState
    CommonCommand RunIdChanged = (counter, args) -> new byte[]{2, 11, (byte) counter, 12, 0, 0, 0, 0, 30, 0, 0, (byte) args[0]};

}
