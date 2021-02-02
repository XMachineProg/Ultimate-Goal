package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.motors.EngineMoviment;
import org.firstinspires.ftc.teamcode.sensor.ColorSensor;


@TeleOp(name="Encoder Test", group="Linear Opmode")
public class TestEncoder extends LinearOpMode {
    private ColorSensor core = new ColorSensor();


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

    @Override
    public void runOpMode() {


        setHardwareMap(); // Return to setHardwareMap method
        leftEngine.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        leftEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftEngine.getCurrentPosition();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("200", "Inialized Code");
            telemetry.update();
            telemetry.addData(core.getReading(colorSensor), "202");
            telemetry.addData(core.sensorName(colorSensor), "202");

            telemetry.addData(String.valueOf(leftEngine.getCurrentPosition()), " | current position");

            leftEngine.setTargetPosition(500);
            leftEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftEngine.setPower(1);
            while (leftEngine.isBusy()) {

            }
            leftEngine.setPower(0);
            leftEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            break;
        }
    }
}
