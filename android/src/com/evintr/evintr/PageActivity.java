package com.evintr.evintr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PageActivity extends Activity
{
    Context context;
    static String[] arr;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        context = this.getBaseContext();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.page);
        
        LinearLayout root = (LinearLayout)findViewById(R.id.pageroot);

        TextView name = new TextView(context);
        name.setTextColor(Color.BLACK);
        TextView desc = new TextView(context);
        desc.setTextColor(Color.BLACK);
        TextView addr = new TextView(context);
        addr.setTextColor(Color.BLACK);
        TextView ticket = new TextView(context);
        ticket.setTextColor(Color.BLACK);
        ticket.setMovementMethod(LinkMovementMethod.getInstance());
        TextView price = new TextView(context);
        price.setTextColor(Color.BLACK);
        TextView start = new TextView(context);
        start.setTextColor(Color.BLACK);
        TextView end = new TextView(context);
        end.setTextColor(Color.BLACK);

        Bundle extras = getIntent().getExtras();
        arr = extras.getStringArray("arr");

        name.setText("Name:  " + arr[0].replaceAll("_", " ") + "\n\n");
        desc.setText("Description:  " + arr[1].replaceAll("n", ""));
        addr.setText("Address:  " + arr[2]);
        
        if (arr[4].toLowerCase().contains("ticket") && arr[3].length() > 2)
        {
//            String t = "Ticket:  <a href='" + arr[3] + "'>" + arr[3] + "</a>";
//            ticket.setText(Html.fromHtml(t));
            ticket.setText("Ticket:  " + arr[3]);
            ticket.setTextColor(Color.parseColor("#0000FF"));
        }
        else if (arr[4].toLowerCase().contains("cash"))
            price.setText("Price:  " + arr[5]);

        start.setText("Start Date:  " + arr[6]);
        end.setText("End Date:  " + arr[7]);
        
        ticket.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(arr[3]));
                startActivity(i);
            }
        });
        
        root.addView(name);
        root.addView(desc);
        root.addView(addr);
        root.addView(ticket);
        root.addView(price);
        root.addView(start);
        root.addView(end);
        
    }

}