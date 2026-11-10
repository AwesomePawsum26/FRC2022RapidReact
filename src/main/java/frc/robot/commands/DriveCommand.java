// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.driveSubsystem;

public class DriveCommand extends Command{
    public final driveSubsystem driveSubsystem;

    public final XboxController controller;


    public DriveCommand(driveSubsystem driveSubsystem){
        this.driveSubsystem = driveSubsystem;

        addRequirements(driveSubsystem);

        controller = new XboxController(0);
    }

    @Override
    public void execute(){
        driveSubsystem.Drive(controller.getLeftY(),controller.getRightX());
    }

}
