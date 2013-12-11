package com.example.t.to.speech;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements 
		TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */

	private TextToSpeech ts;
	private Button btnSpeak;
	private EditText txtText;

	static final String[] texts={"hai this appllication desgined by anand welcome to  android world"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ts = new TextToSpeech(this, this);

		btnSpeak = (Button) findViewById(R.id.btnSpeak);
		Button b =(Button)findViewById(R.id.button1);

		txtText = (EditText) findViewById(R.id.txtText);
		ts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
			
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status!=TextToSpeech.ERROR)
				{
					ts.setLanguage(Locale.ENGLISH);
					
					
			}
			}
		});
		  b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Random r  = new Random();
				String random = texts[r.nextInt(1)];
				ts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
				
			
			}
		});

		// button on click event
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				speakOut();
			}

		});
		
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (ts != null) {
			ts.stop();
			ts.shutdown();
		}
		super.onDestroy();
	}
	 @Override
		protected void onPause() {
			// TODO Auto-generated method stub
	    	if(ts!=null)
	    	{
	    		ts.stop();
	    		ts.shutdown();
	    		
	    	}
			super.onPause();
		}

	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = ts.setLanguage(Locale.ENGLISH);

		//tts.setPitch(10); // set pitch level

		//	 tts.setSpeechRate(2); // set speech speed rate

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			} else {
				btnSpeak.setEnabled(true);
				speakOut();
			}

		} else {
			Log.e("TTS", "Initilization Failed");
			
		}

	}

	private void speakOut() {

		String text = txtText.getText().toString();

		ts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}