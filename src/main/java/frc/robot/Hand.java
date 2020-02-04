package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hand {

    WPI_TalonSRX armL = new WPI_TalonSRX(0);
    WPI_TalonSRX intake = new WPI_TalonSRX(8);

    public static final int kTimeoutMs = 30;
    private static final ControlMode PercentOutput = ControlMode.PercentOutput;
    private static final ControlMode Magic = ControlMode.MotionMagic;
    double kP =10.0;
    double kI=0;
    double kD=0;
    double kF=0.2;
    double kIzone=0; 
    double kPeakOutput=1.0;
    int vel = 1500;
    int acc = 600;
    double testForward;


    public Hand(){
    //    armL.configFactoryDefault();
        armL.configFactoryDefault();

        armL.setNeutralMode(NeutralMode.Brake);
        intake.setNeutralMode(NeutralMode.Brake);

        armL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,10);
        armL.setSensorPhase(false);
        intake.setSensorPhase(true);
        armL.setInverted(false);
        intake.setInverted(true);

        /* Set relevant frame periods to be at least as fast as periodic rate */
		armL.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		armL.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,kTimeoutMs);

		/* Set the peak and nominal outputs */
		armL.configNominalOutputForward(0, kTimeoutMs);  // slot 0
		armL.configNominalOutputReverse(0, kTimeoutMs);  // slot 0
		armL.configPeakOutputForward(1, kTimeoutMs);  // slot 0
        armL.configPeakOutputReverse(-1,kTimeoutMs);  // slot 0
        intake.configNominalOutputForward(0, kTimeoutMs);  // slot 0
		intake.configNominalOutputReverse(0, kTimeoutMs);  // slot 0
		intake.configPeakOutputForward(1, kTimeoutMs);  // slot 0
		intake.configPeakOutputReverse(-1,kTimeoutMs);  // slot 0

		/* Set Motion Magic gains in slot0 - see documentation */
        armL.selectProfileSlot(0, 0);  // gains slot, loop 0
        setGains();

        armL.setSelectedSensorPosition(0, 0, kTimeoutMs);  /* Zero the sensor */

        printGains();
        armL.set(PercentOutput,0);        
        intake.set(PercentOutput,0);

    }

    public void driveManual(double forward){
    
        armL.set(PercentOutput, forward);

    }

    

    public void magic(int preset){
        int target = 0;
        if (preset == 1) target = 0;
        if (preset == 2) target = 330; 
        if (preset == 3) target = 230;
        if (preset == 4) target =435;   
        armL.set(Magic, target);
        

    }


    public void driveIntake(int direction){
        intake.set(0.75*direction);
    }



    public void printGains(){
//        Shuffleboard.selectTab("Gains");	
        SmartDashboard.putNumber("kP_arm",kP);
        SmartDashboard.putNumber("kI_arm",kI);
        SmartDashboard.putNumber("kIz_arm",kIzone);
        SmartDashboard.putNumber("kD_arm",kD);
        SmartDashboard.putNumber("kF_arm",kF);
        SmartDashboard.putNumber("vel_arm", vel);
        SmartDashboard.putNumber("acc_arm",acc);	
    }


    public void getGains(){
//        Shuffleboard.selectTab("Gains");	
        kP = SmartDashboard.getNumber("kP_arm",0);
        kI = SmartDashboard.getNumber("kI_arm",0);
        kIzone = SmartDashboard.getNumber("kIz_arm",0);
        kD = SmartDashboard.getNumber("kD_arm",0);
        kF = SmartDashboard.getNumber("kF_arm",0);
        vel = (int) SmartDashboard.getNumber("vel_arm", 0);
        acc = (int) SmartDashboard.getNumber("acc_arm",0);	
    
    }

    private void setGains(){
        armL.config_kF(0, kF, kTimeoutMs);
		armL.config_kP(0, kP, kTimeoutMs);
		armL.config_kI(0, kI,kTimeoutMs);
        armL.config_kD(0, kD, kTimeoutMs);
    /* Set acceleration and vcruise velocity - see documentation */
		armL.configMotionCruiseVelocity(vel, kTimeoutMs);
		armL.configMotionAcceleration(acc, kTimeoutMs);

    }

    public void getArmData(){
//        Shuffleboard.selectTab("System");	
        SmartDashboard.putNumber("arm_Current",armL.getOutputCurrent());
        SmartDashboard.putNumber("arm_Position",armL.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("arm_Error",armL.getClosedLoopError(0));
        SmartDashboard.putString("DB/String 9",String.valueOf(armL.getControlMode()));
        SmartDashboard.putNumber("arm_voltage", armL.getMotorOutputVoltage());
    }

    public void zeroEncoder(){
        armL.getSensorCollection().setQuadraturePosition(0, 20);
    }
    
}