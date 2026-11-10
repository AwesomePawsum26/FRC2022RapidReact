// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.hornCommand;
import frc.robot.commands.shooterCommand;
import frc.robot.subsystems.driveSubsystem;
import frc.robot.subsystems.hornSubsystem;
import frc.robot.subsystems.shooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private hornSubsystem hornSubsystem = new hornSubsystem();
  private hornCommand hornCommand = new hornCommand(hornSubsystem);

  private driveSubsystem driveSubsystem = new driveSubsystem();
  private DriveCommand driveCommand = new DriveCommand(driveSubsystem);

  private shooterSubsystem shooterSubsystem = new shooterSubsystem();
  private shooterCommand shooterCommand = new shooterCommand(shooterSubsystem);
    
  private final CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    shooterSubsystem.setDefaultCommand(shooterCommand);
    driveSubsystem.setDefaultCommand(driveCommand);
    hornSubsystem.setDefaultCommand(hornCommand);

    configureBindings();
  }

  private void configureBindings() {
    controller.a().whileTrue(new shooterCommand(shooterSubsystem));
    controller.rightBumper().whileTrue(new hornCommand(hornSubsystem));
  }
  
}
