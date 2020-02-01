
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
    public static int forwardAxis = 1;
    public static int turnAxis = 4;
    public static int armAxis = 1;
    public static double kP = 0;
	public static double kI = 0;
	public static double kD = 0;
    public static double kIz = 0;
    public static double kF = 1;
    
    public Constants(){

    }

    public void getvalusr(){
        kP = SmartDashboard.getNumber("kP ", 0);
        kI = SmartDashboard.getNumber("kI ", 0);
        kD = SmartDashboard.getNumber("kD ", 0);
        kIz = SmartDashboard.getNumber("kIz ", 0);
        kF = SmartDashboard.getNumber("kF ", 1);

        



    }

}