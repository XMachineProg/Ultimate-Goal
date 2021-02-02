package org.firstinspires.ftc.teamcode.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EngineState {
    public String isEnginesBusy(Boolean leftEngine, Boolean rightEngine) {
        try {
            if (leftEngine == true && rightEngine == false) {
                return "202 " + "Only left engine is busy";
            } else if (leftEngine == false && rightEngine == true) {
                return "202 " + "Only right engine is busy";
            } else {
                return "202 " + "Both engine is busy";
            }
        } catch (Exception e) {
            return "500, Some error has occurred:   " + e.getMessage();
        }

    }
}