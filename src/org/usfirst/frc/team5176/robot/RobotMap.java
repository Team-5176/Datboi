package org.usfirst.frc.team5176.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static SpeedController driveTrainFrontLeftMotor;
    public static SpeedController driveTrainBackLeftMotor;
    public static SpeedController driveTrainFrontRightMotor;
    public static SpeedController driveTrainBackRightMotor;
    public static SpeedController spidermanMotor;
    public static SpeedController pewpewMotor;
    public static RobotDrive driveTrainRobotDrive41;
    
    public static void init() {
    	driveTrainFrontLeftMotor = new VictorSP(0);
    	
    	driveTrainBackLeftMotor = new VictorSP(1);
    	
    	driveTrainFrontRightMotor = new VictorSP(2);
    	
    	driveTrainBackRightMotor = new VictorSP(3);
    	
    	spidermanMotor = new VictorSP(4);
    	
    	pewpewMotor = new VictorSP(5);
    	
    	driveTrainRobotDrive41 = new RobotDrive(driveTrainFrontLeftMotor, driveTrainBackLeftMotor, driveTrainFrontRightMotor, driveTrainBackRightMotor);
    	
    	driveTrainRobotDrive41.setSafetyEnabled(true);
        driveTrainRobotDrive41.setExpiration(0.1);
        driveTrainRobotDrive41.setSensitivity(0.3);
        driveTrainRobotDrive41.setMaxOutput(1.0);
    }
}
