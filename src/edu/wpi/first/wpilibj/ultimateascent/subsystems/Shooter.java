/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.ultimateascent.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.ultimateascent.RobotMap;
import edu.wpi.first.wpilibj.ultimateascent.commands.CommandBase;
import edu.wpi.first.wpilibj.ultimateascent.commands.shooter.ShooterMaintainPowerCmd;

/**
 *
 * @author root
 */
public class Shooter extends PIDSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // Motors
    private Victor shooterMotor = new Victor(RobotMap.SHOOTER_WHEEL);
    private Encoder shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_1, RobotMap.SHOOTER_ENCODER_2);
    
    
    // Constants
    private static final int kp = 0;
    private static final int ki = 0;
    private static final int kd = 0;
    
    // Variables
    private boolean enabled = false;
    private boolean camel = CommandBase.isOperatorCamel();
    
    public Shooter() {
        super(kp, ki, kd);
    }
    
    public void initDefaultCommand() {
        disable();
        setDefaultCommand(new ShooterMaintainPowerCmd());
    }
    
    public void setShooterPower(double newPower) {
        shooterMotor.set(newPower);
    }
    
    public double getShooterPower() {
        return shooterMotor.get();
    }

    public double getShooterRate() {
        return shooterEncoder.getRate();
    }
    
    public void setShooterEnabled(boolean newEnabled) {
        enabled = newEnabled;
    }
    
    public boolean isShooterEnabled() {
        return enabled;
    }
    
    protected double returnPIDInput() {
        return this.getShooterRate();
    }

    protected void usePIDOutput(double d) {
        setShooterPower(getShooterPower() + d);
    }
}
