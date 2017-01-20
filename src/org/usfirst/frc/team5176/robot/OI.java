package org.usfirst.frc.team5176.robot;

import org.usfirst.frc.team5176.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick pilotJoystick;
	public Joystick coJoystick;
	
	public JoystickButton shootButton;
	public JoystickButton climbButton;
	public JoystickButton descendButton;
	
	public OI() {
        pilotJoystick = new Joystick(0);
        coJoystick = new Joystick(1);
        
        shootButton = new JoystickButton(coJoystick, 1);
        shootButton.whenPressed(new ShootDeezBallz());
        shootButton.whenReleased(new KeepDeezBallz());
        
        climbButton = new JoystickButton(coJoystick, 6);
        climbButton.whenPressed(new ClimbRope());
        
        descendButton = new JoystickButton(coJoystick, 7);
        descendButton.whenPressed(new DescendRope());
        
        SmartDashboard.putData("Arcade Drive", new ArcadeDrive());
    }

    public Joystick getPilotJoystick() {
        return pilotJoystick;
    }
    
    public Joystick getCoJoystick() {
    	return coJoystick;
    }
}
