package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.subsystems.PewPew;

import edu.wpi.first.wpilibj.command.Command;

public class ShootDeezBallz extends Command {
	private static final PewPew pewpew = new PewPew();
	
	public ShootDeezBallz() {
		requires(pewpew);
	}
	
	@Override
	protected void initialize() {
		setTimeout(0.2);
	}
	
	@Override
	protected void execute() {
		pewpew.startShooter();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
