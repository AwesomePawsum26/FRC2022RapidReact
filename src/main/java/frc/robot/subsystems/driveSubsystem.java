// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class driveSubsystem extends SubsystemBase {
  private final VictorSP leftDrive;
  private final VictorSP rightDrive;

  private DifferentialDrive drive;


  public driveSubsystem() {
    leftDrive = new VictorSP(1);
    rightDrive = new VictorSP(0);

    leftDrive.setInverted(true);

    drive = new DifferentialDrive(leftDrive, rightDrive);
  }

  public void Drive(double xSpeed, double zRotation){
    drive.arcadeDrive(xSpeed, zRotation);
  }
}