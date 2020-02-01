package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.*;
//import edu.wpi.first.wpilibj.AnalogInput;
public class Drive {
    //AnalogInput ai;
    Stick stick;
    TalonSRX br = new TalonSRX(3);
    TalonSRX fr = new TalonSRX(4);
    TalonSRX bl = new TalonSRX(1);
    TalonSRX fl = new TalonSRX(2);
    
    double sensorPositionBL, sensorPositionBR;
    
      


public Drive(Stick _stick){
    stick=_stick;
    initialising();
    fl.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,20);
    

    fr.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,20);   
    
    

}

public void set_stuff() {

//giving them action later
stick.readStuff();
bl.set(ControlMode.PercentOutput, stick.left_input);

br.set(ControlMode.PercentOutput, stick.right_input);

}

public void initialising() {
    br.setInverted(true);
    fr.setInverted(true);

    bl.setSensorPhase(true);
    fr.setSensorPhase(true);

    fr.follow(br);
    fl.follow(bl);

    br.selectProfileSlot(0,0);
    bl.selectProfileSlot(0,0);

    
  //  double getVelocity = bl.getSelectedSensorVelocity()*2*Math.PI*60;
 //   br.getSelectedSensorVelocity();
////////////////////////////////////////////////////////////////////// 
    bl.config_kP(0, Constants.kP);

    


}

public void call(){

    set_stuff();
    sensorPositionBR = fr.getSelectedSensorPosition();
    sensorPositionBL = fl.getSelectedSensorPosition();

    SmartDashboard.putNumber("Pos L", fl.getSelectedSensorPosition());
    SmartDashboard.putNumber("Pos R ",fr.getSelectedSensorPosition());
}
}