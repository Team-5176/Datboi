package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	private double joyX;
	private double joyY;
	private double twist;
	
	private Joystick joystick;
	
	public ArcadeDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		joystick = Robot.oi.getPilotJoystick();
	}
	
	@Override
	protected void execute() {
		joyX = joystick.getX();
		joyY = joystick.getY();
		twist = joystick.getTwist() / 2.0;
		
		if(Math.abs(joyX) < 0.20) {
	    	joyX = 0.0;
		}
    	
    	if(Math.abs(joyY) < 0.20) {
	    	joyY = 0.0;
    	}
    	
    	if(Math.abs(twist) < 0.30) {
	    	twist = 0.0;
    	}
    	
    	Robot.driveTrain.dankMemes(joyX, joyY, twist);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
