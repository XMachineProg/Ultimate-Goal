package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Constants {

    public static final double TICKS_PER_REVOLUTION = 1120;
    public static final double MAX_RPM = 150;
    public static final boolean RUN_USING_ENCODER = true;

    //public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0, 0, 0,
    //        getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

    public static final double WHEEL_RADIUS = 3.54;
    public static final double GEAR_RATIO = 2.0 / 1.0;
    public static final double TRACK_WIDTH = 2.75;

    /*
    public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
    public static double kA = 0;
    public static double kStatic = 0;
     */

    public static final double MAX_VEL = 77.80;
    public static final double MAX_ACCEL = 77.80;
    public static final double MAX_ANG_VEL = Math.toRadians(28);
    public static final double MAX_ANG_ACCEL = Math.toRadians(28);


    public final String VULFORIA_KEY = "" +
            "Ad0XRoH/////AAABmSr0ccFZ1EoRoG+s5pV2S0koIWGdGTrMJPWjfqhHu+w8pWUxyquP2vbm1ZIetL5fz1PWkzldJxg5ZGMNLh5+TD9FHfUBFFY9+4HuxOsWHKa8QbZQD87XhdPUI+Yu1dQnheR+ZHhuUP4/YBDfvmHBQOOlTJjhtKNjYBypyZgumjL3pCuo3VOD02wDxYZ1Oh79aqWF8ESwi0V683flO0juAdFCQXd5m29OuLVwIu0A4Msoh5P2+V8TLkmUkA4KCxGF9YR/1PLqDh5GG6oWpEuDwaLsnnMBMlpe28sgLyAHtlhWOLvCrrr21BKc9oJvb88vne493ambW79yxA0LAmKBHNbXBoWlQ/QoB7ojAhMGkvHe"
    ; // VULFORIA KEY

    public final int SCREEN_WIDTH = 1080;

    public final String FIRST_LABEL_NAME = "Quad";
    public final String SECOND_LABEL_NAME = "Single";
    public final Double HD_401_TICKS = 1120.00;
    public final Double GEARBOX_ONE = 0.50;
    public final Double OMNIWHEELS_DIAMETER = 3.54;
    public final Double PI = 3.14;

}
