package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;


public class shooterSubsystem extends SubsystemBase {
    private final SparkMax flywheel;
    private final SparkMax hood;
    private final SparkMax indexer;
    private SparkClosedLoopController hoodLoopController;
    private SparkMaxConfig hoodConfig;

    public shooterSubsystem() {
        flywheel = new SparkMax(7, MotorType.kBrushless);
        hood = new SparkMax(8, MotorType.kBrushless);
        indexer = new SparkMax(2, MotorType.kBrushless);
        
        hoodConfig = new SparkMaxConfig();
        hoodConfig.closedLoop.pid(0, 0, 0);
        hoodConfig.closedLoop.outputRange(0.5, 0.5);

        hood.configure(hoodConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        hoodLoopController = hood.getClosedLoopController();
        
    }

    public void Shoot(){
        flywheel.setVoltage(-5);
        indexer.setVoltage(-5);

    }
    public void stopShooter(){
        flywheel.setVoltage(0);
        indexer.setVoltage(0);
        hood.setVoltage(0);
    }
    public void setHoodPosition(double position){
        hoodLoopController.setSetpoint(position, ControlType.kPosition);
    }
    public double getHoodPosition(){
        return hood.getEncoder().getPosition();
    }
}
