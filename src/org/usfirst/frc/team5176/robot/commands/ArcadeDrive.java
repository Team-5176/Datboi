package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	private double joyX, joyY, twist, lastX, lastY, lastTwist;
	
	private Joystick joystick;
	
	public ArcadeDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		joystick = Robot.oi.getPilotJoystick();
		lastX = 0.0;
		lastY = 0.0;
		lastTwist = 0.0;
	}
	
	@Override
	protected void execute() {
		joyX = joystick.getRawAxis(0);
		joyY = joystick.getRawAxis(1);
		twist = joystick.getRawAxis(4) / 2.0;
		
		if(Math.abs(joyX) < 0.20) {
	    	joyX = 0.0;
		}
    	
    	if(Math.abs(joyY) < 0.20) {
	    	joyY = 0.0;
    	}
    	
    	if(Math.abs(twist) < 0.20) {
	    	twist = 0.0;
    	}
    	
    	lastX = lastX + Robot.ACCELERATION * (joyX * 0.75 - lastX);
    	lastY = lastY + Robot.ACCELERATION * (joyY * 0.75 - lastY);
    	lastTwist = lastTwist + Robot.ACCELERATION * (twist * 0.75 - lastTwist);
    	
    	Robot.driveTrain.dankMemes(lastX, lastY, lastTwist);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
