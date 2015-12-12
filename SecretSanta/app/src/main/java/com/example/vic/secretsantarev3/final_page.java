package com.example.vic.secretsantarev3;
//This file is the final page that sends the email and displays the information for the event

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.util.Properties;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class final_page extends AppCompatActivity implements View.OnClickListener {
	//declare variable
    private Button buttonSend;
	
	//create page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);

        //test to see if everything transferred!
        Bundle meetingPageData = getIntent().getExtras();//get all saved data
        int guestNumber = meetingPageData.getInt("guestNumberKey", 2); //gets the number of guests from last page. 2 is default
        String groupName = meetingPageData.getString("groupNameKey"); //gets the group name from last page
        String[] GuestList = meetingPageData.getStringArray("guestListArrayKey"); //gets the array of names from saved list if not first time using contact page
        String[] EmailList = meetingPageData.getStringArray("emailListArrayKey"); //gets the array of names
        String meetingPlace = meetingPageData.getString("meetingPlace"); //gest the meeting place
        String eventTime = meetingPageData.getString("eventTime"); //gets the event time
        String eventDate = meetingPageData.getString("eventDate"); //gest the eventdate

        final TextView final1 = (TextView) findViewById(R.id.final1); //
        final1.setText("Group Name: "+groupName);

        final TextView final2 = (TextView) findViewById(R.id.final2); //
        final2.setText("Number of guests: "+guestNumber);

        final TextView final3 = (TextView) findViewById(R.id.final3); //
        final3.setText("Meeting Place: "+meetingPlace);

        final TextView final4 = (TextView) findViewById(R.id.final4); //
        final4.setText("Event Date: "+eventDate);

        final TextView final5 = (TextView) findViewById(R.id.final5); //
        final5.setText("Event Time: " +eventTime);
		
		//create name and email list
        String Name_list = "";
        String Email_list = "";
        for(int i = 0; i < guestNumber; i++)
        {
            Name_list = Name_list + GuestList[i]+"\n";
            Email_list = Email_list + EmailList[i]+"\n";
        }

        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);
    }
	//function to send the email out
    private void sendEmail(){
		//declare and initialize variables
        Bundle meetingPageData = getIntent().getExtras();//get all saved data
        int guestNumber = meetingPageData.getInt("guestNumberKey"); //gets the number of guests from last page. 2 is default
        String groupName = meetingPageData.getString("groupNameKey"); //gets the group name from last page
        String[] GuestList = meetingPageData.getStringArray("guestListArrayKey"); //gets the array of names from saved list if not first time using contact page
        String[] EmailList = meetingPageData.getStringArray("emailListArrayKey"); //gets the array of names
        String meetingPlace = meetingPageData.getString("meetingPlace"); //gets the meeting place
        String eventTime = meetingPageData.getString("eventTime"); //gets the event time
        String eventDate = meetingPageData.getString("eventDate"); //gets the event date
        int guest = 0;

        //call function to shuffle GuestList and EmailList
        shuffleArrays(GuestList, EmailList);

        while (guest < guestNumber - 1) {
            //content for email
            String email = EmailList[guest];
            String subject = "Your Secret Santa Assignment is...";
            String message = "Hello " + GuestList[guest] + "," + "\n" + "\n"
                    + "Your Secret Santa Assignment for " + groupName + " is " + GuestList[guest + 1] + "." + "\n" + "\n"
                    + "The event will be taking place at " + meetingPlace + " on " + eventDate + " at " + eventTime + "\n" + "\n"
                    + "Happy Holidays!";

            //creating sendmail object
            SendMail sm = new SendMail(this, email, subject, message);

            //executing sendmail to send email
            sm.execute();

            guest++;
        }
        //content for email
        String email = EmailList[guest];
        String subject = "Your Secret Santa Assignment is...";
        String message = "Hello " + GuestList[guest] + "," + "\n" + "\n"
            + "Your Secret Santa Assignment for " + groupName + " is " + GuestList[0] + "." + "\n" + "\n"
            + "The event will be taking place at " + meetingPlace + " on " + eventDate + " at " + eventTime + "\n" + "\n"
            + "Happy Holidays!";

        //creating sendmail object
        SendMail sm = new SendMail(this, email, subject, message);

        //executing sendmail to send email
        sm.execute();

    }
	//click button and send emails
    @Override
    public void onClick(View v){
        Bundle meetingPageData = getIntent().getExtras();//get all saved data
        String[] GuestList = meetingPageData.getStringArray("guestListArrayKey"); //gets the array of names from saved list if not first time using contact page
        String[] EmailList = meetingPageData.getStringArray("emailListArrayKey"); //gets the array of names

        shuffleArrays(GuestList, EmailList);
        sendEmail();
    }

	//function to shuffle the guest names and list to send out
    static void shuffleArrays(String[] guestList, String[] emailList){
        Random random = new Random();

        for (int i = guestList.length - 1; i > 0; i--){
            int index = random.nextInt(i + 1);

            // Simple swap of both emailList and guestList
            String guestName = guestList[index];
            String emailAddress = emailList[index];
            guestList[index] = guestList[i];
            emailList[index] = emailList[i];
            guestList[i] = guestName;
            emailList[i] = emailAddress;
        }
    }
	//function to restart entering info
    public void startOver(View view)
    {
        Intent i = new Intent(this, HomePage.class);
        startActivity(i);
    }
}




