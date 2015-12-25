package com.example.aj.microphone_camera_demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void startRecording(View v) {
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivityForResult(intent, 1);
    }

    public void startCamera(View v)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) //for voice recorder
        {
            if (data != null)
            {
                Uri savedUri = data.getData();
                Toast.makeText(this,"Saved: " + savedUri.getPath(), Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == 2)   //for camera
        {
            if(data != null)
            {
                //Fetch the data as a Bitmap Image
                Bitmap bp = (Bitmap) data.getExtras().get("data");

                //Set the image on ImageView
                imageView.setImageBitmap(bp);
            }
        }
    }


}
