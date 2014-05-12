package com.evintr.evintr;

import java.io.InputStreamReader;
import java.net.URI;
import java.util.Scanner;
import java.util.Stack;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity
{
    Context context;
    Handler handler = new Handler();
    static TableLayout table;
    boolean empty = false, lclick = false;;
    static String sZip, sType, sSubtype, apiZip = "";
    static String[] strobj, apiarr;
    static TextView name, zip, price, type, subtype;
    static int x = 0, y = 20, c = 0, length = 0;
    static Stack<Integer> cStack = new Stack<Integer>();
    static Scanner in;
    static int ID = 0;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        //        x = extras.getInt("x");
        sZip = extras.getString("zip");
        sType = extras.getString("type");
        if (extras.getString("subtype") != null)
            sSubtype = extras.getString("subtype");
        else
            sSubtype = "All";

        context = this.getBaseContext();

        if (x != 0)
            x -= 20;

        String[] temp = sZip.split(" ");
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i].toLowerCase() == temp[i].toUpperCase() && temp[i].length() == 5)
            {
                apiZip = temp[i];
                break;
            }
        }

        if (!cStack.isEmpty())
        {
            table.removeAllViews();
            c = cStack.pop();
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.results);
        table = (TableLayout) findViewById(R.id.table);
        table.setVerticalScrollBarEnabled(true);
        table.setScrollBarStyle(TableLayout.SCROLLBARS_INSIDE_OVERLAY);
        //        if (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getOrientation() % 2 != 0)
        table.setGravity(Gravity.CENTER);

        handler.postDelayed(results, 100);
    }

    public final Runnable results = new Runnable()
    {
        public void run()
        {
            TableRow row = new TableRow(context);
            if (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getOrientation() % 2 != 0)
                row.setGravity(Gravity.CENTER);

            LinearLayout root = (LinearLayout) findViewById(R.id.resultroot);

            name = new TextView(context);
            zip = new TextView(context);
            price = new TextView(context);
            type = new TextView(context);
            subtype = new TextView(context);

            name.setText("Name");
            name.setTextColor(Color.BLACK);
            name.setPadding(0, 2, 1, 2);
            row.addView(name);

            zip.setText("ZIP");
            zip.setTextColor(Color.BLACK);
            zip.setPadding(5, 2, 1, 2);
            row.addView(zip);

            price.setText("Price");
            price.setTextColor(Color.BLACK);
            price.setPadding(5, 2, 1, 2);
            row.addView(price);

            type.setText("Type");
            type.setTextColor(Color.BLACK);
            type.setPadding(5, 2, 1, 2);
            row.addView(type);

            subtype.setText("Subtype");
            subtype.setTextColor(Color.BLACK);
            subtype.setPadding(5, 2, 1, 2);
            row.addView(subtype);

            table.addView(row);
            root.refreshDrawableState();

            if (y - 20 % 100 == 0)
            {
                try
                {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    if (apiZip == "")
                        request.setURI(new URI("http://evintr.com/droidapi.php?" + (y - 20)));
                    else
                        request.setURI(new URI("http://evintr.com/droidapi.php?" + (y - 20) + "?" + apiZip));
                    HttpResponse response = client.execute(request);
                    request = null;
                    client = null;
                    in = new Scanner(new InputStreamReader(response.getEntity().getContent()));
                    in.useDelimiter("%%%");
                    length = in.nextInt();
                    apiarr = new String[100];
                    if (length < 1)
                        empty = true;
                    else
                        for (int i = 0; i < 100; i++)
                        {
                            if (in.hasNext())
                            {
                                apiarr[i] = in.next();
                            }
                            else
                                break;
                        }
                    in.close();
                } catch (Exception e)
                {
                    Toast.makeText(context, "Problem getting request!", 1).show();
                }
            }
            cStack.add(c % 100);
            //            apiarr[0].replace("\\[\\[", "");
            //            apiarr[0].replace("\\]\\]", "");
            if (!empty)
                while (x < y && apiarr[c % 100] != null)
                {
                    strobj = apiarr[c % 100].split("\",\"");
                    strobj[0].replace("\"", "");
                    strobj[strobj.length - 1].replace("\"", "");

                    name = new TextView(context);
                    zip = new TextView(context);
                    price = new TextView(context);
                    type = new TextView(context);
                    subtype = new TextView(context);
                    c++;

                    try
                    {
                        if (sZip.equals("") || sZip.equals("Enter ZIP...") || searchName(strobj) || sZip.equals(strobj[2]))
                            if (sType.toLowerCase().equals(strobj[14].toLowerCase()) || sType.equals("All"))
                                if (sSubtype.equals("All") || subtype.equals(null) || strobj[15].toLowerCase().contains(sSubtype.toLowerCase()))
                                {
                                    x++;
                                    empty = false;
                                    row = new TableRow(context);
                                    if (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getOrientation() % 2 != 0)
                                        row.setGravity(Gravity.CENTER);

                                    strobj[1] = strobj[1].replaceAll("_", " ").replaceAll("&apos;", "'");

                                    if (strobj[1].length() > 29)
                                    {
                                        int z = 0;
                                        int zz = 0;
                                        for (int j = 0; j < strobj[1].length(); j++)
                                        {
                                            name.setText(strobj[1] + "\n");
                                            if (strobj[1].charAt(j) == ' ')
                                            {
                                                if (j > 15)
                                                    zz = j;
                                                else
                                                    z = j;
                                            }
                                            
                                            if (j >= 30)
                                            {
                                                name.setText(strobj[1].subSequence(0, z) + "\n");
                                                name.append(strobj[1].subSequence(z + 1, zz) + "\n");
                                                name.append(strobj[1].subSequence(zz + 1, strobj[1].length()) + "\n");
                                                break;
                                            }
                                        }
                                    }
                                    else if (strobj[1].length() >= 15)
                                    {
                                        int z = 0;
                                        for (int j = 0; j < strobj[1].length(); j++)
                                        {
                                            name.setText(strobj[1] + "\n");
                                            if (strobj[1].charAt(j) == ' ')
                                                z = j;
                                            
                                            if (j >= 15)
                                            {
                                                name.setText(strobj[1].subSequence(0, z) + "\n");
                                                name.append(strobj[1].subSequence(z + 1, strobj[1].length()) + "\n");
                                                break;
                                            }
                                        }
                                    }
                                    else
                                        name.setText(strobj[1] + "\n");

                                    zip.setText(strobj[2]);
                                    if (strobj[4].equals("Cash") || strobj[4].equals("GCash"))
                                    {
                                        price.setTextColor(Color.BLACK);
                                        price.setText(strobj[5]);
                                    }
                                    else if (strobj[4].equals("Ticket"))
                                    {
                                        price.setTextColor(Color.BLUE);
                                        price.setText("Ticket");
                                    }
                                    else if (strobj[4].equals("Free"))
                                    {
                                        price.setTextColor(Color.rgb(0, 180, 0));
                                        price.setText("FREE!");
                                    }
                                    else if (strobj[4].equals("Unknown"))
                                    {
                                        price.setTextColor(Color.RED);
                                        price.setText("Unknown");
                                    }
                                    type.setText(strobj[14]);
                                    subtype.setText(strobj[15].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "\n"));

                                    name.setGravity(Gravity.LEFT);
                                    name.setTextColor(Color.BLACK);
                                    name.setPadding(0, 2, 1, 2);
                                    row.addView(name);

                                    zip.setGravity(Gravity.CENTER);
                                    zip.setTextColor(Color.BLACK);
                                    zip.setPadding(5, 2, 5, 2);
                                    row.addView(zip);

                                    price.setGravity(Gravity.CENTER);
                                    price.setPadding(5, 2, 5, 2);
                                    row.addView(price);

                                    type.setGravity(Gravity.CENTER);
                                    type.setTextColor(Color.BLACK);
                                    type.setPadding(5, 2, 5, 2);
                                    row.addView(type);

                                    subtype.setGravity(Gravity.CENTER);
                                    subtype.setTextColor(Color.BLACK);
                                    subtype.setPadding(5, 2, 1, 2);
                                    row.addView(subtype);

                                    String[] arr = new String[9];
                                    arr[0] = (strobj[1]);
                                    arr[1] = (strobj[9]);
                                    arr[2] = (strobj[3]);
                                    arr[3] = (strobj[6]);
                                    arr[4] = (strobj[4]);
                                    arr[5] = (strobj[5]);
                                    arr[6] = (strobj[10]);
                                    arr[7] = (strobj[11]);
                                    arr[8] = (strobj[22]);
                                    row.setTag(arr);

                                    row.setOnLongClickListener(new OnLongClickListener()
                                    {

                                        public boolean onLongClick(View v)
                                        {

                                            lclick = true;
                                            String[] temp = (String[]) v.getTag();
                                            temp[8] = temp[8].replace("\"", "");
                                            temp[8] = temp[8].replace("]]", "");
                                            ID = Integer.parseInt(temp[8]);
                                            registerForContextMenu(v);
                                            return false;
                                        }

                                    });
                                    row.setOnClickListener(new OnClickListener()
                                    {
                                        public void onClick(View v)
                                        {
                                            if (!lclick)
                                            {
                                                try
                                                {
                                                    String[] arr = (String[]) v.getTag();
                                                    Intent intent = new Intent(context, PageActivity.class);
                                                    intent.putExtra("arr", arr);
                                                    startActivity(intent);
                                                } catch (Exception e)
                                                {
                                                }
                                            }
                                            lclick = false;
                                        }
                                    });

                                    table.addView(row);
                                }
                                else
                                    empty = true;
                    } catch (Exception e)
                    {
                        Toast.makeText(context, "The if statement!", 1).show();
                        e.printStackTrace();
                    }
                }//end while

            if (empty)
            {
                row = new TableRow(context);
                name = new TextView(context);
                if (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getOrientation() % 2 != 0)
                    row.setGravity(Gravity.CENTER);
                name.setText("No results found!");
                name.setTextColor(Color.RED);
                row.addView(name);
                table.addView(row);
            }
            else
            {
                Button prev = new Button(context);
                Button next = new Button(context);
                prev.setText("Previous");
                next.setText("Next");
                row = new TableRow(context);
                if (((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getOrientation() % 2 != 0)
                    row.setGravity(Gravity.CENTER);
                if (y > 39)
                    prev.setOnClickListener(new OnClickListener()
                    {
                        public void onClick(View v)
                        {
                            y -= 20;
                            if (x > 39)
                                x -= 40;
                            c = cStack.pop();
                            c = cStack.pop();
                            table.removeAllViews();
                            handler.post(results);
                        }
                    });
                next.setOnClickListener(new OnClickListener()
                {
                    public void onClick(View v)
                    {
                        y += 20;
                        table.removeAllViews();
                        handler.post(results);
                    }
                });
                row.addView(prev);
                row.addView(next);
                table.addView(row);
                //                        }
            }
        }

    };

    public boolean searchName(String[] obj) throws Exception
    {
        String[] temp = obj[1].split("_");
        String[] search = sZip.split(" ");
        int zip = -1;
        for (int z = 0; z < search.length; z++)
        {
            if (search[z].toLowerCase().equals(search[z].toUpperCase()) && search[z].length() == 5)
                zip = z;
        }
        for (int z = 0; z < temp.length; z++)
        {
            for (int x = 0; x < search.length; x++)
            {
                if (zip != -1)
                {
                    if (temp[z].toLowerCase().indexOf(search[x].toLowerCase()) != -1 && obj[2].equals(search[zip]))
                        return true;
                }
                else
                {
                    if (temp[z].toLowerCase().indexOf(search[x].toLowerCase()) != -1)
                        return true;
                }
            }
        }
        return false;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo info)// called when the phone's 'menu' button is pressed
    {
        super.onCreateContextMenu(menu, v, info);// returns... something.
        MenuInflater menuInflater = getMenuInflater();// makes a menuInflater
        menuInflater.inflate(R.menu.event_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item)// when an item is selected
    {
        try
        {
            switch (item.getItemId())
            {
            case R.id.Report:
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI("http://evintr.com/report.php?ID=" + ID));
                client.execute(request);
                Toast.makeText(context, "Thanks! Event Reported.", 1).show();
                break;

            case R.id.Share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(android.content.Intent.EXTRA_TEXT, "http://evintr.com/page.html?ID=" + ID);
                startActivity(Intent.createChooser(share, "Share via:"));
                break;
            }
        } catch (Exception e)
        {
            Toast.makeText(context, "Something broke. Sorry about that.", 1).show();
        }
        return super.onOptionsItemSelected(item);
    }
}