package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Iterativ Joystick Test", group = "Iterative OpMode")
public class SecondJoystickTest extends OpMode {


    private FtcDashboard dashboard = FtcDashboard.getInstance(); // Get instnace from FtcDashboard
    private Telemetry tlmtr = dashboard.getTelemetry(); //Telemtry variable

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private double leftPower;
    private double rightPower;
    private double max;
    private double turn;
    private double drive;

    @Override
    public void init() {

        setHardwareMap(leftMotor, rightMotor); // Add motors to hwmap list
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        drive = -gamepad1.left_stick_y;
        turn = gamepad1.left_stick_x;
        leftPower = drive + turn;
        rightPower = drive - turn;

        max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if (max > 1) {
            leftPower /= max;
            rightPower /= max;
        }
        if (gamepad1.right_trigger == 0.0) {
            leftPower /= 2.0;
            rightPower /= 2.0;
        }
        tlmtr.addData("Left power", leftPower);
        tlmtr.addData("Right power", rightPower);
        tlmtr.update();
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }


    private void setHardwareMap(DcMotor lm, DcMotor rm) {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        tlmtr.addData("HardwareMap ", leftMotor.getDeviceName() + " and " + rightMotor.getDeviceName() + " added to hwmap list!");
        tlmtr.update();
    }
}
