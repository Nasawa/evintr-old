package com.evintr.evintr;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.android.Facebook;

public class FacebookActivity extends Activity
{
    Context context;
    Handler handler = new Handler();
    String appID = "###########";
    LinearLayout fb;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.facebook);

        context = this.getBaseContext();

        fb = (LinearLayout) findViewById(R.id.fbroot);

        handler.post(getfb);
    }

    public final Runnable getfb = new Runnable()
    {
        public void run()
        {
            //"https://graph.facebook.com/me/events?"
            Facebook facebook = new Facebook(appID);
            try
            {
                Signature[] sigs = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
                for (Signature sig : sigs)
                {
                    Log.d("MyApp", "Signature hashcode : " + sig.hashCode());
                }
                String res = facebook.request("me/events");
                TextView t = new TextView(context);
                t.setText(res);
                fb.addView(t);

            } catch (Exception e)
            {
            }
        }
    };

}