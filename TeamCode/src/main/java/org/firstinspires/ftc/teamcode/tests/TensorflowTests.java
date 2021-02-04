package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.internal.android.dx.rop.cst.Constant;
import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

@Autonomous(name = "Detect Objects", group = "Concept")
public class TensorflowTests extends LinearOpMode {

    private Constants consts = new Constants();

    @Override
    public void runOpMode() throws InterruptedException {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = consts.VULFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        VuforiaLocalizer myVuforia = ClassFactory.getInstance().createVuforia(parameters);

        int tfodMonitorViewId =
                hardwareMap.appContext.getResources().getIdentifier (
                        "tfodMonitorViewId",
                        "id",
                        hardwareMap.appContext.getPackageName()
                )
        ;

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8f;

        TFObjectDetector myTfod =
                ClassFactory.getInstance().createTFObjectDetector(tfodParameters, myVuforia)
        ;

        myTfod.loadModelFromAsset("UltimateGoal.tflite", "Quad", "Single");

        myTfod.activate();

        waitForStart();
        while (opModeIsActive()) {

            List<Recognition> updatedRecognitions = myTfod.getUpdatedRecognitions();

            if (updatedRecognitions != null) {

                telemetry.addData("if", 1);
                telemetry.update();

                for (Recognition recognition : updatedRecognitions) {
                    telemetry.addData("for", 1);
                    telemetry.update();

                    if (recognition.getLabel() == "Quad") {
                        int quadLeftX = (int) recognition.getLeft();
                        int quadRightX = (int) recognition.getRight();
                        int quadCenterX = ( quadLeftX + quadRightX ) / 2;
                        int offset = quadCenterX - consts.SCREEN_WIDTH / 2;
                        telemetry.addData("102", "Quad location: " + offset);
                        telemetry.update();
                        break;
                    } else {
                        int singleLeftX = (int) recognition.getLeft();
                        int singleRightX = (int) recognition.getRight();
                        int singleCenterX = (int) (singleLeftX + singleRightX) / 2;
                        int offset = singleCenterX - consts.SCREEN_WIDTH /2;
                        telemetry.addData("102", "Single location " + offset);
                        telemetry.update();
                        break;
                    }
                }

            }  else {
                telemetry.addData ("404", "Nothing was recognized");
                telemetry.update();
            }


            telemetry.update();

            //sleep(1000);
        }
    }
}
