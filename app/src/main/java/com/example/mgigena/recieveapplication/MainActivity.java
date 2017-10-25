package com.example.mgigena.recieveapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    ImageSwitcher mImageSwitcher1, mImageSwitcher2, mImageSwitcher3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mImageSwitcher1 = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        mImageSwitcher2 = (ImageSwitcher) findViewById(R.id.imageSwitcher2);
        mImageSwitcher3 = (ImageSwitcher) findViewById(R.id.imageSwitcher3);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            } else if (type.startsWith("image/")) {
                handleSendImage(intent);
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent);
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            mTextView.setText(sharedText);
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            mImageSwitcher1.setImageURI(imageUri);
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            mImageSwitcher2.setImageURI(imageUris.get(0));
            mImageSwitcher3.setImageURI(imageUris.get(1));
        }
    }
}
