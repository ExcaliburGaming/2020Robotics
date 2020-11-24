package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="teleop_program")
public class teleop_program extends LinearOpMode {

    // Declaring the Motors used in this program.
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotor Motor;
    DcMotor Flywheel1;
    DcMotor Flywheel2;


    // Declaring the buttons used throughout this program.
    boolean buttonY;
    boolean buttonB;
    boolean buttonA;
    boolean buttonX;
    boolean dpad_up;
    boolean dpad_down;
    boolean RightBumper;
    boolean RightTrigger;
    boolean LeftTrigger;
    boolean LeftBumper;
    boolean dpad_right;
    boolean dpad_left;


    // Declaring extra values, buttons, & servos.
    double current_power;
    Servo loadingservo;
    boolean GOOD = true;

    int GREAT = 0;

    double FL_float = 0;
    double FR_float = 0;
    double BL_float = 0;
    double BR_float = 0;

    //DcMotorController.DeviceMode DevMode;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // Hardware mapping the two Flywheel Motors.
        Flywheel1 = hardwareMap.dcMotor.get("Left");
        Flywheel2 = hardwareMap.dcMotor.get("Right");

        // Making sure the Right Flywheel Motor is reversed to make sure they both run in the correct direction.
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);


        // Hardware Mapping the 4 Drive Motors.
        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

        // Reversing the two motors on one side to make sure they all run in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);


//        GrabPlatform1.setPosition(1);
//        GrabPlatform2.setPosition(0);
//        OpenClaw.setPosition(RELEASE);

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        while (opModeIsActive()) {


            // Declaring the buttons on the Controller needed to run the Program.
            buttonA = gamepad1.a;
            buttonB = gamepad1.b;
            buttonX = gamepad1.x;
            buttonY = gamepad1.y;
            RightTrigger = gamepad1.right_bumper;
            LeftBumper = gamepad1.left_bumper;
            RightBumper = gamepad1.right_bumper;
            LeftTrigger = gamepad1.left_bumper;
            dpad_up = gamepad1.dpad_up;
            dpad_down = gamepad1.dpad_down;
            dpad_left = gamepad1.dpad_left;
            dpad_right = gamepad1.dpad_right;

            // Multiple If Statements intended to stop the Drive Motors when the 'X' button is pushed on the Controller.
            if (buttonX = true) {
                Motor.setPower(0.80);
            }
            if (buttonX = false) {
                Motor.setPower(0.00);
            }





            // The code below is used to disengage/fully stop the Flywheel's Motors by pushing the 'B' button on the Controller.
            if (buttonB) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.00);
                Flywheel2.setPower(0.00);
            }


            // The code below is used to put the Flywheel Motor(s) at 100% Throttle IN REVERSE when the 'A' button is pushed on the Controller.
            if (buttonA) {
                telemetry.addData("Flywheel Motors Engaged in Reverse. ", " ");
                telemetry.update();
                Flywheel1.setPower(-1.00);
                Flywheel2.setPower(-1.00);
            }


            // Engaging all the Flywheel Motor(s) at 100% throttle when the 'Y' button is pushed. Can be configured to max out at a different Value.
            if (buttonY) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(1.00);
                Flywheel2.setPower(1.00);
            }


            // Closes the Wobble Goal Grabber.
            if (LeftBumper) {
                loadingservo.setPosition(0.00);
                telemetry.addData("Closing.", " ");
                telemetry.update();
            }

            // Opens the Wobble Goal Grabber.
            if (RightBumper) {
                loadingservo.setPosition(1.00);
                telemetry.addData("Opening.", " ");
                telemetry.update();
            }


            // Sets the Flywheel Motors to the power level needed to launch a Ring into the Top Goal at 5 Feet away from the target.
            if (dpad_up) {

                telemetry.addData("Motors set to Top Goal.", " ,");
                telemetry.update();

                Flywheel1.setPower(1.00);
                Flywheel2.setPower(1.00);
            }


            // Sets the Flywheel Motors to the power level needed to launch a ring into the Middle Goal at 5 Feet away from the target.
            if (dpad_right) {

                telemetry.addData("Motors set to Middle Goal.", " ,");
                telemetry.update();

                Flywheel1.setPower(0.60);
                Flywheel2.setPower(0.60);
            }

            // Sets the Flywheel Motors to the power level needed to launch a ring into the Bottom Goal at 5 Feet away from the target.
            if (dpad_down) {

                telemetry.addData("Motors set to Bottom Goal.", " ,");
                telemetry.update();

                Flywheel1.setPower(0.30);
                Flywheel2.setPower(0.30);
            }




            /* This is a tool I coded to help us figure out what power levels correspond to which goals, the code below this line increases the power by 0.05%, but can be configured
            to give any percent of power.
            It also caps out at 100% power, and doesn't add any value to it after that.
            */
            if (buttonX && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);

                current_power = (current_power + 0.05);

                telemetry.addData("Calculated Power", current_power);

                current_power = Range.clip(current_power, -1, 1);

                telemetry.addData("Clipped Power", current_power);

                telemetry.addData("Motor 2 Power", Flywheel2.getPower());
                telemetry.update();

                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
                GOOD = true;
                sleep(200);
            }




            /* This is a tool I coded to help us figure out what power levels correspond to which goals, the code below this line decreases the power by 0.05%, but can be configured
            to take any percent of power.
            It also caps out at -100% power, or full reverse, and doesn't take any value from it after that.
            */
            if (buttonA && GOOD) {
                GOOD = false;
                telemetry.addData("Current Power Level Is: ", current_power);

                current_power = (current_power - 0.05);

                telemetry.addData("Calculated Power", current_power);

                current_power = Range.clip(current_power, -1, 1);

                telemetry.addData("clipped power", current_power);
                telemetry.update();

                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
                GOOD = true;
                sleep(200);
            }




            // Temporarily unused Void Statement, will be implemented eventually.
            /*if (stopIsPushed) {
                FL.setPower(0.00);
                FR.setPower(0.00);
                BL.setPower(0.00);
                BR.setPower(0.00);
                telemetry.addData("Path", "Complete");
                telemetry.update();
                idle();
            }   */
        }
    }
}



