package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class shooterSubsystem extends SubsystemBase {
    private final SparkMax flywheel;
    private final SparkMax hood;
    private final SparkMax indexer;

    public shooterSubsystem() {
        flywheel = new SparkMax(7, MotorType.kBrushless);
        hood = new SparkMax(8, MotorType.kBrushless);
        indexer = new SparkMax(2, MotorType.kBrushless);

    }

    public void Shoot(){
        flywheel.setVoltage(2);
        indexer.setVoltage(2);
    }
}
