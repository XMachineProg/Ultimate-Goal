package org.firstinspires.ftc.teamcode.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EngineState {
    public int isEnginesBusy(DcMotor leftEngine, DcMotor rightEngine) {
        try {
            if (leftEngine.isBusy() == true && rightEngine.isBusy() == false) {
                return 10;
            } else if (leftEngine.isBusy() == false && rightEngine.isBusy() == true) {
                return 01;
            } else {
                return 11;
            }
        } catch (Exception e) {
            return 500;
        }

    }
}
