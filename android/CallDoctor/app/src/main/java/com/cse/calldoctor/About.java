package com.cse.calldoctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;

import com.cse.calldoctor.VerticalMarqueeTextView.VerticalMarqueeTextView;

public class About extends AppCompatActivity {

    // Create an instance of the VerticalMarqueeTextView class
    // to be used with the above TextView.
    private VerticalMarqueeTextView VMTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        VMTV = (VerticalMarqueeTextView) findViewById(R.id.vmTextView);

        // Set the Text.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            VMTV.setText(Html.fromHtml(getString(R.string.about), Html.FROM_HTML_MODE_LEGACY));
        } else {
            VMTV.setText(Html.fromHtml(getString(R.string.about)));
        }

        // Set the VMTV movement method so that it can scroll.
        VMTV.setMovementMethod(new ScrollingMovementMethod());

        // Alternately you may also pass in the (long) duration between scroll
        // moves and the (int) pixelYOffSet amount that you wish to scroll by.
        // Using the following method calls:
//         VMTV.setDuration(200);
//         VMTV.setPixelYOffSet(5);

        // Optionally pause the marquee to wait for the Activity to start.
        VMTV.pauseMarquee();
    }

    @Override
    protected void onResume() {

        // Start or restart the Marquee if paused.
        if (VMTV.isPaused()) {
            VMTV.resumeMarquee();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {

        // Pause the Marquee when the Activity pauses.
        VMTV.pauseMarquee();
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        VMTV.stopMarquee();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
