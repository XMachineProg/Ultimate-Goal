package org.firstinspires.ftc.teamcode.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EngineState {
    public int isEnginesBusy(Boolean leftEngine, Boolean rightEngine) {
        try {
            if (leftEngine == true && rightEngine == false) {
                return 10;
            } else if (leftEngine == false && rightEngine == true) {
                return 01;
            } else {
                return 11;
            }
        } catch (Exception e) {
            return 500;
        }

    }
}
