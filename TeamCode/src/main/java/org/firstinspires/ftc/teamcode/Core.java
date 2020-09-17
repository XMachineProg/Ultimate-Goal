package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Core {
    protected DcMotor leftMotor; // This variable will be responsible for the left engine.
    protected DcMotor rightMotor; // This variable will be responsible for the right engine.
    protected HardwareMap hwMap; // This variable will be responsible for the hardware map.

    public void setHardwareMap() {
        leftMotor = (DcMotor)hwMap.get("leftMotor");
        rightMotor = (DcMotor)hwMap.get("rightMotor");
       
    }

}
