package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Arm{
    CANSparkMax arm = new CANSparkMax(0, MotorType.kBrushless);
    CANPIDController pidController;
    double armValue;
    boolean posmod=false;
    double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    

    public Arm(){
        arm.restoreFactoryDefaults();
        /*arm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,20);
        arm.setSensorPhase(false);
        arm .setInverted(false);       
        arm.setNeutralMode(NeutralMode.Brake);
        */
        kP = 0;

        pidController = arm.getPIDController();
        pidController.setP(kP);
        pidController.setFF(kFF);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);
   
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
        ////nmae///

        }
    
}