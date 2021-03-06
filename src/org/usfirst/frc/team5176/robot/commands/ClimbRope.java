package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.subsystems.SpiderMan;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbRope extends Command {
	private static final SpiderMan spiderman = new SpiderMan();
	
	public ClimbRope() {
		requires(spiderman);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.1);
	}
	
	@Override
	protected void execute() {
		spiderman.climbUp();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
