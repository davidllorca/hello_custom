package com.davidllorca.hellocustom;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 7/7/14.
 */
public class SaludationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludation);
        // Get string from extras
        String saludation = getIntent().getExtras().getString("saludation");
        // View reference
        TextView out = (TextView) findViewById(R.id.out);
        // Set content in textview
        out.setText(saludation);
    }
}
