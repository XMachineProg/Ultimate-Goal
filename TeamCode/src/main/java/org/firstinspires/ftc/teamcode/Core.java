package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Core {
    protected DcMotor leftMotor; // This variable will be responsible for the left engine.
    protected DcMotor rightMotor; // This variable will be responsible for the right engine.
    protected HardwareMap hwMap; // This variable will be responsible for the hardware map.
    protected NormalizedColorSensor colorSensor;
    private Telemetry tlmtr;

    public void setHardwareMap() {
        leftMotor = (DcMotor)hwMap.get("leftMotor");
        rightMotor = (DcMotor)hwMap.get("rightMotor");
        colorSensor = (NormalizedColorSensor)hwMap.get("colorSensor");
        tlmtr.addData("Hardware Map -> ", "Loaded!");

    }

}
