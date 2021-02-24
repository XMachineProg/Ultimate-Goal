package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.motors.EngineMoviment;
import org.firstinspires.ftc.teamcode.sensor.ColorSensor;


@TeleOp(name="Encoder Test", group="Linear Opmode")
public class TestEncoder extends LinearOpMode {

    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    private Constants cons = new Constants();

    private ColorSensor core = new ColorSensor();


    private DcMotor leftEngine = null;
    private DcMotor rightEngine = null;
    private DcMotor coreMotor = null;
    private NormalizedColorSensor colorSensor = null;
    private EngineMoviment em = new EngineMoviment();
    private Double CPI = cons.HD_401_TICKS * (cons.GEARBOX_ONE) / (cons.PI*cons.OMNIWHEELS_DIAMETER); // 11,1316366526715 expected value


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
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        setHardwareMap(); // Return to setHardwareMap method
        telemetry.addData("CPI puro ", CPI);
        leftEngine.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {



            telemetry.addData("200", "Inialized Code");telemetry.update();


            rightEngine.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftEngine.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            telemetry.addData("Encoder Position: ", rightEngine.getCurrentPosition() + "left: " + leftEngine.getCurrentPosition());
            rightEngine.setTargetPosition((int) (CPI * 39));
            leftEngine.setTargetPosition((int) (CPI * 39));

            rightEngine.setPower(.5);
            leftEngine.setPower(.5);
            rightEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (rightEngine.isBusy() || leftEngine.isBusy()) {
                telemetry.addData("Path", "driving");
                telemetry.addData("Encoder position: ", rightEngine.getCurrentPosition() + " " + leftEngine.getCurrentPosition());
                telemetry.update();
            }

            rightEngine.setPower(0);
            leftEngine.setPower(0);
            telemetry.addData("Path", "complete");
            telemetry.update();

            break;
        }
    }
}
