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

        myTfod.loadModelFromAsset("Skystone.tflite", "Stone", "Skystone");

        myTfod.activate();

        waitForStart();
        while (opModeIsActive()) {

            List<Recognition> updatedRecognitions = myTfod.getUpdatedRecognitions();

            for (Recognition recognition : updatedRecognitions) {
                telemetry.addData(" > ", recognition.getLabel());
            }
            telemetry.update();
        }
    }
}
