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
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 01) {
            if (rightMotor.getPower() < 1 ) {
                rightMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                rightMotor.setPower(0);
            } else {
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                rightMotor.setPower(0);
            }
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 10) {
            leftMotor.setPower(0);
            rightMotor.setPower(1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            rightMotor.setPower(0);
        } else {
            leftMotor.setPower(0);
            if (rightMotor.getPower() < 1) {
                rightMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                rightMotor.setPower(0);
            } else {
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                rightMotor.setPower(0);
            }
        }
    }

    public void turnRight(DcMotor leftMotor, DcMotor rightMotor, long time) {
        if (es.isEnginesBusy(leftMotor, rightMotor) == 00) {
            leftMotor.setPower(1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            leftMotor.setPower(0);
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 10) {
            if (leftMotor.getPower() < 1 ) {
                leftMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
            } else {
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
            }
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 01) {
            rightMotor.setPower(0);
            leftMotor.setPower(1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            leftMotor.setPower(0);
        } else {
            rightMotor.setPower(0);
            if (leftMotor.getPower() < 1) {
                leftMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
            } else {
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
            }
        }
    }

    public void straightAhead(DcMotor leftMotor, DcMotor rightMotor, long time) {
        if (es.isEnginesBusy(leftMotor, rightMotor) == 00) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 10) {
            if (leftMotor.getPower() < 1 ) {
                leftMotor.setPower(1);
                rightMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            } else {
                rightMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 01) {
            rightMotor.setPower(1);
            leftMotor.setPower(1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            leftMotor.setPower(0);
        } else {
            if (leftMotor.getPower() < 1) {
                leftMotor.setPower(1);
                rightMotor.setPower(1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            } else {
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        }
    }

    public void goBack(DcMotor leftMotor, DcMotor rightMotor, long time) {
        if (es.isEnginesBusy(leftMotor, rightMotor) == 00) {
            leftMotor.setPower(-1);
            rightMotor.setPower(-1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 10) {
            if (leftMotor.getPower() > 1 ) {
                leftMotor.setPower(-1);
                rightMotor.setPower(-1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            } else {
                rightMotor.setPower(-1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        } else if (es.isEnginesBusy(leftMotor, rightMotor) == 01) {
            rightMotor.setPower(-1);
            leftMotor.setPower(-1);
            try { Thread.sleep (time); } catch (InterruptedException ex) {}
            leftMotor.setPower(0);
        } else {
            if (leftMotor.getPower() > 1) {
                leftMotor.setPower(-1);
                rightMotor.setPower(-1);
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            } else {
                try { Thread.sleep (time); } catch (InterruptedException ex) {}
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        }
    }

}
