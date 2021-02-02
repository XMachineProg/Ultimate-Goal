package org.firstinspires.ftc.teamcode.motors;

import android.database.sqlite.SQLiteException;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EngineMoviment {
    private EngineState es = new EngineState();

    public void turnLeft(DcMotor leftMotor, DcMotor rightMotor, long time) {
        if (es.isEnginesBusy(leftMotor, rightMotor) == 00) {
            rightMotor.setPower(1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            rightMotor.setPower(0);
        }

    }
}
