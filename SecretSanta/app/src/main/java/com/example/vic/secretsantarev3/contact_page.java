package com.example.vic.secretsantarev3;
//This file declares the contact_page where each guest name and email is entered

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class contact_page extends AppCompatActivity {
    //declare variables
	int pageReferencecounter = 0;
    int guestNumber;
    String groupName;
    String[] oldGuestList;
    String[] oldEmailList;
    String[] currentGuestList;
    String[] currentEmailList;
    String currentName;
    String currentEmail;
	
	//create page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Bundle homePageData = getIntent().getExtras();

        guestNumber = homePageData.getInt("guestNumberKey", 2); //gets the number of guests from last page. 2 is default
        groupName = homePageData.getString("groupNameKey"); //gets the group name from last page

        pageReferencecounter = homePageData.getInt("pageReferenceKey", pageReferencecounter); //gets the number of times the page has been displayed.
        if (pageReferencecounter == 0){
            oldGuestList = new String[guestNumber]; //creates a new array if the first time viewing contact page
            oldEmailList = new String[guestNumber];
        }
        else
        {
            oldGuestList = homePageData.getStringArray("guestListArrayKey"); //gets the array of names from saved list if not first time using contact page
            oldEmailList = homePageData.getStringArray("emailListArrayKey"); //gets the array of names
        }
        final TextView baconText = (TextView) findViewById(R.id.baconText); //
        baconText.setText("Group Name: "+groupName); //displays the group name at the bottom of the page

        final TextView pageNumber = (TextView) findViewById(R.id.pageNumber); //
        pageNumber.setText("Guest #"+(pageReferencecounter+1)); //displays the guest number at the top of the page


    }
    public void moveToMeetPageIfDone(View view)
    {
        
		final EditText currentNameEdit = (EditText) findViewById(R.id.currentNameOfFriend);
		currentName = currentNameEdit.getText().toString(); //gets the name from edittext

		final EditText currentEmailEdit = (EditText) findViewById(R.id.currentEmailOfFriend);
		currentEmail = currentEmailEdit.getText().toString(); //gets the name from edittext

		currentGuestList = new String[guestNumber]; //Get old array and add new entry
		System.arraycopy(oldGuestList, 0, currentGuestList,0,guestNumber);
		currentGuestList[pageReferencecounter] = currentName;

		currentEmailList = new String[guestNumber]; //Get old array and add new entry
		System.arraycopy(oldEmailList, 0, currentEmailList,0,guestNumber);
		currentEmailList[pageReferencecounter] = currentEmail;		
		
		//after namelist and emaillist are randomized email a person the next person in the list
		if(pageReferencecounter < (guestNumber-1))
        {

            Intent i = new Intent(this, contact_page.class); //displays this page again to collect new guest info

            i.putExtra("groupNameKey", groupName); //saves the group name
            i.putExtra("guestNumberKey", guestNumber); //saves the number of guests
            i.putExtra("guestListArrayKey", currentGuestList); //saves the array of guest's name
            i.putExtra("emailListArrayKey", currentEmailList); //saves the array of guest's emails

            pageReferencecounter = pageReferencecounter +1; //increments the number of times the page has been displayed
            i.putExtra("pageReferenceKey",pageReferencecounter); //saves the number of times the page has been displayed.

            startActivity(i);
        }
        else //exception for final person to get first person for secret santa
        {
            Intent i = new Intent(this, meeting_page.class);

            i.putExtra("groupNameKey", groupName); //saves the group name
            i.putExtra("guestNumberKey", guestNumber); //saves the number of guests
            i.putExtra("guestListArrayKey", currentGuestList); //saves the array of guest's name
            i.putExtra("emailListArrayKey", currentEmailList); //saves the array of guest's emails

            startActivity(i);
        }
    }
}
