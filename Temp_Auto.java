package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Temp_Auto")
public class Temp_Auto extends LinearOpMode {
    //drivetrain motors
    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM)
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    // - drivetrain power variables
    // Setting the power of the drivetrain motor variables.

    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    // power determined by the x and y values of the sticks.
    double Yvalue1=0.0;
    double Xvalue1=0.0;
    double Xvalue2 = 0.0;

    // Intake motor
    // - motor type and variable name
    DcMotor Intake; //This is using an AndyMark Neverest 40 Motor
    // Flipper motor
    // lodingservo is a smart servo used for our flipper on the robot
    Servo loadingservo;
    // Fly wheel motors
    DcMotor Flywheel1;
    DcMotor Flywheel2;
    //both Flywheel1 and Flywheel2 are neverrest 40's


    // additional variables used
    double current_power;
    boolean GOOD = true;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // harware mapping
        // - drivetrain
        // Hardware mapping the Drivetrain's 4 main drive motors,
        // they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR = hardwareMap.dcMotor.get("rf");
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL = hardwareMap.dcMotor.get("lb");
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR = hardwareMap.dcMotor.get("rb");
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // - intake
        Intake = hardwareMap.dcMotor.get("intake");
        // - flipper
        loadingservo = hardwareMap.servo.get("flipper");
        // - flywheel
        Flywheel1 = hardwareMap.dcMotor.get("flyl");
        Flywheel2 = hardwareMap.dcMotor.get("flyr");
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);


//      Reversing 2 of the motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        // Telemetry that ensures the robot is ready to run the program.
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // while (opModeIsActive()) {
        
            mcdrive(120);

            mcdrive(-54);

            //  strafe(-30);

            Flywheel1.setPower(1.00);
            Flywheel2.setPower(1.00);
            loadingservo.setPosition(0.00);
            wait(300);
            loadingservo.setPosition(1.00);
            wait(200);
            loadingservo.setPosition(0.00);
            wait(300);
            loadingservo.setPosition(1.00);
            wait(200);
            loadingservo.setPosition(0.00);
            wait(300);
            loadingservo.setPosition(1.00);
            Flywheel1.setPower(0.00);
            Flywheel2.setPower(0.00);

            // strafe(+12);

            mcdrive(-66);

            // mcdrive(1);


            // This Program uses: Gobilda 5202 435 RPM motors.
            // Which has 383.6 ticks per/Revolution. Our wheel are 4" in diameter.
            // Circumference is 12.5663706144".
            // Approx. 30.5251030464" Ticks Per Inch.
            }

            public void mcdrive(double inches) {

                /*
                FL_power = (Yvalue1 - Xvalue1);
                FR_power = (Yvalue1 + Xvalue1);
                BL_power = (Yvalue1 - Xvalue1);
                BR_power = (Yvalue1 + Xvalue1);
                */

                float distance = (((float) inches) * (float) 30.5251030464);
                int distance_int = Math.round(distance);

                int target_position_FL;
                int target_position_FR;
                int target_position_BL;
                int target_position_BR;

                target_position_FL = (FL.getCurrentPosition() + distance_int);
                target_position_FR = (FR.getCurrentPosition() + distance_int);
                target_position_BL = (BL.getCurrentPosition() + distance_int);
                target_position_BR = (BR.getCurrentPosition() + distance_int);

                FL.setPower(1.00);
                FR.setPower(1.00);
                BL.setPower(1.00);
                BR.setPower(1.00);

                FL.setTargetPosition(target_position_FL);
                FR.setTargetPosition(target_position_FR);
                BL.setTargetPosition(target_position_BL);
                BR.setTargetPosition(target_position_BR);

                FL.setPower(0.00);
                FR.setPower(0.00);
                BL.setPower(0.00);
                BR.setPower(0.00);

                while(FL.isBusy() || FR.isBusy() || BL.isBusy() || BR.isBusy());


            }

            public void turn(double inches) {

                float distance = (((float) inches) * (float) 30.5251030464);
                int distance_int = Math.round(distance);

                int target_position_FL;
                int target_position_FR;
                int target_position_BL;
                int target_position_BR;

                target_position_FL = (FL.getCurrentPosition() + distance_int);
                target_position_FR = (FR.getCurrentPosition() + distance_int);
                target_position_BL = (BL.getCurrentPosition() + distance_int);
                target_position_BR = (BR.getCurrentPosition() + distance_int);

                FL.setPower(1.00);
                FR.setPower(1.00);
                BL.setPower(1.00);
                BR.setPower(1.00);

                FL.setTargetPosition(target_position_FL);
                FR.setTargetPosition(target_position_FR);
                BL.setTargetPosition(target_position_BL);
                BR.setTargetPosition(target_position_BR);

                FL.setPower(0.00);
                FR.setPower(0.00);
                BL.setPower(0.00);
                BR.setPower(0.00);

                while(FL.isBusy() || FR.isBusy() || BL.isBusy() || BR.isBusy());










            }


            /*public void mcstrafe(double inches) {


                        FL_power = (Yvalue1 - Xvalue1);
                        FR_power = (Yvalue1 + Xvalue1);
                        BL_power = (Yvalue1 - Xvalue1);
                        BR_power = (Yvalue1 + Xvalue1);


                float distance = (((float) inches) * (float) 30.5251030464);
                int distance_int = Math.round(distance);

                int target_position_FL;
                int target_position_FR;
                int target_position_BL;
                int target_position_BR;

                target_position_FL = (FL.getCurrentPosition() + distance_int);
                target_position_FR = (FR.getCurrentPosition() + distance_int);
                target_position_BL = (BL.getCurrentPosition() + distance_int);
                target_position_BR = (BR.getCurrentPosition() + distance_int);

                FL.setPower(1.00);
                FR.setPower(1.00);
                BL.setPower(1.00);
                BR.setPower(1.00);

                FL.setTargetPosition(target_position_FL);
                FR.setTargetPosition(target_position_FR);
                BL.setTargetPosition(target_position_BL);
                BR.setTargetPosition(target_position_BR);

                FL.setPower(0.00);
                FR.setPower(0.00);
                BL.setPower(0.00);
                BR.setPower(0.00);

                while(FL.isBusy() || FR.isBusy() || BL.isBusy() || BR.isBusy());


            }
            */


}