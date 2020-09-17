package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Core {
    private DcMotor leftMotor; // This variable will be responsible for the left engine.
    // Place the variable responsible for the right engine here.
    private HardwareMap hwMap; // This variable will be responsible for the hardware map.

    public void setHardwareMap() {
        leftMotor = (DcMotor)hwMap.get("leftMotor");
        /* Place the hardware map of the right engine here, to create do the following:
        take the engine variable you created and assign its type followed by Hardware Map.get ("here you name the variable").
        This sequence will make the robot's engine connect to the variable you created.*/
    }

}
