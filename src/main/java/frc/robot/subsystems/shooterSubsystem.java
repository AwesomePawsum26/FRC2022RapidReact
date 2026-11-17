package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;


public class shooterSubsystem extends SubsystemBase {
    //initialize motors
    private final SparkMax flywheel;
    private final SparkMax hood;
    private final SparkMax indexer;
    
    //initialize motorconfigs and hood loop
    private SparkMaxConfig flywheelConfig;
    private SparkMaxConfig indexerConfig;
    private SparkMaxConfig hoodConfig;
    private SparkClosedLoopController hoodLoopController;
    
    //initialize publisher network tables
    private DoublePublisher shooterSpeed;
    private DoublePublisher hoodPosition;

    //initialize entry network tables
    private DoubleEntry changeShooterSpeed;
    public DoubleEntry changeHoodPosition;

    //initialize PID network tables
    private DoubleEntry kP;
    private DoubleEntry kI;
    private DoubleEntry kD;

    public shooterSubsystem() {
        //create network tables
        NetworkTableInstance shooterTable = NetworkTableInstance.getDefault();
        NetworkTable shooterTable2 = shooterTable.getTable("Shooter");
        NetworkTable hoodTable = shooterTable.getTable("Hood");
        
        //create variables and assign them to specific network tables and give them inital values
        shooterSpeed = shooterTable2.getDoubleTopic("Shooter Speed").publish();
        changeShooterSpeed = shooterTable2.getDoubleTopic("Change Shooter Speed").getEntry(0);
        hoodPosition = hoodTable.getDoubleTopic("Hood Position").publish();
        changeHoodPosition = hoodTable.getDoubleTopic("Change Hood Position").getEntry(0);
        
        kP = shooterTable2.getDoubleTopic("kP").getEntry(0);
        kI = shooterTable2.getDoubleTopic("kI").getEntry(0);
        kD = shooterTable2.getDoubleTopic("kD").getEntry(0);

        //do .set() for all of em to make an acutal default value b/c wpilib's weird
        shooterSpeed.set(0);
        changeShooterSpeed.set(0);
        hoodPosition.set(0);
        changeHoodPosition.set(0);
        kP.set(0);
        kI.set(0);
        kD.set(0);

        //define motor and motorconfig for flwheel
        flywheel = new SparkMax(7, MotorType.kBrushless);
        
        flywheelConfig = new SparkMaxConfig();
        flywheelConfig.inverted(true);
        flywheel.configure(flywheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //define motors and motorconfig for hood, set PID, and make idle brake
        hood = new SparkMax(8, MotorType.kBrushless);
        
        hoodConfig = new SparkMaxConfig();
        hoodConfig.closedLoop.pid(kP.get(), kI.get(), kD.get());
        hoodConfig.closedLoop.outputRange(-0.5, 0.5);
        hoodConfig.idleMode(IdleMode.kBrake);

        hood.configure(hoodConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        hoodLoopController = hood.getClosedLoopController();

        //define motor for indexer and set config
        indexer = new SparkMax(2, MotorType.kBrushless);

        indexerConfig = new SparkMaxConfig();
        indexerConfig.inverted(true);
        indexer.configure(indexerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    //shoot command, makes flywheel, hood, and indexer run at defined values
    public void Shoot(){
        //currently both set to 5
        flywheel.setVoltage(changeShooterSpeed.get());
        setHoodPosition();
        indexer.setVoltage(5);
    }
    //stop command, makes every motor be 0 voltage when shoot not running 
    public void stopShooter(){
        flywheel.setVoltage(0);
        hood.setVoltage(0);
        indexer.setVoltage(0);
    }
    //causes the hood to go to the position specified by changeHoodPosition network table
    public void setHoodPosition(){
        hoodLoopController.setSetpoint(changeHoodPosition.get(), ControlType.kPosition);
    }
    //gets hood position
    public double getHoodPosition(){
        return hood.getEncoder().getPosition();
    }
    //sets published network tables to constantly get flywheel and hood values
    @Override
    public void periodic(){
        shooterSpeed.set(flywheel.getEncoder().getVelocity());
        hoodPosition.set(getHoodPosition());
    }
}