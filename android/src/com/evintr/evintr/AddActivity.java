package com.evintr.evintr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AddActivity extends Activity
{
    Context context;
    static TableRow ticketRow, cashRow, subtypeRow, rateRow;
    static CharSequence[] options = null;
    static boolean[] selections = null;
    static Dialog d, st, et;
    static EditText nameText, zipText, addrText, cashText, ticketText, emailText, phoneText, descText, startText, endText;
    static Spinner infoText, rateText, typeText;
    static ToggleButton regText;
    static Bundle bundle;

    //    static EditText startText, endText;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        startup();
    }
    
    public void startup()
    {
        context = AddActivity.this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.add);

        CharSequence[] temp = { "Tennis", "Football", "Baseball", "Basketball", "Volleyball", "Disc Golf", "Golf", "Putt Putt", "Bowling",
                "Racquetball", "Other" };
        options = temp;

        st = new DateTimeDialog(context);
        st.setOwnerActivity(this);

        et = new DateTimeDialog(context);
        et.setOwnerActivity(this);

        TableLayout table = (TableLayout) findViewById(R.id.addtable);
        table.setStretchAllColumns(true);

        TableRow nameRow = new TableRow(context);
        TableRow zipRow = new TableRow(context);
        TableRow addrRow = new TableRow(context);
        TableRow infoRow = new TableRow(context);
        cashRow = new TableRow(context);
        ticketRow = new TableRow(context);
        TableRow emailRow = new TableRow(context);
        TableRow phoneRow = new TableRow(context);
        TableRow descRow = new TableRow(context);
        TableRow startRow = new TableRow(context);
        TableRow startPick = new TableRow(context);
        TableRow endRow = new TableRow(context);
        TableRow endPick = new TableRow(context);
        TableRow regRow = new TableRow(context);
        rateRow = new TableRow(context);
        TableRow typeRow = new TableRow(context);
        subtypeRow = new TableRow(context);
        TableRow imageRow = new TableRow(context);
        TableRow submit = new TableRow(context);

        TextView nameLabel = new TextView(context);
        nameLabel.setText("Event Name*:");
        nameLabel.setTextColor(Color.BLACK);
        nameText = new EditText(context);
        nameText.setGravity(Gravity.RIGHT);
        nameText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        nameRow.addView(nameLabel);
        nameRow.addView(nameText);
        nameRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView zipLabel = new TextView(context);
        zipLabel.setText("Event ZIP*:");
        zipLabel.setTextColor(Color.BLACK);
        zipText = new EditText(context);
        zipText.setGravity(Gravity.RIGHT);
        zipText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        zipRow.addView(zipLabel);
        zipRow.addView(zipText);
        zipRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView addrLabel = new TextView(context);
        addrLabel.setText("Event Address*:");
        addrLabel.setTextColor(Color.BLACK);
        addrText = new EditText(context);
        addrText.setGravity(Gravity.RIGHT);
        addrText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        addrRow.addView(addrLabel);
        addrRow.addView(addrText);
        addrRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView infoLabel = new TextView(context);
        infoLabel.setText("Price Info*:");
        infoLabel.setTextColor(Color.BLACK);
        infoText = new Spinner(context);
        ArrayAdapter<CharSequence> infoAdapter = ArrayAdapter.createFromResource(context, R.array.price_array, android.R.layout.simple_spinner_item);
        infoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        infoText.setAdapter(infoAdapter);
        infoRow.addView(infoLabel);
        infoRow.addView(infoText);
        infoRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView cashLabel = new TextView(context);
        cashLabel.setText("Price:");
        cashLabel.setTextColor(Color.BLACK);
        cashText = new EditText(context);
        cashText.setGravity(Gravity.RIGHT);
        cashText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        cashRow.addView(cashLabel);
        cashRow.addView(cashText);
        cashRow.setVisibility(TextView.GONE);
        cashRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView ticketLabel = new TextView(context);
        ticketLabel.setText("Ticket URL:");
        ticketLabel.setTextColor(Color.BLACK);
        ticketText = new EditText(context);
        ticketText.setGravity(Gravity.RIGHT);
        ticketText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        ticketRow.addView(ticketLabel);
        ticketRow.addView(ticketText);
        ticketRow.setVisibility(TextView.GONE);
        ticketRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView emailLabel = new TextView(context);
        emailLabel.setText("Email Address*:");
        emailLabel.setTextColor(Color.BLACK);
        emailText = new EditText(context);
        emailText.setGravity(Gravity.RIGHT);
        emailText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        emailRow.addView(emailLabel);
        emailRow.addView(emailText);
        emailRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView phoneLabel = new TextView(context);
        phoneLabel.setText("Phone Number:\nxxx-xxx-xxxx");
        phoneLabel.setTextColor(Color.BLACK);
        phoneText = new EditText(context);
        phoneText.setGravity(Gravity.RIGHT);
        phoneText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        phoneRow.addView(phoneLabel);
        phoneRow.addView(phoneText);
        phoneRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView descLabel = new TextView(context);
        descLabel.setText("Description*:");
        descLabel.setTextColor(Color.BLACK);
        descText = new EditText(context);
        descText.setGravity(Gravity.TOP);
        descText.setMinHeight(50);
        descText.setMinLines(10);
        descText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        descRow.addView(descLabel);
        descRow.addView(descText);
        descRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView startLabel = new TextView(context);
        startLabel.setText("Start Date*:");
        startLabel.setTextColor(Color.BLACK);
        startText = new EditText(context);
        startText.setGravity(Gravity.RIGHT);
        startText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        startRow.addView(startLabel);
        startRow.addView(startText);
        startRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView spickLabel = new TextView(context);
        spickLabel.setText("Select Start Date");
        spickLabel.setTextColor(Color.BLACK);
        Button startButton = new Button(context);
        startButton.setText("Pick date");
        startButton.setGravity(Gravity.RIGHT);
        startPick.addView(spickLabel);
        startPick.addView(startButton);

        TextView endLabel = new TextView(context);
        endLabel.setText("End Date:");
        endLabel.setTextColor(Color.BLACK);
        endText = new EditText(context);
        endText.setGravity(Gravity.RIGHT);
        endText.setMaxWidth(((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2);
        endRow.addView(endLabel);
        endRow.addView(endText);
        endRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView epickLabel = new TextView(context);
        epickLabel.setText("Select End Date");
        epickLabel.setTextColor(Color.BLACK);
        Button endButton = new Button(context);
        endButton.setText("Pick date");
        endButton.setGravity(Gravity.RIGHT);
        endPick.addView(epickLabel);
        endPick.addView(endButton);

        TextView regLabel = new TextView(context);
        regLabel.setText("Are these regular hours?*");
        regLabel.setTextColor(Color.BLACK);
        regText = new ToggleButton(context);
        regText.setGravity(Gravity.RIGHT);
        regRow.addView(regLabel);
        regRow.addView(regText);
        regRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView rateLabel = new TextView(context);
        rateLabel.setText("Recurrance Rate:");
        rateLabel.setTextColor(Color.BLACK);
        rateText = new Spinner(context);
        ArrayAdapter<CharSequence> rateAdapter = ArrayAdapter.createFromResource(context, R.array.rate_array, android.R.layout.simple_spinner_item);
        rateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateText.setAdapter(rateAdapter);
        rateRow.addView(rateLabel);
        rateRow.addView(rateText);
        rateRow.setGravity(Gravity.FILL_HORIZONTAL);
        rateRow.setVisibility(TextView.GONE);

        TextView typeLabel = new TextView(context);
        typeLabel.setText("Event Type*:");
        typeLabel.setTextColor(Color.BLACK);
        typeText = new Spinner(context);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(context, R.array.type_array, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeText.setAdapter(typeAdapter);
        typeRow.addView(typeLabel);
        typeRow.addView(typeText);
        typeRow.setGravity(Gravity.FILL_HORIZONTAL);

        TextView subtypeLabel = new TextView(context);
        subtypeLabel.setText("Event Subtype*:");
        subtypeLabel.setTextColor(Color.BLACK);
        Button subtypeText = new Button(context);
        subtypeText.setGravity(Gravity.RIGHT);
        subtypeText.setText("Select Subtype");
        subtypeRow.addView(subtypeLabel);
        subtypeRow.addView(subtypeText);
        subtypeRow.setGravity(Gravity.FILL_HORIZONTAL);
        subtypeRow.setVisibility(TextView.GONE);

        Button submitButton = new Button(context);
        submitButton.setText("Submit!");
        submit.addView(submitButton);
        submit.setGravity(Gravity.FILL_HORIZONTAL);

        table.addView(nameRow);
        table.addView(zipRow);
        table.addView(addrRow);
        table.addView(infoRow);
        table.addView(cashRow);
        table.addView(ticketRow);
        table.addView(emailRow);
        table.addView(phoneRow);
        table.addView(descRow);
        table.addView(startRow);
        table.addView(startPick);
        table.addView(endRow);
        table.addView(endPick);
        table.addView(regRow);
        table.addView(rateRow);
        table.addView(typeRow);
        table.addView(subtypeRow);
        table.addView(imageRow);
        table.addView(submit);

        startButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                st.show();
            }
        });

        endButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                et.show();
            }
        });

        st.setOnDismissListener(new OnDismissListener()
        {
            public void onDismiss(DialogInterface dialog)
            {
                startText.setText(st.toString());
            }
        });

        et.setOnDismissListener(new OnDismissListener()
        {
            public void onDismiss(DialogInterface dialog)
            {
                endText.setText(et.toString());
            }
        });

        infoText.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                if (arg0.getSelectedItem().toString().equals("Cash"))
                    cashRow.setVisibility(TextView.VISIBLE);
                else
                    cashRow.setVisibility(TextView.GONE);

                if (arg0.getSelectedItem().toString().equals("Ticket"))
                    ticketRow.setVisibility(TextView.VISIBLE);
                else
                    ticketRow.setVisibility(TextView.GONE);
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        regText.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                    rateRow.setVisibility(TextView.VISIBLE);
                else
                    rateRow.setVisibility(TextView.GONE);
            }
        });

        typeText.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                String type = arg0.getSelectedItem().toString();
                if (!type.equals("Movies"))
                    subtypeRow.setVisibility(TextView.VISIBLE);
                else
                    subtypeRow.setVisibility(TextView.GONE);
                if (type.equals("Sports"))
                {
                    CharSequence[] temp = { "Tennis", "Football", "Baseball", "Basketball", "Volleyball", "Disc Golf", "Golf", "Putt Putt",
                            "Bowling", "Racquetball", "Other" };
                    options = temp;
                }
                else if (type.equals("Music"))
                {
                    CharSequence[] temp = { "Concert", "Outdoor Performance", "Open Mic", "General Admission", "Other" };
                    options = temp;
                }
                else if (type.equals("Sales"))
                {
                    CharSequence[] temp = { "Clothing", "Shoe", "Jewelry", "Furniture", "Appliance", "Other" };
                    options = temp;
                }
                else if (type.equals("Parks"))
                {
                    CharSequence[] temp = { "Tennis", "Hiking", "Gardens", "Disc Golf", "Playground", "Outdoor Performance", "Other" };
                    options = temp;
                }
                else if (type.equals("Arts"))
                {
                    CharSequence[] temp = { "Museums", "Theater", "Plays", "Outdoor Performance", "General Admission", "Other" };
                    options = temp;
                }
                else if (type.equals("Night Life"))
                {
                    CharSequence[] temp = { "Bars", "Clubs", "Open Late", "General Admission", "Other" };
                    options = temp;
                }
                else if (type.equals("Parties"))
                {
                    CharSequence[] temp = { "Grand Opening", "House Party", "Other" };
                    options = temp;
                }
                else
                {
                    CharSequence[] temp = { "Grand Opening", "Public Event", "Other" };
                    options = temp;
                }
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });

        subtypeText.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                try
                {
                    removeDialog(0);
                } catch (Exception e)
                {
                }
                boolean[] temp = new boolean[options.length];
                selections = temp;
                showDialog(0);
            }
        });

        submitButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();

                String r = "";
                if (regText.getText().toString().equals("OFF"))
                    r = "No";
                else
                    r = "Yes";

                Random ran = new Random();
                //                List<CharSequence> list = new ArrayList<CharSequence>();
                //                for(int i = 0; i < selections.length; i++)
                //                {
                //                    if(selections[i])
                //                        list.add(options[i]);
                //                }

                if (endText.getText().toString().length() < 2)
                    endText.setText(startText.getText().toString());
                if (x(nameText) && x(zipText) && x(addrText) && x(emailText) && x(descText) && x(startText) && x(endText))
                {
                    if (zipText.getText().toString().length() == 5 && emailText.getText().toString().contains("@"))
                    {
                        //                        pairs.add(new BasicNameValuePair("authtoken", "###############"));
                        //                        pairs.add(new BasicNameValuePair("scope", "creatorapi"));
                        pairs.add(new BasicNameValuePair("Verification_Number", ran.nextInt(1000001) + ""));
                        pairs.add(new BasicNameValuePair("Event_Name", nameText.getText().toString().replaceAll(" ", "_")));
                        pairs.add(new BasicNameValuePair("Event_ZIP_Code", zipText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Event_Address", addrText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Price_Info", infoText.getSelectedItem().toString()));
                        pairs.add(new BasicNameValuePair("Price", cashText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Ticket_Link", ticketText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Email_Address", emailText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Phone", phoneText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Description", descText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Start_Date", startText.getText().toString()));
                        pairs.add(new BasicNameValuePair("End_Date", endText.getText().toString()));
                        pairs.add(new BasicNameValuePair("Regular", r));
                        pairs.add(new BasicNameValuePair("Rate", rateText.getSelectedItem().toString()));
                        pairs.add(new BasicNameValuePair("eType", typeText.getSelectedItem().toString()));
                        //                pairs.add(new BasicNameValuePair("Subtype", list.toString()));

                        try
                        {
                            HttpClient client = new DefaultHttpClient();
                            HttpPost httpRequest = new HttpPost("http://evintr.com/add.php/");
                            httpRequest.setEntity(new UrlEncodedFormEntity(pairs));
                            HttpResponse res = client.execute(httpRequest);
                            //                            Log.d("res", res.getStatusLine().toString());
                            //                            Log.d("pairs", pairs.toString());
                            if (res.getStatusLine().toString().contains("200"))
                            {
                                Toast.makeText(context, "Event uploaded!", 1).show();
                                Intent intent = new Intent(context, EvintrActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(context, "Submission unsuccessful.", 1).show();
                        } catch (Exception e)
                        {
                            Toast.makeText(context, "Error! Submission unsuccessful." + e.getMessage(), 1).show();
                        }
                    }
                    else
                        Toast.makeText(context, "Please fill out all required fields!", 1).show();
                }
                else
                    Toast.makeText(context, "Please fill out all required fields!", 1).show();

            }
        });
    }

    public boolean x(EditText t)
    {
        if (t.getText().toString().length() > 1)
            return true;
        return false;
    }

    public Dialog onCreateDialog(int id)
    {
        switch (id)
        {
        case 0:
            d = new AlertDialog.Builder(this).setTitle("Subtypes").setMultiChoiceItems(options, selections, new OnMultiChoiceClickListener()
            {
                public void onClick(DialogInterface dialog, int which, boolean isChecked)
                {
                    if (isChecked)
                        selections[which] = true;
                    else
                        selections[which] = false;
                }
            }).create();

            return d;
        }
        return null;
    }

    public void onConfigurationChanged()
    {
        startup();
    }
}