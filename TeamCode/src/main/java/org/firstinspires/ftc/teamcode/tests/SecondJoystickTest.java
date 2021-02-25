package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Iterativ Joystick Test", group = "Iterative OpMode")
public class SecondJoystickTest extends OpMode {
    private FtcDashboard dashboard = FtcDashboard.getInstance(); // Get instnace from FtcDashboard
    private Telemetry tlmtr = dashboard.getTelemetry(); //Telemtry variable

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        setHardwareMap(leftMotor, rightMotor); // Add motors to hwmap list
    }

    @Override
    public void loop() {

    }

    private void setHardwareMap(DcMotor lm, DcMotor rm) {
        lm = hardwareMap.get(DcMotor.class, "leftMotor");
        rm = hardwareMap.get(DcMotor.class, "rightMotor");
        tlmtr.addData("HardwareMap; ", lm.getDeviceName() + " and " + rm.getDeviceName() + " added to hwmap list!");
        tlmtr.update();
    }
}
