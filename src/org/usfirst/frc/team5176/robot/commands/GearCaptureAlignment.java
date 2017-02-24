package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5176.robot.subsystems.Ultrasonics;

import edu.wpi.first.wpilibj.command.Command;

public class GearCaptureAlignment extends Command {
	private final DriveTrain drivetrain = Robot.driveTrain;
	private final Ultrasonics ultrasonics = Robot.ultrasonics;
	
	private boolean ready = false;
	
	public GearCaptureAlignment() {
		requires(drivetrain);
	}
	
	@Override
	protected void execute() {
		new Thread(() -> {
			boolean[] range = inRange();
			ready = isAligned();
			while (!ready) {
				if (range[0] && !range[1]) {
					drivetrain.dankMemes(0.1, 0.0, 0.0);
				} else if (range[1] && !range[0]) {
					drivetrain.dankMemes(-0.1, 0.0, 0.0);
				}
				range = inRange();
				ready = isAligned();
			}
			drivetrain.kidRanIntoTheStreet();
		});
	}
	
	@Override
	protected boolean isFinished() {
		return ready;
	}
	
	private boolean isAligned() {
		boolean[] range = inRange();
		return range[0] && range[1];
	}

	private boolean[] inRange() {
		boolean[] range = new boolean[2];
		if (ultrasonics.getDistL() > 15.0 && ultrasonics.getDistL() < 50.0) {
			range[0] = true;
		}
		if (ultrasonics.getDistR() > 15.0 && ultrasonics.getDistR() < 50.0) {
			range[1] = true;
		}
		return range;
	}
}
