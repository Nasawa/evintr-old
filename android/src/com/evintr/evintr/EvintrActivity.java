package com.evintr.evintr;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EvintrActivity extends Activity
{
    Context context;
    Spinner typeSpinner;
    Spinner subtypeSpinner;
    ArrayAdapter<CharSequence> typeAdapter;
    ArrayAdapter<CharSequence> subtypeAdapter;
    ArrayList<CharSequence> subtype;
    EditText zip;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);

        context = this.getBaseContext();
        subtype = new ArrayList<CharSequence>();

        zip = (EditText) findViewById(R.id.Search);

        typeSpinner = (Spinner) findViewById(R.id.typespinner);
        subtypeSpinner = (Spinner) findViewById(R.id.subtypespinner);

        typeAdapter = ArrayAdapter.createFromResource(context, R.array.event_array, android.R.layout.simple_spinner_item);//Adapts the string array to the drop-down box
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//Identifies the adapter as a drop-down box
        typeSpinner.setAdapter(typeAdapter);

        subtypeAdapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item, subtype);
        subtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //subtypeAdapter.clear();
        subtypeSpinner.setAdapter(subtypeAdapter);

        typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                chex(arg0.getSelectedItem().toString());
                subtypeAdapter.notifyDataSetChanged();
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent nav = new Intent(context, ResultActivity.class);
                int x = 0;
                nav.putExtra("x", x);
                nav.putExtra("zip", zip.getText().toString());
                nav.putExtra("type", typeSpinner.getSelectedItem().toString());
                if (subtypeSpinner.getVisibility() == TextView.VISIBLE)
                    nav.putExtra("subtype", subtypeSpinner.getSelectedItem().toString());
                startActivity(nav);
            }
        });

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent nav = new Intent(context, AddActivity.class);
                startActivity(nav);
            }
        });
        
        Button fb = (Button) findViewById(R.id.facebookButton);
        fb.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent nav = new Intent(context, FacebookActivity.class);
                startActivity(nav);
            }
        });

        Button contact = (Button) findViewById(R.id.contact);
        contact.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.setType("text/plain");

                String uriText;
                uriText = "mailto:evintr@evintr.com?subject=&body=";
                Uri uri = Uri.parse(uriText);

                email.setData(uri);
                startActivity(Intent.createChooser(email, "Email Using:"));
            }
        });
    }

    public void chex(String type)
    {
        subtypeAdapter.clear();
        //        subtype.clear();
        if (type.equals("All") || type.equals("Movies"))
        {
            subtypeSpinner.setVisibility(Spinner.GONE);
        }
        else
            subtypeSpinner.setVisibility(Spinner.VISIBLE);

        //        if (type == "Movies")
        //            subtypeSpinner.setVisibility(Spinner.GONE);

        if (type.equals("Sports"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Tennis");
            subtypeAdapter.add("Football");
            subtypeAdapter.add("Baseball");
            subtypeAdapter.add("Basketball");
            subtypeAdapter.add("Volleyball");
            subtypeAdapter.add("Disc Golf");
            subtypeAdapter.add("Golf");
            subtypeAdapter.add("Putt Putt");
            subtypeAdapter.add("Bowling");
            subtypeAdapter.add("Racquetball");
            subtypeAdapter.add("Other");
        }
        else if (type.equals("Music"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Concert");
            subtypeAdapter.add("Outdoor Performance");
            subtypeAdapter.add("Open Mic");
            subtypeAdapter.add("General Admission");
            subtypeAdapter.add("Other");
        }
        else if (type.equals("Sales"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Clothing");
            subtypeAdapter.add("Shoe");
            subtypeAdapter.add("Jewelry");
            subtypeAdapter.add("Furniture");
            subtypeAdapter.add("Appliance");
            subtypeAdapter.add("Other");
        }
        else if (type.equals("Parks"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Tennis");
            subtypeAdapter.add("Hiking");
            subtypeAdapter.add("Gardens");
            subtypeAdapter.add("Disc Golf");
            subtypeAdapter.add("Playground");
            subtypeAdapter.add("Outdoor Performance");
            subtypeAdapter.add("Other");
        }
        else if (type.equals("Arts"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Museums");
            subtypeAdapter.add("Theater");
            subtypeAdapter.add("Plays");
            subtypeAdapter.add("Outdoor Performance");
            subtypeAdapter.add("General Admission");
            subtypeAdapter.add("Other");
        }
        else if (type.equals("Night Life"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Bars");
            subtypeAdapter.add("Clubs");
            subtypeAdapter.add("Open Late");
            subtypeAdapter.add("General Admission");
            subtypeAdapter.add("Other");
        }
        else if (type.equals("Parties"))
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Grand Opening");
            subtypeAdapter.add("House Party");
            subtypeAdapter.add("Other");
        }
        else
        {
            subtypeAdapter.add("Search All");
            subtypeAdapter.add("Grand Opening");
            subtypeAdapter.add("Public Event");
            subtypeAdapter.add("Other");
        }
    }

//    public boolean onCreateOptionsMenu(Menu menu)// called when the phone's 'menu' button is pressed
//    {
//        MenuInflater menuInflater = getMenuInflater();// makes a menuInflater
//        menuInflater.inflate(R.menu.zelda_menu, menu);// inflates the menu with the zelda_menu .xml
//        return super.onCreateOptionsMenu(menu);// returns... something.
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item)// when an item is selected
//    {
//        try
//        {
//            switch (item.getItemId())
//            // get the item selected
//            {
//
//            case R.id.settings://if 'Settings'
//            {
//
//            }
//            }
//        } catch (Exception e)
//        {
//        }
//    }
}