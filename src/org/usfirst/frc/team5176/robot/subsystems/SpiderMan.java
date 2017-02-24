package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SpiderMan extends Subsystem {
	private final SpeedController spiderMotor = RobotMap.spidermanMotor;
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void setSpeed(double speed) {
		spiderMotor.set(speed);
	}
	
	public void climbUp() {
		spiderMotor.set(0.5);
	}
	
	public void climbDown() {
		spiderMotor.set(-0.5);
	}
	
	public void stop() {
		spiderMotor.set(0.0);
	}
}
