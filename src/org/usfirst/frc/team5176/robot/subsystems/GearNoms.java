package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearNoms extends Subsystem {
	public boolean gearFlag;
	private Thread t;
	private final SpeedController unleashGear = RobotMap.unleashGear;
	private final DigitalInput limitOpen = RobotMap.limitSwitchOpen;
	private final DigitalInput limitClose = RobotMap.limitSwitchClose;
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void openNoms() {
		t = new Thread(() -> {
			while (limitOpen.get() && gearFlag) {
				unleashGear.set(-0.5);
			}
			unleashGear.set(0.0);
		});
		t.start();
	}
	
	public void closeNoms() {
		t = new Thread(() -> {
			while (limitClose.get() && gearFlag) {
				unleashGear.set(0.3);
			}
			unleashGear.set(0.0);
		});
		t.start();
	}
}
