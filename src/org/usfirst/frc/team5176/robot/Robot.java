package org.usfirst.frc.team5176.robot;

import org.usfirst.frc.team5176.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team5176.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5176.robot.subsystems.GearNoms;
import org.usfirst.frc.team5176.robot.subsystems.Jetson;
import org.usfirst.frc.team5176.robot.subsystems.PewPew;
import org.usfirst.frc.team5176.robot.subsystems.Ultrasonics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static DriveTrain driveTrain;
	public static GearNoms gearNoms;
	public static PewPew pewPew;
	public static Jetson jetson;
	public static Ultrasonics ultrasonics;

	public static final double ACCELERATION = 0.3;
	
	public static double servoPos = 0.5;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		
		driveTrain = new DriveTrain();
		gearNoms = new GearNoms();
		pewPew = new PewPew();
		try {
			jetson = new Jetson();
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), false);
		}
		ultrasonics = new Ultrasonics();
		
		oi = new OI();
		
        autonomousCommand = new AutonomousCommandGroup();
		
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		gearNoms.gearFlag = false;
		pewPew.pewpewFlag = false;
	}

	@Override
	public void disabledPeriodic() {
		//Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		RobotMap.driveTrainRobotDrive41.setSafetyEnabled(false);
		
		gearNoms.gearFlag = true;
		pewPew.pewpewFlag = true;
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		RobotMap.driveTrainRobotDrive41.setSafetyEnabled(true);

		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
        SmartDashboard.putNumber("Shoot Speed", 0.5);
        
        SmartDashboard.putNumber("KP", 0.006);
        SmartDashboard.putNumber("KI", 0.00005);
        SmartDashboard.putNumber("KD", 0.00025);
        SmartDashboard.putNumber("Target", 725.0);
        
        gearNoms.gearFlag = true;
        pewPew.pewpewFlag = true;
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		ultrasonics.updateUltrasonics();
		
		if (oi.getCoJoystick().getRawButton(3)) {
			jetson.updateCamera(0);
		} else if (oi.getCoJoystick().getRawButton(4)) {
			jetson.updateCamera(1);
		}
		for (int i = 0; i < jetson.stuff.size(); i++) {
			SmartDashboard.putNumber("Center X " + i, jetson.stuff.get(i).getCenterX());
			SmartDashboard.putNumber("Center Y " + i, jetson.stuff.get(i).getCenterY());
			SmartDashboard.putNumber("Area " + i, jetson.stuff.get(i).getArea());
		}
		
		SmartDashboard.putNumber("Encoder Rate", RobotMap.pewPewEncoder.getRate());
		
		if (oi.getCoJoystick().getRawButton(5)) {
			RobotMap.ballsGrabber.set(Relay.Value.kForward);
		} else {
			RobotMap.ballsGrabber.set(Relay.Value.kOff);
		}
		RobotMap.ballsFondler.set(oi.getCoJoystick().getRawAxis(3));
		
		SmartDashboard.putBoolean("Limit Open", RobotMap.limitSwitchOpen.get());
		SmartDashboard.putBoolean("Limit Close", RobotMap.limitSwitchClose.get());
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		//RobotMap.driveTrainBackLeftMotor.set(oi.getCoJoystick().getRawAxis(1)); //BallsGrabber
		//RobotMap.driveTrainFrontLeftMotor.set(oi.getCoJoystick().getRawAxis(1)); //Front Right
		//RobotMap.driveTrainBackRightMotor.set(oi.getCoJoystick().getRawAxis(1)); //Back Right
		//RobotMap.driveTrainFrontRightMotor.set(oi.getCoJoystick().getRawAxis(1)); //Spiderman
		//RobotMap.pewpewMotor.set(oi.getCoJoystick().getRawAxis(1)); //Pewpew
		//RobotMap.spidermanMotor.set(oi.getCoJoystick().getRawAxis(1)); //Back Left
		//RobotMap.ballsGrabber.set(oi.getCoJoystick().getRawAxis(1)); //Balls Fondler
		//RobotMap.ballsFondler.set(oi.getCoJoystick().getRawAxis(1)); //Front Left
		LiveWindow.run();
	}
}
