package org.usfirst.frc.team5176.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup {
	private final int DRIVETIME = 60;
	private final double SPEED = 0.2;
	
	public AutonomousCommandGroup() {
		//addSequential(new DriveForward(DRIVETIME, SPEED));
		//addSequential(new ShootDeezBallz());
	}
}
