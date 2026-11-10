package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooterSubsystem;

public class shooterCommand extends Command {
    public final shooterSubsystem shooterSubsustem;
    
    public shooterCommand(shooterSubsystem shooterSubsystem){
        this.shooterSubsustem = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute(){
        shooterSubsustem.Shoot();
    }
}
