package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.tests.SecondJoystickTest.RobotState.ERROR;
import static org.firstinspires.ftc.teamcode.tests.SecondJoystickTest.RobotState.IDLE;
import static org.firstinspires.ftc.teamcode.tests.SecondJoystickTest.RobotState.MOVING;

@TeleOp(name = "Iterativ Joystick Test", group = "Iterative OpMode")
public class SecondJoystickTest extends OpMode {

    enum RobotState // Robot states enum
    {
        IDLE,
        MOVING,
        ERROR
    }

    private FtcDashboard dashboard = FtcDashboard.getInstance(); // Get instnace from FtcDashboard
    private Telemetry tlmtr = dashboard.getTelemetry(); //Telemtry variable

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    //private double gmX = gamepad1.left_stick_x;
    //private double gmY = gamepad1.left_stick_y;

    private RobotState robotState = IDLE;

    @Override
    public void init() {
        setHardwareMap(leftMotor, rightMotor); // Add motors to hwmap list
    }

    @Override
    public void loop() {
        switch (robotState) {
            case IDLE:
                idle();
                break;
            case MOVING:
                moving();
                break;
            case ERROR:
                error();
                break;
        }
        while (preProcessGamepad() == 3) {
            // Wait changes
            robotState = IDLE;
        }
        robotState = MOVING;
    }

    private int preProcessGamepad() {
        if (gamepad1.left_stick_x != 0 && gamepad1.left_stick_y == 0) {
            return 0;
        } else if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y != 0) {
            return 1;
        }
        return 3;
    }

    private void idle() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        return;
    }

    private void moving() {
        int returnPreProcess = preProcessGamepad();
        while (returnPreProcess == 0) {
            leftMotor.setPower(gamepad1.left_stick_x);
            rightMotor.setPower(gamepad1.left_stick_x);
        }
        while (returnPreProcess == 1) {
            leftMotor.setPower(-gamepad1.left_stick_y);
            rightMotor.setPower(gamepad1.left_stick_y);
        }
        while (returnPreProcess == 3) {
            robotState = ERROR;
            return;
        }
        robotState = IDLE;
        return;
    }

    private void error() {

    }

    private void setHardwareMap(DcMotor lm, DcMotor rm) {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        tlmtr.addData("HardwareMap ", leftMotor.getDeviceName() + " and " + rightMotor.getDeviceName() + " added to hwmap list!");
        tlmtr.update();
    }
}
