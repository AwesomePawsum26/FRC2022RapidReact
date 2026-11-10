package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class hornSubsystem extends SubsystemBase {
    private final VictorSP horn;

    public hornSubsystem(){
        horn = new VictorSP(2);
    }
    public void honk(){
        horn.set(1);
    }
    public void stopHorn(){
        horn.set(0);
    }
}
