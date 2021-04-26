package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

public class Initializers {
    private TFObjectDetector tfod;
    private VuforiaLocalizer vuforiaLocalizer;

    public void initVuforia(VuforiaLocalizer vuforiaLocalizer, VuforiaLocalizer.Parameters vuforiaParameters) {
        vuforiaParameters.vuforiaLicenseKey = Constants.VULFORIA_KEY; // Add vuforiaKey
        vuforiaParameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK; // Set camera direction
        vuforiaLocalizer = ClassFactory.getInstance().createVuforia(vuforiaParameters);
        this.vuforiaLocalizer = vuforiaLocalizer;
    }

    public void initTfod(TFObjectDetector tfod, int tfodMonitorViewId, HardwareMap hardwareMap, TFObjectDetector.Parameters tfodParameters) {
        this.tfod = tfod;
        tfodMonitorViewId =
                hardwareMap.appContext.getResources().getIdentifier(
                        "tfodMonitorViewId",
                        "id",
                        hardwareMap.appContext.getPackageName()
                )
        ; // Get Tensorflow monitor view id

        tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId); // Create parameters
        tfodParameters.minResultConfidence = 0.8    f; // Minimun confidence to object detection

        tfod =
                ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforiaLocalizer)
        ;
        this.tfod = tfod;
        tfod.loadModelFromAsset("UltimateGoal.tflite", "Quad", "Single"); // load model
        tfod.activate();
    }

    public List<Recognition> updatedRecognitions() {
        return tfod.getUpdatedRecognitions();
    }

}
