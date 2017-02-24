package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.subsystems.GearNoms;

import edu.wpi.first.wpilibj.command.Command;

public class CloseGearCatcher extends Command {
	private static final GearNoms gearnoms = Robot.gearNoms;
	
	public CloseGearCatcher() {
		requires(gearnoms);
	}
	
	@Override
	protected void initialize() {
		setTimeout(1);
	}
	
	@Override
	protected void execute() {
		gearnoms.closeNoms();
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
