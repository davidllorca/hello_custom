package com.davidllorca.hellocustom;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 *  Simple example with CheckBox, RadioButton, Datepicker, Toast, AlertDialog.
 *
 * Created by David Llorca <davidllorcabaron@gmail.com> on 7/7/14.
 */
public class MainActivity extends ActionBarActivity {
    /* Views */
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Views instances
        Button button = (Button) findViewById(R.id.hello);
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);

        // Checkbox listener
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = isChecked ? View.VISIBLE : View.GONE;
                View view = findViewById(R.id.datePicker);
                view.setVisibility(visibility);
                view = findViewById(R.id.timePicker);
                view.setVisibility(visibility);
            }
        });

        // Button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Out string
                String saludation = getResources().getString(R.string.hello);

                // View references
                text = (EditText) findViewById(R.id.entry);

                // Check if field is empty
                if (isEmptyField()) {
                    showAlertDialog();
                    showToast();
                    return;
                }
                // Extract string from field
                String enteredName = text.getText().toString();

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup01);
                if (R.id.rbSr == radioGroup.getCheckedRadioButtonId()) {
                    // for Mr
                    saludation = saludation + " " + getResources().getString(R.string.rbMr) + " ";
                } else {
                    // for Ms
                    saludation = saludation + " " + getResources().getString(R.string.rbMs) + " ";
                }
                saludation += enteredName;

                // Getting time and date
                CheckBox timeCheckBox = (CheckBox) findViewById(R.id.checkbox);
                if (timeCheckBox.isChecked()) {
                    // View references
                    DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                    TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                    // Date
                    String dateToShow = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
                    // Add time
                    dateToShow += " " + time.getCurrentHour() + ":" + time.getCurrentMinute();
                    saludation += " " + dateToShow;
                }

                /*
                //Show string saludation in this Activity
                TextView out = (TextView) findViewById(R.id.out);
                out.setText(saludation);
                */

                //Show saludation in new Activity
                Intent intent = new Intent(MainActivity.this, SaludationActivity.class);
                intent.putExtra("saludation", saludation);
                startActivity(intent);
            }
        });


    }

    /**
     * Check if name's field is empty.
     *
     * @return true if field is empty or contains white spaces, false otherwise.
     */
    private boolean isEmptyField() {
        return "".equals(text.getText().toString().trim());
    }

    /**
     * Show info message by Dialog.
     */
    private void showAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(getResources().getString(R.string.noNameMsq));
        alert.setPositiveButton(android.R.string.ok, null);
        alert.show();
    }

    /**
     * Show info message by toast.
     */
    private void showToast(){
        Toast.makeText(this, getResources().getString(R.string.noNameMsq), Toast.LENGTH_SHORT).show();
    }
}
