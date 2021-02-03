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
    private DcMotor coreMotor = null;
    private NormalizedColorSensor colorSensor = null;
    private EngineMoviment em = new EngineMoviment();

    public void setHardwareMap() {
        leftEngine = hardwareMap.get(DcMotor.class, "leftMotor");
        rightEngine = hardwareMap.get(DcMotor.class, "rightMotor");
        coreMotor = hardwareMap.get(DcMotor.class, "coreMotor");
        colorSensor = (NormalizedColorSensor)hardwareMap.get("colorSensor");
        telemetry.addData("201", "Added to hardware list:" +
                hardwareMap.getNamesOf(leftEngine) + "  " + hardwareMap.getNamesOf(rightEngine));
        telemetry.update();

    }

    @Override
    public void runOpMode() {

        setHardwareMap(); // Return to setHardwareMap methodleftEngine.setDirection(DcMotor.Direction.REVERSE);waitForStart();

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("200", "Inialized Code");telemetry.update();

            telemetry.addData(String.valueOf(leftEngine.getCurrentPosition()), " | current position");

            coreMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            coreMotor.setTargetPosition(90);

            coreMotor.setPower(.5);
            coreMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (coreMotor.isBusy()) {
                telemetry.addData("Path", "driving");
                telemetry.update();
            }

            coreMotor.setPower(0);
            telemetry.addData("Path", "complete");
            telemetry.update();

            break;
        }
    }
}
