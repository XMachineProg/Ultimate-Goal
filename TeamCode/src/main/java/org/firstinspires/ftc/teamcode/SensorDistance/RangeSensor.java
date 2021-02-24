package org.firstinspires.ftc.teamcode.SensorDistance;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RangeSensor {
   protected DistanceSensor range; // this variable is responsibility for distance sensor
   private HardwareMap hwMap; // this variable is responsibility for hardware map
   protected Telemetry tlmtr; // this variable is responsibility for Telemetry

   public void hardwareMap(){
      range = (DistanceSensor)hwMap.get("Range"); // this variable is responsible for the connectivity of the variable to the distance sensor
      tlmtr.addData("Sensor Range", "Loaded");
      tlmtr.update();

   }

    }


