package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class InternalElevator extends SubsystemBase{
    private final SparkMax internalElevatorMotor = new SparkMax(19, MotorType.kBrushless);
    private final SparkMaxConfig internalElevatorMotorConfig = new SparkMaxConfig();
    private final SparkClosedLoopController internalElevatorMotorPIDController = internalElevatorMotor.getClosedLoopController();

    

    public InternalElevator() {
        internalElevatorMotorConfig
                .inverted(false)
                .idleMode(IdleMode.kBrake);
        internalElevatorMotorConfig.encoder
                .positionConversionFactor(2.0)
                .velocityConversionFactor(2.0);
        internalElevatorMotorConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .pid(0.001, 0.0, 0.0);

        internalElevatorMotor.configure(internalElevatorMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        internalElevatorMotor.getEncoder().setPosition(0);

        SmartDashboard.putBoolean("Elevator", false);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("ELEVATOR ENCODER", getPosition());
    }


    public void runUp() {
        internalElevatorMotor.set(0.3);

    }

    public void runDown() {
        internalElevatorMotor.set(-0.3);


    }

    public void stop() {
        internalElevatorMotor.set(0.02);
    }

    public double getPosition() {
        return internalElevatorMotor.getEncoder().getPosition();
    }

}

