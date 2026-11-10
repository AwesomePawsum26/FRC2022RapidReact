package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooterSubsystem;

public class shooterCommand extends Command {
    public final shooterSubsystem shooterSubsystem;
    
    public shooterCommand(shooterSubsystem shooterSubsystem){
        this.shooterSubsystem = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }


    @Override
    public void execute(){
        shooterSubsystem.Shoot();
    }

}
