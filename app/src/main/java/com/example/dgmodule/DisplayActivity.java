package com.example.dgmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Initialize TextView
        TextView goalDisplay1 = findViewById(R.id.goalDisplay1);
        TextView goalDisplay2 = findViewById(R.id.goalDisplay2);
        TextView goalDisplay3 = findViewById(R.id.goalDisplay3);

        // Initialize CheckBoxes
        CheckBox checkBoxGoal1 = findViewById(R.id.checkBoxGoal1);
        CheckBox checkBoxGoal2 = findViewById(R.id.checkBoxGoal2);
        CheckBox checkBoxGoal3 = findViewById(R.id.checkBoxGoal3);

        // Retrieve the data passed via Intent
        String goal1 = getIntent().getStringExtra("GOAL1");
        String goal2 = getIntent().getStringExtra("GOAL2");
        String goal3 = getIntent().getStringExtra("GOAL3");
        // Display the goal in the TextView
        goalDisplay1.setText(goal1);
        goalDisplay2.setText(goal2);
        goalDisplay3.setText(goal3);

        // Listener to check if all checkboxes are ticked
        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
            if (checkBoxGoal1.isChecked() && checkBoxGoal2.isChecked() && checkBoxGoal3.isChecked()) {
                // Navigate to the CongratulatoryActivity
                Intent intent = new Intent(DisplayActivity.this, CongratulatoryActivity.class);
                startActivity(intent);
            }
        };

        // Set the listener for all checkboxes
        checkBoxGoal1.setOnCheckedChangeListener(listener);
        checkBoxGoal2.setOnCheckedChangeListener(listener);
        checkBoxGoal3.setOnCheckedChangeListener(listener);

        // Initialize "Clear All" Button
        Button clearButton = findViewById(R.id.DGclearButton);

        // Set OnClickListener for "Clear All" Button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(intent);

                // Optional: Close the current activity
                finish();
            }
        });
    }
}