package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ultrasonics extends Subsystem {
	private final Ultrasonic ultrasonicR = RobotMap.ultrasonicR,
							 ultrasonicL = RobotMap.ultrasonicL;
	
	private static double ultraDistR = 0,
			  			  ultraDistL = 0;

	private static double lastUltraDistR = 0,
						  lastUltraDistL = 0;

	private static int ultraCountR = 0,
					   ultraCountL = 0;

	private static final double MAX_ULTRA_DIFF = 5;

	@Override
	protected void initDefaultCommand() {
        ultrasonicR.setAutomaticMode(true);
        ultrasonicL.setAutomaticMode(true);
        lastUltraDistR = ultrasonicR.getRangeInches();
        lastUltraDistL = ultrasonicL.getRangeInches();
	}

	
	public void updateUltrasonics() {
        ultraDistR = ultrasonicR.getRangeInches();
        ultraDistL = ultrasonicL.getRangeInches();
		if ((ultraDistR > lastUltraDistR - MAX_ULTRA_DIFF && ultraDistR < lastUltraDistR + MAX_ULTRA_DIFF) || ultraCountR > 5) {
			SmartDashboard.putNumber("UltrasonicR", ultraDistR);
			lastUltraDistR = ultraDistR;
			ultraCountR = 0;
		} else {
			ultraCountR++;
		}
		if ((ultraDistL > lastUltraDistL - MAX_ULTRA_DIFF && ultraDistL < lastUltraDistL + MAX_ULTRA_DIFF) || ultraCountL > 5) {
			SmartDashboard.putNumber("UltrasonicL", ultraDistL);
			lastUltraDistL = ultraDistL;
			ultraCountL = 0;
		} else {
			ultraCountL++;
		}
	}
	
	public double getDistR() {
		return lastUltraDistR;
	}
	
	public double getDistL() {
		return lastUltraDistL;
	}
}
