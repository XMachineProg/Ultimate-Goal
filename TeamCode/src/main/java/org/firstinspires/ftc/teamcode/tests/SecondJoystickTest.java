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
    private double leftPower; // Power of left motor
    private double rightPower; // Power of right Motor
    private double max; // variable of the maximum and absolute value between two forces
    private double turn; // Left and right power of gamepad
    private double drive; // Front and back power of gamepad

    @Override
    public void init() {

        setHardwareMap(leftMotor, rightMotor); // Add motors to hwmap list
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE); // reverse the directions of the left motor
    }

    @Override
    public void loop() {

        drive = -gamepad1.left_stick_y; // Keep negative, otherwise the robot goes backwards
        turn = gamepad1.left_stick_x;
        leftPower = drive + turn; // Gets the left motor power along with the rotation
        rightPower = drive - turn; // Gets the right motor power along with the rotation

        max = Math.max(Math.abs(leftPower), Math.abs(rightPower)); // maximum and absolute value between two forces
        if (max > 1) { // If the power is greater than 1, it will be "normalized"
            leftPower /= max;
            rightPower /= max;
        }
        if (gamepad1.right_trigger == 0.0) { // If the right trigger is not pressed, the power will be reduced by half.
            leftPower /= 2.0;
            rightPower /= 2.0;
        }
        tlmtr.addData("Left power", leftPower);
        tlmtr.addData("Right power", rightPower);
        tlmtr.addData("Left ticks", leftMotor.getCurrentPosition());
        tlmtr.addData("Rigt ticks", rightMotor.getCurrentPosition());
        tlmtr.update();
        leftMotor.setPower(leftPower); // Set the power to the motor
        rightMotor.setPower(rightPower); // Set the power to the motor
    }


    private void setHardwareMap(DcMotor lm, DcMotor rm) {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        tlmtr.addData("HardwareMap ", leftMotor.getDeviceName() + " and " + rightMotor.getDeviceName() + " added to hwmap list!");
        tlmtr.update();
    }
}
