package com.florianmski.urbandictionary.ui.activities;




import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.florianmski.urbandictionary.R;

public class NotifyMessage extends Activity {
	WebView web;
	String z="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TextView txt=new TextView(this);
		Bundle b=getIntent().getExtras();
		 z=b.getString("keyp");
		//Toast.makeText(getApplicationContext(),z, Toast.LENGTH_LONG).show();
		//txt.setText("Activity after click on notification");
		setContentView(R.layout.activity_search);
		web=(WebView)findViewById(R.id.webview);
		web.loadUrl("http://www.urbandictionary.com/define.php?term="+z);
	}
	
}
