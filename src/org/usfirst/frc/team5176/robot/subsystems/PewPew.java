package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PewPew extends Subsystem {
	private final SpeedController pewMotor = RobotMap.pewpewMotor;
	
	@Override
	protected void initDefaultCommand() {
		
	}

	public void startShooter() {
		pewMotor.set(1.0);
	}
	
	public void stopShooter() {
		pewMotor.set(0.0);
	}
}
