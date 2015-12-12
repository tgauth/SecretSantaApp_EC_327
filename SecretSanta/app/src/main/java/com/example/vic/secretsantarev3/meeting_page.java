package com.example.vic.secretsantarev3;
//This file declares our meeting_page activity 

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class meeting_page extends AppCompatActivity {
	//declare variables
    String meetingPlace;
    String eventDate;
    String eventTime;
    String groupName;
    int guestNumber;
    String[] GuestList;
    String[] EmailList;

	//create page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_page);
		
		//initialize variables
        Bundle PageData = getIntent().getExtras();//get al; saved data
        guestNumber = PageData.getInt("guestNumberKey", 2); //gets the number of guests from last page. 2 is default
        groupName = PageData.getString("groupNameKey"); //gets the group name from last page

        GuestList = PageData.getStringArray("guestListArrayKey"); //gets the array of names from saved list if not first time using contact page
        EmailList = PageData.getStringArray("emailListArrayKey"); //gets the array of names


    }
    public void moveToFinalPage(View view)
    {
		//enter the meeting place, event date, and time
		//save the variables and move to the final page
        Intent i = new Intent(this, final_page.class);
		
        final EditText currentMeetingPlace = (EditText) findViewById(R.id.meetingPlace);
        meetingPlace = currentMeetingPlace.getText().toString(); //gets the meeting place from edittext

        final EditText currentDateOfEvent = (EditText) findViewById(R.id.dateOfEvent);
        eventDate = currentDateOfEvent.getText().toString(); //gets the event date from edittext

        final EditText currentEventTime = (EditText) findViewById(R.id.timeOfEvent);
        eventTime = currentEventTime.getText().toString(); //gets the event date from edittext

        i.putExtra("groupNameKey", groupName); //saves the group name
        i.putExtra("guestNumberKey", guestNumber); //saves the number of guests
        i.putExtra("guestListArrayKey", GuestList); //saves the array of guest's names
        i.putExtra("emailListArrayKey", EmailList); //saves the array of guest's emails
        i.putExtra("eventTime", eventTime); //saves the event Time
        i.putExtra("eventDate", eventDate);
        i.putExtra("meetingPlace", meetingPlace);

        startActivity(i);
    }
}
