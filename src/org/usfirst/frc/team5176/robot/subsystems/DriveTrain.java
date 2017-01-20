package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;
import org.usfirst.frc.team5176.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    private final RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
	
	public void dankMemes(double x, double y, double rotation) {
		robotDrive41.mecanumDrive_Cartesian(x, y, rotation, 0.0);
    }
}
