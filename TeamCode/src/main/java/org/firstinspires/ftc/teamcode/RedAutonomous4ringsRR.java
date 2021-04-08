package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@Config
@Autonomous(group = "drive")
public class RedAutonomous4ringsRR extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d(-48, -48, 0))
                .lineTo(new Vector2d(-5, -60))
                .addTemporalMarker(2, () -> {})
                .build();
        Trajectory traj2 = drive.trajectoryBuilder(trajectory.end())
                .lineToLinearHeading(new Pose2d(-48, -48, Math.toRadians(90)))
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())

                .lineToLinearHeading(new Pose2d(-53, -38, Math.toRadians(90)))
                .addTemporalMarker(0.5, () -> {
                    drive.setCrowlPower(0.3);
                })
                .addTemporalMarker(0.5, () -> {
                    drive.setCrowlPower(0);
                })
                .build();

        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .lineToLinearHeading(new Pose2d(-48, -48, Math.toRadians(90)))
                .build();

        Trajectory traj5 = drive.trajectoryBuilder(traj4.end())
                .lineToLinearHeading(new Pose2d(-5, -60, 0))
                .build();
        Trajectory traj6 = drive.trajectoryBuilder(traj5.end())
                .strafeLeft(10)
                .addTemporalMarker(2, () -> {})
                .build();
        Trajectory traj7 = drive.trajectoryBuilder(traj6.end())
                .strafeTo(new Vector2d(0, -35))
                .addTemporalMarker(2, () -> {
                    drive.turn(Math.toRadians(-8));
                })
                .addTemporalMarker(2, () -> {
                    drive.turn(Math.toRadians(-8));
                })
                .addTemporalMarker(2, () -> {
                    drive.turn(Math.toRadians(-8));
                })
                .addTemporalMarker(2, () -> {
                    drive.turn(Math.toRadians(24));
                })
                .build();
        Trajectory traj8 = drive.trajectoryBuilder(traj7.end())
                .back(15)
                .build();
        Trajectory traj9 = drive.trajectoryBuilder(traj8.end())
                .forward(15)
                .addTemporalMarker(6, () -> {})
                .build();
        Trajectory traj10 = drive.trajectoryBuilder(traj9.end())
                .back(20)
                .build();
        Trajectory traj11 = drive.trajectoryBuilder(traj10.end())
                .forward(20)
                .addTemporalMarker(4, () -> {})
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(trajectory);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
        drive.followTrajectory(traj4);

        drive.followTrajectory(traj5);
        drive.followTrajectory(traj6);
        drive.followTrajectory(traj7);

        drive.followTrajectory(traj8);
        drive.followTrajectory(traj9);
        drive.followTrajectory(traj10);
        drive.followTrajectory(traj11);


        //Pose2d poseEstimate = drive.getPoseEstimate();
        //telemetry.addData("finalX", poseEstimate.getX());
        //telemetry.addData("finalY", poseEstimate.getY());
        //telemetry.addData("finalHeading", poseEstimate.getHeading());
        //telemetry.update();

        while (!isStopRequested() && opModeIsActive()) ;
    }


    /*
    @Override

    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-48, -48, 0);


        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(-5, -60))
                .addTemporalMarker(2, () -> {})
                .build();
        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(-48, -48, Math.toRadians(90)))
                .build();
        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .lineToLinearHeading(new Pose2d(-53, -38, Math.toRadians(90)))
                .addTemporalMarker(2, () -> {})
                .build();
        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .lineToLinearHeading(new Pose2d(-53, -38, Math.toRadians(90)))
                .build();
        Trajectory traj5 = drive.trajectoryBuilder(traj4.end())
                .lineToLinearHeading(new Pose2d(-5, -60, 0))
                .build();
        Trajectory traj6 = drive.trajectoryBuilder(traj5.end())
                .strafeLeft(10)
                .build();
        Trajectory traj7 = drive.trajectoryBuilder(traj6.end())
                .strafeTo(new Vector2d(0, -35))
                .build();
        Trajectory traj8 = drive.trajectoryBuilder(traj7.end())
                .back(15)
                .build();
        Trajectory traj9 = drive.trajectoryBuilder(traj8.end())
                .forward(15)
                .build();
        Trajectory traj10 = drive.trajectoryBuilder(traj9.end())
                .back(20)
                .build();
        Trajectory traj11 = drive.trajectoryBuilder(traj10.end())
                .forward(20)
                .build();



        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
        drive.followTrajectory(traj4);
        drive.followTrajectory(traj5);
        drive.followTrajectory(traj6);
        drive.followTrajectory(traj7);
        drive.followTrajectory(traj8);
        drive.followTrajectory(traj9);
        drive.followTrajectory(traj10);
        drive.followTrajectory(traj11);
        Pose2d poseEstimate = drive.getPoseEstimate();

        while (opModeIsActive() && !isStopRequested()) ;

        //drive.turn(-5);
        //sleep(2000);
        //drive.turn(-5);
        //sleep(2000);
        //drive.turn(-5);
        //sleep(2000);
        //drive.turn(15);


    }*/
}
