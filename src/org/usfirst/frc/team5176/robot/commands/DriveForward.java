package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {
	private final int DRIVETIME;
	private final double SPEED;
	private static final DriveTrain driveTrain = Robot.driveTrain;
	
    public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(1, 0.25);
    }
    
    public DriveForward(int time, double speed) {
    	DRIVETIME = time;
    	SPEED = speed;
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(DRIVETIME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.driveForward(SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.kidRanIntoTheStreet();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
