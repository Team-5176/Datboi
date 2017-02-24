package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.subsystems.SpiderMan;

import edu.wpi.first.wpilibj.command.Command;

public class StopClimbing extends Command {
	private static final SpiderMan spiderman = new SpiderMan();
	
	public StopClimbing() {
		requires(spiderman);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.1);
	}
	
	@Override
	protected void execute() {
		spiderman.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
