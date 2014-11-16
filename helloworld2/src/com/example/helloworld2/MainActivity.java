package com.example.helloworld2;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.ImageView;



import android.os.Handler;
import android.webkit.WebView;

//public class MainActivity extends Activity {
//
//String url = "http://192.168.1.187:8080/camera.html";
//
//@Override
//protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    //Remove title bar
//    //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//    //Remove notification bar
// //   this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//    setContentView(R.layout.activity_mini_bot_controller);
//
//    WebView webview = (WebView) findViewById(R.id.browser);
//    Intent intent = getIntent();
//    String myIP = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//    webview.getSettings().setJavaScriptEnabled(true);
//    webview.setWebViewClient(new MyWebViewClient ());
//    webview.loadUrl(url);
//
//
//}

public class MainActivity extends Activity {
private VideoView myVideoView;
private int position = 0;
private ProgressDialog progressDialog;
private MediaController mediaControls;

ImageView iv;
ProgressDialog pd;
Bitmap image;
String ImageURL = "http://129.161.52.13:8080/shot.jpg";
//String ImageURL = "http://i.stack.imgur.com/kr2nh.png";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //set the main layout of the activity
    setContentView(R.layout.activity_main);
    
    String VideoURL = "http://100.251.75.10:8080/video.mjpeg";
    iv = (ImageView) findViewById(R.id.imageView1);

    
    iv = (ImageView) findViewById(R.id.imageView1);
    //while(true){
    	
    	pd = new ProgressDialog(this);
        pd.setMessage("Loading..");
        new TheTask().execute();    
    	Bitmap blah = null;
//    	try {
    		//blah = downloadBitmap(ImageURL);
//    		if (blah != null)
//    		{
//    			iv.setImageBitmap(blah);
//    		}
			//Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
   	
   // }
//:8080/videofeed?dummy=mjpg
    
    //set media controller buttons
/*    if(mediaControls == null)
    {
    	mediaControls = new MediaController(MainActivity.this);
    }
*/    //init the videoview
/*    myVideoView = (VideoView) findViewById(R.id.video_view);
    
    //create progress bar while the video file is loading 
    progressDialog = new ProgressDialog(MainActivity.this);
    //set a message for the progress bar
    progressDialog.setTitle("copied video view example");
    // set message for progress bar
	progressDialog.setMessage("Loading...");
	progressDialog.setCancelable(false);
	// show the progress bar
	progressDialog.show();

	try {
		//set the media controller in the VideoView
		myVideoView.setMediaController(mediaControls);

		//set the uri of the video to be played
		myVideoView.setVideoURI(Uri.parse(VideoURL));

	} catch (Exception e) {
		Log.e("Error", e.getMessage());
		e.printStackTrace();
	}
*/	
	//myVideoView.requestFocus();
	//we also set an setOnPreparedListener in order to know when the video file is ready for playback
	//myVideoView.setOnPreparedListener(new OnPreparedListener() {
	
//		public void onPrepared(MediaPlayer mediaPlayer) {
//			// close the progress bar and play the video
//			progressDialog.dismiss();
//			//if we have a position on savedInstanceState, the video playback should start from here
//			myVideoView.seekTo(position);
//			if (position == 0) {
//				myVideoView.start();
//			} else {
//				//if we come from a resumed activity, video playback will be paused
//				myVideoView.pause();
//			}
//		}
	//});

}



@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
	super.onSaveInstanceState(savedInstanceState);
	//we use onSaveInstanceState in order to store the video playback position for orientation change
	savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
	myVideoView.pause();
}

@Override
public void onRestoreInstanceState(Bundle savedInstanceState) {
	super.onRestoreInstanceState(savedInstanceState);
	//we use onRestoreInstanceState in order to play the video playback from the stored position 
	position = savedInstanceState.getInt("Position");
	myVideoView.seekTo(position);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
        return true;
    }
    return super.onOptionsItemSelected(item);
}

class TheTask extends AsyncTask<Void,Void,Void>
{

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        pd.show();
    }


    @Override
    protected Void doInBackground(Void... params) {
        // TODO Auto-generated method stub
        try
        {
        //URL url = new URL( "http://a3.twimg.com/profile_images/670625317/aam-logo-v3-twitter.png");


        image = downloadBitmap(ImageURL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        pd.dismiss();
        if(image!=null)
        {
            iv.setImageBitmap(image);
            new TheTask().execute();    

        }

    }   
}
private Bitmap downloadBitmap(String url) {
    // initilize the default HTTP client object
    final DefaultHttpClient client = new DefaultHttpClient();
    Bitmap bmpImage= null;

    //forming a HttoGet request 
    final HttpGet getRequest = new HttpGet(url);
    try {

        HttpResponse response = client.execute(getRequest);

        //check 200 OK for success
        final int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode != HttpStatus.SC_OK) {
            Log.w("ImageDownloader", "Error " + statusCode + 
                    " while retrieving bitmap from " + url);
            return null;

        }

        final HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream inputStream = null;
            try {
                // getting contents from the stream 
                inputStream = entity.getContent();

                // decoding stream data back into image Bitmap that android understands
                bmpImage = BitmapFactory.decodeStream(inputStream);


            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                entity.consumeContent();
            }
        }
    } catch (Exception e) {
        // You Could provide a more explicit error message for IOException
        getRequest.abort();
        Log.e("ImageDownloader", "Something went wrong while" +
                " retrieving bitmap from " + url + e.toString());
    } 

    return bmpImage;
}



}

