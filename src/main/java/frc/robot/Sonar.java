package frc.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import edu.wpi.first.wpilibj.Joystick;
public class Sonar {

    AnalogInput ai;
    TalonFX br = new TalonFX(3);
    TalonFX fr = new TalonFX(4);
    TalonFX bl = new TalonFX(1);
    TalonFX fl = new TalonFX(2);
    double conversion = 1;
    //constructor
    Sonar(int pin){
        ai = new AnalogInput(pin);
    }
//methods
    public double getDistance(){
        return ai.getAverageVoltage()*conversion;
    }
}
