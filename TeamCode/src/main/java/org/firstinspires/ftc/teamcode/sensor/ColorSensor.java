package org.firstinspires.ftc.teamcode.sensor;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Core;

public class ColorSensor extends Core /*implements InterfaceSensor*/ {

    //private String sensorName = super.colorSensor.getDeviceName(); // Gets the name of the device being used as a sensor
    //private NormalizedRGBA colors = colorSensor.getNormalizedColors(); // Get colors in RGB form


    private String calculateColor(Float red, Float green, Float blue) {
        if (red > green && red > blue) {
            return "Red";
        } else if (green > red && green > blue) {
            return "Green";
        }  else {
            return "Blue";
        }
    }
    /*
    @Override
    public String sensorName() {
        return sensorName;
    }

    @Override
    public String getReading() {
        return calculateColor(colors.red, colors.green, colors.blue);
    }*/
}
