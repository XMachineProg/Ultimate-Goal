package org.firstinspires.ftc.teamcode.sensor;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Core;

public class ColorSensor extends Core implements InterfaceSensor {

    private String sensorName = super.colorSensor.getDeviceName(); // Gets the name of the device being used as a sensor
    private NormalizedRGBA colors = colorSensor.getNormalizedColors(); // Get colors in RGB form
    private String reading;


    @Override
    public String sensorName() {
        return sensorName;
    }

    @Override
    public String getReading() {
        reading = "" +
                "Alpha = " + colors.alpha +
                "Red = " + colors.red +
                "Green = " + colors.green +
                "Blue = " + colors.blue;
        return reading;
    }
}
