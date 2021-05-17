package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

@Autonomous(name = "Detect Objects", group = "Concept")
public class TensorflowTests extends LinearOpMode {


    TFObjectDetector.Parameters tfodParameters = null;
    private FtcDashboard dashboard = FtcDashboard.getInstance();
    private Telemetry dashboardTelemetry = dashboard.getTelemetry();
    private int tfodMonitorViewId;
    private VuforiaLocalizer myVuforia = null;
    private VuforiaLocalizer.Parameters parameters = null;
    private TFObjectDetector myTfod = null;
    private Constants consts = new Constants();
    //private EngineMoviment em = new EngineMoviment();
    private RobotState robState = RobotState.TARGET_RING;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //setHardwareMap();
        initVuforia();
        initTfod();
        waitForStart();

        while (opModeIsActive()) {
            switch (robState) {
                case TARGET_RING:
                    targetRing();
                    break;
                case MOVE_RING:
                    //moveRing();
                    break;
                case DONE:
                    //shutdown();
                    break;
                case ERROR:
                    robState = RobotState.DONE;
                    break;
                case TEST:
                    //em.straightAhead(leftEngine, rightEngine, 2000);
                    break;
                default: {
                    telemetry.addData("Error", 500);
                    telemetry.update();
                    robState = RobotState.ERROR;
                }
            }
            RobotState robState = RobotState.TARGET_RING;
            sleep(1000);

        }
    }
    //private DcMotor leftEngine = null;
    //private DcMotor rightEngine = null;

    private void targetRing() {
        Recognition quad = null;

        Recognition single = null;

        List<Recognition> updateRecognitions = myTfod.getUpdatedRecognitions();
        if (updateRecognitions != null) {
            int i = 0;
            for (Recognition recognition : updateRecognitions) {
                telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                        recognition.getLeft(), recognition.getTop());
                telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                        recognition.getRight(), recognition.getBottom());
            }
        } else {
            telemetry.addData("Idle", "000");
            telemetry.update();
        }
    }

    private void initVuforia() {
        parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = consts.VULFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        myVuforia = ClassFactory.getInstance().createVuforia(parameters);

        dashboard.startCameraStream(myVuforia, 60);
    }

    private void initTfod() {
        tfodMonitorViewId =
                hardwareMap.appContext.getResources().getIdentifier(
                        "tfodMonitorViewId",
                        "id",
                        hardwareMap.appContext.getPackageName()
                )
        ;

        tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        myTfod =
                ClassFactory.getInstance().createTFObjectDetector(tfodParameters, myVuforia)
        ;

        myTfod.loadModelFromAsset("UltimateGoal.tflite", "Quad", "Single");

        myTfod.activate();
    }

    enum RobotState {
        TARGET_RING,
        MOVE_RING,
        DONE,
        TEST,
        ERROR,
    }

    /*public void setHardwareMap() {
        leftEngine = hardwareMap.get(DcMotor.class, "leftMotor");
        rightEngine = hardwareMap.get(DcMotor.class, "rightMotor");
        telemetry.addData("201", "Added to hardware list:" +
                hardwareMap.getNamesOf(leftEngine) + "  " + hardwareMap.getNamesOf(rightEngine));
        telemetry.update();

    }*/
}
