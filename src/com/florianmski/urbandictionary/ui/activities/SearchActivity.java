package com.florianmski.urbandictionary.ui.activities;

import java.util.Random;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.SearchView;

//import com.crashlytics.android.Crashlytics;
import com.florianmski.urbandictionary.R;
import com.florianmski.urbandictionary.ui.fragments.TermFragment;

public class SearchActivity extends TranslucentActivity
{
	private static final int NOTIFY_ME_ID=9;
	LoginDataBaseAdapter ldp;
	WebView web;
	String y="";
	int x;
	private SearchView searchView1;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
    	ldp=new LoginDataBaseAdapter(this);
    	 ldp=ldp.open();
    	Random rn=new Random();
    	x=rn.nextInt(10)+1;
        super.onCreate(savedInstanceState);
        //Crashlytics.start(this);
        setContentView(R.layout.activity_search);

        if(savedInstanceState == null)
        {
            TermFragment f = TermFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_term, f).commit();
        }
        web=(WebView)findViewById(R.id.webview);
        
        ldp.insertEntry("Obstinate");
        ldp.insertEntry("Impetuous");
        ldp.insertEntry("HeadStrong");
        ldp.insertEntry("Lynch");
        ldp.insertEntry("Palliate");
        ldp.insertEntry("Languish");
        ldp.insertEntry("Bizaare");
        ldp.insertEntry("Adorable");
        ldp.insertEntry("Discernible");
        ldp.insertEntry("Atrocious");
        ldp.insertEntry("Probation");
        
       y=ldp.getSinlgeEntry1(x);
        
        notify1(1);
        
      

    }
    private void notify1(int info)
    {
    	//NotificationManager from the Service
		final NotificationManager mgr=
			(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
		// Step -2 Build Own Notification , This is title of Message
		Notification note=new Notification(R.drawable.dd,
														"WORD OF THE DAY IS",
														System.currentTimeMillis());
		
		// This pending intent will open after notification click
		// Step -3 This place ur message in the Queue
		Intent b=new Intent("com.florianmski.urbandictionary.ui.activities.NotifyMessage");
		 b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		b.putExtra("keyp",y);
		PendingIntent i = PendingIntent.getActivity(getBaseContext(),0,b,PendingIntent.FLAG_UPDATE_CURRENT);
		//startActivity(b);
		 
	    
		
		// Step -4 Detail of the Message
		note.setLatestEventInfo(getBaseContext(), "WORD IS:"+y,
								"LEARN THE WORD",i);
		
		//After uncomment this line you will see number of notification arrived
		//note.number=2;
		mgr.notify(NOTIFY_ME_ID, note);
    } 
    
}
