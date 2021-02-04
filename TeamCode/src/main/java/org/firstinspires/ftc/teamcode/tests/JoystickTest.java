package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.motors.EngineMoviment;

@TeleOp(name = "Joystick test", group = "Linear Opmode")
public class JoystickTest extends LinearOpMode {

    private DcMotor leftEngine = null;
    private DcMotor rightEngine = null;
    private NormalizedColorSensor colorSensor = null;
    private EngineMoviment em = new EngineMoviment();



    public void setHardwareMap() {
        leftEngine = hardwareMap.get(DcMotor.class, "leftMotor");
        rightEngine = hardwareMap.get(DcMotor.class, "rightMotor");
        colorSensor = (NormalizedColorSensor)hardwareMap.get("colorSensor");
        telemetry.addData("201", "Added to hardware list:" +
                hardwareMap.getNamesOf(leftEngine) + "  " + hardwareMap.getNamesOf(rightEngine));
        telemetry.update();

    }

    private double leftEnginePower = 0.0;
    private double rightEnginePower = 0.0;


    @Override
    public void runOpMode() throws InterruptedException {
        rightEngine.setDirection(DcMotorSimple.Direction.REVERSE);
        setHardwareMap();
        waitForStart();

        while (opModeIsActive()) {
            while (gamepad1.right_trigger > 0) {
                leftEnginePower = gamepad1.right_trigger;
                rightEnginePower = gamepad1.right_trigger;
                leftEngine.setPower(leftEnginePower);
                rightEngine.setPower(rightEnginePower);
            }
            while (gamepad1.right_trigger == 0) {
                leftEnginePower = gamepad1.right_trigger;
                rightEnginePower = gamepad1.right_trigger;
                leftEngine.setPower(leftEnginePower);
                rightEngine.setPower(rightEnginePower);
            }
        }
    }
}
