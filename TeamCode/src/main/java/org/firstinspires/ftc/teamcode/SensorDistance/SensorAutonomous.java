package org.firstinspires.ftc.teamcode.SensorDistance;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorAutonomous extends RangeSensor implements InterfaceRange{

    // these variables are responsible for connecting the sensor and telemetry of the SensorRange class with the SensorAutonomous
    private Telemetry telemetry = super.tlmtr;
    private DistanceSensor sensor = super.range;

    public void distance(){

        if(sensor.getDistance(DistanceUnit.CM) > 0)
        telemetry.addData("Distance:", sensor.getDistance(DistanceUnit.CM)); // This variable is responsible of calculating distance
        telemetry.update();

    }
    public String sensorName(){
        return sensor.getDeviceName();
    }
}
