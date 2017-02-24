package org.usfirst.frc.team5176.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5176.robot.subsystems.Jetson;

import edu.wpi.first.wpilibj.command.Command;

public class ShootingAutoAlign extends Command {
	private final DriveTrain drivetrain = Robot.driveTrain;
	private final Jetson jetson = Robot.jetson;
	
	private boolean ready = false;
	
	private ArrayList<Jetson.Stuff> stuff;
	
	public ShootingAutoAlign() {
		requires(drivetrain);
	}
	
	@Override
	protected void execute() {
		new Thread(() -> {
			jetson.updateCamera(0);
			while (!jetson.readyToUpdate);
			stuff = jetson.stuff;
			ready = isAligned();
			while (!ready) {
				double offset = stuff.get(0).getCenterX() - 600;
				drivetrain.dankMemes(0.0, 0.0, offset / 2000);
				jetson.updateCamera(0);
				while (!jetson.readyToUpdate);
				stuff = jetson.stuff;
				ready = isAligned();
			}
		}).start();
	}

	@Override
	protected boolean isFinished() {
		return ready;
	}
	
	private boolean isAligned() {
		if (stuff.size() != 2) {
			return false;
		} else {
			//TODO Get the right values for this.
			//TODO Possibly change power for different Y's
			return stuff.get(0).getCenterX() > 500 && stuff.get(0).getCenterX() < 700;
		}
	}
}
