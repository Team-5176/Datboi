package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.subsystems.PewPew;

import edu.wpi.first.wpilibj.command.Command;

public class KeepDeezBallz extends Command {
	private static final PewPew pewpew = new PewPew();
	
	public KeepDeezBallz() {
		requires(pewpew);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.2);
	}
	
	@Override
	protected void execute() {
		pewpew.stopShooter();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
