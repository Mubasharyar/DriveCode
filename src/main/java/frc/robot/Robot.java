/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  Stick joy = new Stick(0);
  Stick joy1 = new Stick(1);
  Elevator joyArm = new Elevator();
  Drive motors = new Drive(joy);
  double stickVal;

  @Override
  public void robotInit() {

  }


  @Override
  public void robotPeriodic() {
  }


  @Override
  public void autonomousInit() {

  }


  @Override
  public void autonomousPeriodic() {
 
  }


  @Override
  public void teleopPeriodic() {
    motors.call();
    joy.butt();
    joy1.butt();
    stickVal = joy1.getRawAxis(1);
/*Here you can define whether you want the button to run in the Position PID loop or the Magic PID Loop*/    
    if(joy1.getRawButton(1)) joyArm.posMod(0);
    else if(joy1.getRawButton(2)) joyArm.posMod(1);
    else if(joy1.getRawButton(3)) joyArm.posMod(2);
    else if(Math.abs(stickVal)>0.05 || !joyArm.posmod) joyArm.perMod(stickVal);

/////////////* assigns 0 value to the current position of the arm   *//////////
    if(joy1.getRawButton(8))joyArm.elevator.setSelectedSensorPosition(0);




    System.out.println("enc = "+joyArm.arm.getSelectedSensorPosition()+
    "   mode="+joyArm.posmod+"   error="+joyArm.arm.getClosedLoopError()
    );
  }


  @Override
  public void testPeriodic() {
    
    

    
  }
}
