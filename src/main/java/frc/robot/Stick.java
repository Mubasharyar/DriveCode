
package frc.robot;
import java.lang.reflect.Array;
import java.util.Arrays;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
public class Stick extends Joystick{
    //Drive motors = new Drive();
    double left_input ;
    double right_input;
    boolean [] toggle = new boolean [10];
    boolean [] butt = new boolean[10];
    boolean [] buttp = new boolean[10];
    double deltaX, deltaXMax, x, y;;
    double xPrev = 0;
//    int i = 0;

 
    public Stick(int port) {
        super(port);
 
 
    }
    public void readStuff() {



    butt();
    x = -this.getRawAxis(1);
    deltaXMax = 0.015;
    deltaX = x - xPrev;
    if (this.getRawButton(1)){ y = x;}
    else{

    if (deltaX > deltaXMax && x > 0){
        deltaX = deltaXMax;
    }
    else if (deltaX < -deltaXMax && x < 0) {
        deltaX = -deltaXMax;
    }
    else if(deltaX == 0){
        y = x;
    }
    y = xPrev + deltaX;
    xPrev = y;
}

     
    double forward = y;
    forward = (forward*forward*Math.signum(forward));
    double sides = this.getRawAxis(Constants.turnAxis);
    // decrease max speed turn
    sides = sides*sides*Math.signum(sides)*.5;
    
    left_input = forward + sides;
    right_input = forward - sides; 

    }
    public void butt(){
    
    
    int i = 0;
    
        //System.arraycopy(butt,0,buttp,0,butt.length);

        while(i<10)
        {
            if(this.getRawButton(i+1)==true){
                butt[i]= true;
            }
            else{
                butt[i] = false ;

            }
            
            i++;
    
        
        }

        for (int a = 0; a < butt.length ; a++){
         //   for (int b = 0; b <= buttp.length; b++){
             
                if (butt[a] && !buttp[a]){
                    toggle[a] = !toggle[a];


                }
                else{
                    toggle[a]= toggle[a];
                }
          //  }
            //System.out.println(buttp[a]);
           


        }
    /*    System.out.println(Arrays.toString(toggle));*/
        SmartDashboard.putBoolean("toggle", toggle[2]);
        System.arraycopy(butt,0,buttp,0,butt.length);
        
    }
}

