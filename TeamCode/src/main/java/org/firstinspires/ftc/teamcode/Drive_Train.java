package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Drive_Train")
public class Drive_Train extends LinearOpMode {

    // Declare OpMode members.
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    int GOOD=0;

    double FL_float = 0;
    double FR_float = 0;
    double BL_float = 0;
    double BR_float = 0;

    //DcMotorController.DeviceMode DevMode;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

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

            WheelPower();

        }
        stopIsPushed();
    }

    void WheelPower() {
        float LeftYvalue = gamepad1.left_stick_y;
        float LeftXvalue = gamepad1.left_stick_x;
        float RightXvalue = gamepad1.right_stick_x;

        float FLSpeed = (-LeftYvalue + LeftXvalue + RightXvalue);
        float BLSpeed = (-LeftYvalue - LeftXvalue + RightXvalue);
        float FRSpeed = (-LeftYvalue - LeftXvalue - RightXvalue);
        float BRSpeed = (-LeftYvalue + LeftXvalue - RightXvalue);

        FLSpeed = Range.clip(FLSpeed, -1, 1);
        FRSpeed = Range.clip(FRSpeed, -1, 1);
        BLSpeed = Range.clip(BLSpeed, -1, 1);
        BRSpeed = Range.clip(BRSpeed, -1, 1);

        if(Math.abs(FLSpeed)>0) {
            if (Math.abs(FL_float) < Math.abs(FLSpeed)) {
                FL_float += FLSpeed / 20;
            } else {
                FL_float = FLSpeed;
            }
            FL.setPower(FL_float);
        } else {
            FL.setPower(0);
            FL_float = 0;
        }

        if(Math.abs(FRSpeed)>0) {
            if (Math.abs(FR_float) < Math.abs(FRSpeed)) {
                FR_float += FRSpeed / 20;
            } else {
                FR_float = FRSpeed;
            }
            FR.setPower(FR_float);
        } else {
            FR.setPower(0);
            FR_float = 0;
        }

        if(Math.abs(BLSpeed)>0) {
            if (Math.abs(BL_float) < Math.abs(BLSpeed)) {
                BL_float += BLSpeed / 20;
            } else {
                BL_float = BLSpeed;
            }
            BL.setPower(BL_float);
        } else {
            BL.setPower(0);
            BL_float = 0;
        }

        if(Math.abs(BRSpeed)>0) {
            if (Math.abs(BR_float) < Math.abs(BRSpeed)) {
                BR_float += BRSpeed / 20;
            } else {
                BR_float = BRSpeed;
            }
            BR.setPower(BR_float);
        } else {
            BR.setPower(0);
            BR_float = 0;
        }

    }

    /*void xRailLower() {
        GOOD=1;
        telemetry.addData("Boldposition", xRailPosition);
        xRailPosition = 0;
        telemetry.addData("Bposition", xRailPosition);    //
        telemetry.update();
        XRail.setTargetPosition(xRailPosition);
        XRail.setPower(1.00);
        //while (XRail.isBusy()){}
        //XRail.setPower(0.0);
        GOOD=0;
    }*/

    void stopIsPushed(){
        FL.setPower(0.00);
        FR.setPower(0.00);
        BL.setPower(0.00);
        BR.setPower(0.00);
        telemetry.addData("Path", "Complete");
        telemetry.update();
        idle();
    }
}



//  ColorSensor color;                //for reading color on beacon


//  boolean LEDState = false;//mode of the color sensor;
//Active = true, Passive = false
// Send telemetry message to signify robot waiting;

//     telemetry.addData("Status", "Ready to run");    //
//              telemetry.update();