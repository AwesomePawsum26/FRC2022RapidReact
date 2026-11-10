package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hornSubsystem;

public class hornCommand extends Command{
    public final hornSubsystem hornSubsystem;

    public hornCommand(hornSubsystem hornSubsystem){
        this.hornSubsystem = hornSubsystem;

        addRequirements(hornSubsystem);
    }

    @Override 
    public void execute(){
        hornSubsystem.honk();
    }
}
