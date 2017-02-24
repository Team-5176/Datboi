package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PewPew extends Subsystem {
	public boolean pewpewFlag;
	private Thread t;
	private final SpeedController pewMotor = RobotMap.pewpewMotor;
	private final PID pid = new PID();
	private boolean shooting = false;
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void reset() {
		if (!shooting) {
			pid.resetError();
			pid.setPID(SmartDashboard.getNumber("KP", 0.0), SmartDashboard.getNumber("KI", 0.0), SmartDashboard.getNumber("KD", 0.0));
			pid.setTarget(SmartDashboard.getNumber("Target", 0.0));
		}
	}

	public void startShooter() {
		reset();
		shooting = true;
		t = new Thread(() -> {
			while (shooting && pewpewFlag) {
				pewMotor.set(pid.updateMotor());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		});
		t.start();
		//pewMotor.set(SmartDashboard.getNumber("Shoot Speed", 0.0));
	}
	
	public void stopShooter() {
		shooting = false;
		pewMotor.set(0.0);
	}
	
	public class PID {
		private double p, i, d, target, error, lastError, totalError;
		private final Encoder pewEncoder = RobotMap.pewPewEncoder;
		
		public PID(double p, double i, double d, double targ) {
			this.p = p;
			this.i = i;
			this.d = d;
			target = targ;
			error = 0.0;
			lastError = 0.0;
			totalError = 0.0;
		}
		
		public PID(double p, double i, double d) {
			this(p, i, d, 0.0);
		}
		
		public PID() {
			this(0.0, 0.0, 0.0, 0.0);
		}
		
		public void setPID(double p, double i, double d) {
			this.p = p;
			this.i = i;
			this.d = d;
		}
		
		public void setTarget(double targ) {
			target = targ;
		}
		
		public void resetError() {
			error = 0.0;
			lastError = 0.0;
			totalError = 0.0;
		}
		
		public double updateMotor() {
			double rate = pewEncoder.getRate(), pError, iError, dError;
			error = (target - rate) / 10;
			totalError += error;
			if (totalError > 15000) {
				totalError = 15000;
			} else if (totalError < -15000) {
				totalError = -15000;
			}
			pError = p * error;
			iError = i * totalError;
			dError = d * (error - lastError);
			lastError = error;
			double setSpeed = pError + iError - dError;
			if (setSpeed > 1.0) {
				setSpeed = 1.0;
			} else if (setSpeed < -1.0) {
				setSpeed = -1.0;
			}
			SmartDashboard.putNumber("pError", pError);
			SmartDashboard.putNumber("iError", iError);
			SmartDashboard.putNumber("dError", dError);
			SmartDashboard.putNumber("Error", error);
			SmartDashboard.putNumber("Total Error", totalError);
			SmartDashboard.putNumber("Set Speed", setSpeed);
			return setSpeed;
		}
	}
}
