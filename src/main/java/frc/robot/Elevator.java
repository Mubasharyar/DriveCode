package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;


public class Elevator{
    TalonSRX elevator = new TalonSRX(0);
    double armValue;
    boolean posmod=false;
    

    public Elevator(){
        elevator.configFactoryDefault();
        elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,20);
        elevator.setSensorPhase(false);
        elevator.setInverted(false);       
        elevator.setNeutralMode(NeutralMode.Brake);
        elevator.config_kP(0,15);
        elevator.config_kF(0, 0);
        elevator.config_kD(0, 0);
        elevator.configClosedLoopPeakOutput(0, .75, 20);
        elevator.configMotionCruiseVelocity(500, 20);
        elevator.configMotionAcceleration(1000, 20);
    }

/*               setting arm movements according to the buttons pressed               */
/*                             Position PID loop                                      */
    
    public void posMod(int armPos){
        posmod=true;    
        if(armPos==0){
            elevator.set(ControlMode.Position, 0);
        }
        else if(armPos==1){
            elevator.set(ControlMode.Position, 100);
        }
        else if(armPos==2){
            elevator.set(ControlMode.Position, 200);
        }
    }
/*                             Motion Magic PID loop                                  */
        public void Magic(int armPos){
            posmod=true;    
            if(armPos==0){
                elevator.set(ControlMode.MotionMagic, 0);
            }
            else if(armPos==1){
                elevator.set(ControlMode.MotionMagic, 100);
            }
            else if(armPos==2){
                elevator.set(ControlMode.MotionMagic, 200);
            }
/*                setting arm movements according to the raw axis value      */
    }
    public void perMod(double stickVal){
        posmod=false;
        elevator.set(ControlMode.PercentOutput, stickVal);
        ////nmae///

        }
    
}