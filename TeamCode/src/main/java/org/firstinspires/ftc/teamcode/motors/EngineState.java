package org.firstinspires.ftc.teamcode.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EngineState {
    public int isEnginesBusy(DcMotor leftEngine, DcMotor rightEngine) {
        try {
            if (leftEngine.isBusy() == true && rightEngine.isBusy() == false) {
                return 10;
            } else if (leftEngine.isBusy() == false && rightEngine.isBusy() == true) {
                return 01;
            } else if (leftEngine.isBusy() == true && rightEngine.isBusy() == true){
                return 11;
            } else {
                return 00;
            }
        } catch (Exception e) {
            return 500;
        }

    }
}
