package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;


public class Arm{
    TalonSRX arm = new TalonSRX(0);
    double armValue;
    boolean posmod=false;
    

    public Arm(){
        arm.configFactoryDefault();
        arm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,20);
        arm.setSensorPhase(false);
        arm .setInverted(false);       
        arm.setNeutralMode(NeutralMode.Brake);
        arm.config_kP(0,15);
        arm.config_kF(0, 0);
        arm.config_kD(0, 0);
        arm.configClosedLoopPeakOutput(0, .75, 20);
        arm.configMotionCruiseVelocity(500, 20);
        arm.configMotionAcceleration(1000, 20);
    }

/*               setting arm movements according to the buttons pressed               */
/*                             Position PID loop                                      */
    
    public void posMod(int armPos){
        posmod=true;    
        if(armPos==0){
            arm.set(ControlMode.Position, 0);
        }
        else if(armPos==1){
            arm.set(ControlMode.Position, 100);
        }
        else if(armPos==2){
            arm.set(ControlMode.Position, 200);
        }
    }
/*                             Motion Magic PID loop                                  */
        public void Magic(int armPos){
            posmod=true;    
            if(armPos==0){
                arm.set(ControlMode.MotionMagic, 0);
            }
            else if(armPos==1){
                arm.set(ControlMode.MotionMagic, 100);
            }
            else if(armPos==2){
                arm.set(ControlMode.MotionMagic, 200);
            }
/*                setting arm movements according to the raw axis value      */
    }
    public void perMod(double stickVal){
        posmod=false;
        arm.set(ControlMode.PercentOutput, stickVal);
        }
    
}