package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.internal.android.dx.rop.cst.Constant;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.motors.EngineMoviment;
import org.firstinspires.ftc.teamcode.vision.Initializers;

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
    Initializers visionInit = new Initializers();
    private FtcDashboard dashboard = FtcDashboard.getInstance();
    private Telemetry dashboardTelemetry = dashboard.getTelemetry();
    TFObjectDetector.Parameters tfodParameters = null;
    private int tfodMonitorViewId;
    private VuforiaLocalizer myVuforia = null;
    private VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    private TFObjectDetector myTfod = null;
    private Constants consts = new Constants();
    //private EngineMoviment em = new EngineMoviment();
    private RobotState robState = RobotState.TARGET_RING;
    //private DcMotor leftEngine = null;
    //private DcMotor rightEngine = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        visionInit.initVuforia(myVuforia, parameters);
        visionInit.initTfod(myTfod, tfodMonitorViewId, hardwareMap, tfodParameters);
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
    private void targetRing() {
         Recognition quad = null;

         Recognition single = null;

         List<Recognition> updateRecognitions = visionInit.updatedRecognitions();
         if (updateRecognitions != null) {
             telemetry.addData("Tagged Ring", "202");
             telemetry.update();
             for ( Recognition recognition : updateRecognitions) {
                 if (recognition.getLabel().equals(consts.FIRST_LABEL_NAME)) {
                     quad = recognition;
                 } else if (recognition.getLabel().equals(consts.SECOND_LABEL_NAME)) {
                     single = recognition;
                 }

                 if (quad != null) {
                         int quadLeftX = (int) quad.getTop();
                         int quadRightX = (int) quad.getBottom();
                         int quadCenterX = (quadLeftX + quadRightX) / 2;
                         int offset = quadCenterX - consts.SCREEN_WIDTH / 2;
                         telemetry.addData("Offset: ", offset);
                         telemetry.update();
                     } else {
                 }

                 if (single != null) {
                     int singleLeftX = (int) single.getTop();
                     int singleRightX = (int) single.getBottom();
                     int singleCenterX = (singleLeftX + singleRightX) / 2;
                     int offset = singleCenterX - consts.SCREEN_WIDTH / 2;
                     telemetry.addData("Offset: ", offset);
                     telemetry.update();
                 } else {
                 }



             }
         } else {
             telemetry.addData("Idle", "000");
             telemetry.update();
         }
    }
}
