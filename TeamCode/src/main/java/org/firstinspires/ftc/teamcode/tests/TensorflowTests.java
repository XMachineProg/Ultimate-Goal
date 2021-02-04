package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.internal.android.dx.rop.cst.Constant;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.motors.EngineMoviment;

import java.util.List;

@Autonomous(name = "Detect Objects", group = "Concept")
public class TensorflowTests extends LinearOpMode {



    enum RobotState
    {
        TARGET_RING,
        MOVE_RING,
        DONE,
        TEST,
        ERROR,
    }
    TFObjectDetector.Parameters tfodParameters = null;
    private int tfodMonitorViewId;
    private VuforiaLocalizer myVuforia = null;
    private VuforiaLocalizer.Parameters parameters = null;
    private TFObjectDetector myTfod = null;
    private Constants consts = new Constants();
    private EngineMoviment em = new EngineMoviment();
    private RobotState robState = RobotState.TARGET_RING;
    private DcMotor leftEngine = null;
    private DcMotor rightEngine = null;

    @Override
    public void runOpMode() throws InterruptedException {

        setHardwareMap();
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
                    em.straightAhead(leftEngine, rightEngine, 2000);
                    break;
                default: {
                    telemetry.addData("Error", 500);
                    telemetry.update();
                    robState = RobotState.ERROR;
                }
            }
            RobotState robState = RobotState.TARGET_RING;
            sleep(2000);

        }
    }

    private void targetRing() {
         Recognition quad = null;

         Recognition single = null;

         List<Recognition> updateRecognitions = myTfod.getUpdatedRecognitions();
         if (updateRecognitions != null) {
             telemetry.addData("Tagged Ring", "202");
             telemetry.update();
             for ( Recognition recognition : updateRecognitions) {
                 telemetry.addData("Tagged Ring", recognition.getLabel());
                 telemetry.update();
                 if (recognition.getLabel().equals(consts.FIRST_LABEL_NAME)) {
                     telemetry.addData("Inside", "Quad");
                     quad = recognition;
                     telemetry.addData("Recognition: ", quad.getConfidence());
                     //break;
                 } else if (recognition.getLabel().equals(consts.SECOND_LABEL_NAME)) {
                     telemetry.addData("Inside", recognition.getLabel());
                     single = recognition;
                     telemetry.addData("Recognition: ", single.getConfidence());
                     //break;
                 }
                 telemetry.addData("Recognition: ", quad.estimateAngleToObject(AngleUnit.DEGREES));
                 telemetry.update();

                 if (quad != null) {
                         int quadLeftX = (int) quad.getLeft();
                         int quadRightX = (int) quad.getRight();
                         int quadCenterX = (quadLeftX + quadRightX) / 2;
                         int offset = quadCenterX - consts.SCREEN_WIDTH / 2;
                         telemetry.addData("Offset: ", offset);
                         telemetry.update();
                     } else {
                         telemetry.addData("101", "No quad");
                         telemetry.update();
                 }

                 while (single != null) {
                     int singleLeftX = (int) single.getRight();
                     int singleRightX = (int) single.getRight();
                     int singleCenterx = (singleLeftX + singleRightX) / 2;
                     int offset = singleCenterx - consts.SCREEN_WIDTH / 2;
                     telemetry.addData("Offset: ", offset);
                     telemetry.update();
                 } /*else {
                     telemetry.addData("101", "No single");
                 }*/



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
    }

    private void initTfod() {
         tfodMonitorViewId =
                hardwareMap.appContext.getResources().getIdentifier (
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

    public void setHardwareMap() {
        leftEngine = hardwareMap.get(DcMotor.class, "leftMotor");
        rightEngine = hardwareMap.get(DcMotor.class, "rightMotor");
        telemetry.addData("201", "Added to hardware list:" +
                hardwareMap.getNamesOf(leftEngine) + "  " + hardwareMap.getNamesOf(rightEngine));
        telemetry.update();

    }
}
