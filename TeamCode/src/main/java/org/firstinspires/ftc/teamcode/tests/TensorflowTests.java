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

        TFObjectDetector myTfod =
                ClassFactory.getInstance().createTFObjectDetector(tfodParameters, myVuforia)
        ;

        myTfod.loadModelFromAsset("UsualThings.tflite", "person",
                "bicycle",
                "car",
                "motorcycle",
                "airplane",
                "bus",
                "train",
                "truck",
                "boat",
                "traffic light",
                "fire hydrant",
                "?",
                "stop sign",
                "parking meter",
                "bench",
                "bird",
                "cat",
                "dog",
                "horse",
                "sheep",
                "cow",
                "elephant",
                "bear",
                "zebra",
                "giraffe",
                "??",
                "backpack",
                "umbrella",
                "???",
                "handbag",
                "tie",
                "suitcase",
                "frishbee",
                "skis",
                "snowboard",
                "sport balls",
                "kite",
                "baseball bat",
                "baseball glove",
                "skateboard",
                "tennis racket",
                "bottle",
                "????",
                "wine glass",
                "cup",
                "fork",
                "knife",
                "spoon",
                "bowl",
                "banana",
                "apple",
                "sandwich",
                "orange",
                "broccoli",
                "carrot",
                "hotdog",
                "pizza",
                "donut",
                "cake",
                "chair",
                "couch",
                "potted plant",
                "bed",
                "?????",
                "dinning table",
                "??????",
                "???????",
                "toilet",
                "????????",
                "tv",
                "laptop",
                "mouse",
                "remote",
                "keyboard",
                "cell phone",
                "microwave",
                "oven",
                "toaster",
                "sink",
                "refrigerator",
                "?????????",
                "book",
                "clock",
                "vase",
                "scissors",
                "teddy bear",
                "hair drief",
                "toothbrush"
        );

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
