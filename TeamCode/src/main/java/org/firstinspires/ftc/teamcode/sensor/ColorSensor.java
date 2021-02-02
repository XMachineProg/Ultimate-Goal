package org.firstinspires.ftc.teamcode.sensor;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Core;

public class ColorSensor implements InterfaceSensor {






    private Float redColor(NormalizedColorSensor sensor) {
        return sensor.getNormalizedColors().red;
    }
    private Float greenColor(NormalizedColorSensor sensor) {
        return sensor.getNormalizedColors().green;
    }
    private Float blueColor(NormalizedColorSensor sensor) {
        return sensor.getNormalizedColors().blue;
    }

    private String calculateColor(Float red, Float green, Float blue) {
        if (red > green && red > blue) {
            return "Red";
        } else if (green > red && green > blue) {
            return "Green";
        } else if (red == blue && red == green) {
            return "Black";
        }else {
            return "Blue";
        }
    }

    public String sensorName(NormalizedColorSensor sensor) {
        return sensor.getDeviceName();
    }

    @Override
    public String getReading(NormalizedColorSensor sensor) {
        return calculateColor(redColor(sensor), greenColor(sensor), blueColor(sensor));
    }
}
