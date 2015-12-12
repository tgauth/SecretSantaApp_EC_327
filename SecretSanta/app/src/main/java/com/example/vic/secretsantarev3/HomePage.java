package com.example.vic.secretsantarev3;
//This file is the HomePage where the user enters the group name and number of guests

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class HomePage extends AppCompatActivity {

	//create page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // sources for images:
        // christmas ornament
        // http://www.sweetshimas.com/myfiles/image/PSP%20TUBES/PSP%20WINTER%20CHRISTMAS/ornament-green.png
        // snowflake background
        // http://www.greatvectors.com/preview/glittering-background-with-snowflakes-1425.jpg
        // elf
        // https://s-media-cache-ak0.pinimg.com/736x/f9/ac/92/f9ac92957061627899962b50c629f2f1.jpg
        // santa hat
        // http://cliparts.co/cliparts/8cz/ngn/8czngnB7i.gif
        // candy cane:
        // http://vignette2.wikia.nocookie.net/clubpenguin/images/7/78/Candy_Cane_Pin.PNG/revision/20150313162223
    }
	
	//function to move to the next page
    public void moveToContactPage(View view)
    {
        Intent i = new Intent(this, contact_page.class);
		
		//initialize variables
        final EditText groupName = (EditText) findViewById(R.id.groupName);
        String userGroupName = groupName.getText().toString(); //gets the group name from edittext
        final EditText guestNumber = (EditText) findViewById(R.id.numberOfGuests);
        final int numberOfGuests = Integer.parseInt(guestNumber.getText().toString()); //gets the number of guests from the edit text

        i.putExtra("groupNameKey", userGroupName); //passes the value to the next activity
        i.putExtra("guestNumberKey",numberOfGuests);

        startActivity(i);
    }

}
