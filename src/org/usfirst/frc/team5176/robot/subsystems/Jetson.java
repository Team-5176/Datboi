package org.usfirst.frc.team5176.robot.subsystems;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Jetson extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public boolean readyToUpdate;
	public ArrayList<Stuff> stuff;
	boolean started;
	Scanner scan;
	BufferedOutputStream stream;
    public Jetson() throws Exception {
    	started = false;
    	stuff = new ArrayList<>();
    	
    	new Thread(() -> {
			try {
				Process p = Runtime.getRuntime().exec("ssh -t -t -p 5803 ubuntu@10.51.76.69");
				scan = new Scanner(p.getInputStream());
				stream = new BufferedOutputStream(p.getOutputStream());
				//Scanner errorStream = new Scanner(p.getErrorStream());
				while(!started) {
					/*if (errorStream.hasNext()) {
						DriverStation.reportWarning(errorStream.nextLine(), false);
					}*/
					if (scan.hasNext()) {
						String str = scan.nextLine();
						if(str.contains("Last login")) {
							stream.write("~/shiet/doit.sh\n".getBytes());
							stream.flush();
							continue;
						}
						if(str.contains("O shit waddup!")) {
							DriverStation.reportWarning("Jetson code started!", false);
							readyToUpdate = true;
							started = true;
							continue;
						}
					}
				}
			} catch(Exception e) {
				DriverStation.reportError(e.getMessage(), false);
			}
		}).start();
    }
    
    public void updateCamera(int cam) {
    	if (readyToUpdate) {
    		readyToUpdate = false;
	    	new Thread(() -> {
	    		boolean dataFinished = false;
	    		stuff = new ArrayList<Stuff>();
	    		try {
	    			stream.write(String.valueOf(cam).getBytes());
	    			stream.flush();
	    			while (!dataFinished) {
	    				if (scan.hasNext()) {
	    					String str = scan.nextLine();
	    					if (str.contains("Data finished sending.")) {
	    						readyToUpdate = true;
	    						dataFinished = false;
	    					} else {
	    						String[] args = str.trim().split(":");
	    						stuff.add(new Stuff(Double.valueOf(args[0]),
	    											Double.valueOf(args[1]),
	    											Double.valueOf(args[2])));
	    					}
	    				}
	    			}
	    		} catch(Exception e) {
	    			
	    		}
	    	});
    	}
    }
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
    public static class Stuff {
    	double centerX, centerY, area;
    	
    	public Stuff(double cx, double cy, double a) {
    		centerX = cx;
    		centerY = cy;
    		area = a;
    	}
    	
		public double getCenterX() {
			return centerX;
		}
		
		public void setCenterX(int centerX) {
			this.centerX = centerX;
		}
		
		public double getCenterY() {
			return centerY;
		}
		
		public void setCenterY(int centerY) {
			this.centerY = centerY;
		}
		
		public double getArea() {
			return area;
		}
		
		public void setArea(int area) {
			this.area = area;
		}
    }
}
