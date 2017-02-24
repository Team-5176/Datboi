package org.usfirst.frc.team5176.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;
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
    public static SpeedController unleashGear;
    public static SpeedController ballsFondler;
    public static RobotDrive driveTrainRobotDrive41;
    public static Ultrasonic ultrasonicR;
    public static Ultrasonic ultrasonicL;
    public static Encoder pewPewEncoder;
    public static Relay ballsGrabber;
    public static DigitalInput limitSwitchOpen;
    public static DigitalInput limitSwitchClose;
    
    public static void init() {
    	driveTrainFrontLeftMotor = new VictorSP(6);
    	//driveTrainFrontLeftMotor.setInverted(true);
    	
    	driveTrainBackLeftMotor = new VictorSP(4);
    	//driveTrainBackLeftMotor.setInverted(true);
    	
    	driveTrainFrontRightMotor = new VictorSP(0);
    	driveTrainFrontRightMotor.setInverted(true);
    	
    	driveTrainBackRightMotor = new VictorSP(3);
    	driveTrainBackRightMotor.setInverted(true);
    	
    	spidermanMotor = new VictorSP(2);
    	
    	pewpewMotor = new VictorSP(5);
    	pewpewMotor.setInverted(true);
    	
    	ballsFondler = new VictorSP(7);
    	ballsFondler.setInverted(true);
    	
    	unleashGear = new VictorSP(1);
    	//unleashGear.setInverted(true);
    	
    	driveTrainRobotDrive41 = new RobotDrive(driveTrainFrontLeftMotor, driveTrainBackLeftMotor, driveTrainFrontRightMotor, driveTrainBackRightMotor); //added the negatives
    	
    	driveTrainRobotDrive41.setSafetyEnabled(true);
        driveTrainRobotDrive41.setExpiration(0.1);
        driveTrainRobotDrive41.setSensitivity(0.3);
        driveTrainRobotDrive41.setMaxOutput(1.0);
        
        ultrasonicR = new Ultrasonic(0, 1);
        ultrasonicL = new Ultrasonic(2, 3);
        
        pewPewEncoder = new Encoder(7, 8);
        
        ballsGrabber = new Relay(1);
        
        limitSwitchOpen = new DigitalInput(6);
        limitSwitchClose = new DigitalInput(5);
    }
}
