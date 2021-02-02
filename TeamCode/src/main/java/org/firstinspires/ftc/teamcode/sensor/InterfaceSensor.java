package org.firstinspires.ftc.teamcode.sensor;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

public interface InterfaceSensor {
    String sensorName(NormalizedColorSensor sensor);
    String getReading(NormalizedColorSensor sensor);
}
