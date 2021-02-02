package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class TestMotorLeftRight extends LinearOpMode{
    //Vars
    private Core core = new Core();
    //private Telemetry tlmtr = null;
    private DcMotor leftMotor = core.leftMotor;
    private DcMotor rightMotor = core.rightMotor;

    @Override
    public void runOpMode() {
        telemetry.addData("200", "Intialized");
        telemetry.update();
        core.setHardwareMap();

        waitForStart();
        while (opModeIsActive()) {
                leftMotor.setPower(1.0);
                if (leftMotor.isBusy()) {
                    telemetry.addData("200", "running");
                    telemetry.update();
                } else {telemetry.addData("404", "motor not found"); telemetry.update();}
        }

    }
}
