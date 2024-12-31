package com.example.dgmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        EditText goal1EditText = findViewById(R.id.goal1);
        EditText goal2EditText = findViewById(R.id.goal2);
        EditText goal3EditText = findViewById(R.id.goal3);
        Button saveGoalButton = findViewById(R.id.saveGoalButton);

        // Set click listener for the Save Goal button
        saveGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditText
                String goal1 = goal1EditText.getText().toString();
                String goal2 = goal2EditText.getText().toString();
                String goal3 = goal3EditText.getText().toString();

                // Create an Intent to navigate to DisplayActivity
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("GOAL1", goal1);
                intent.putExtra("GOAL2", goal2); // Pass Goal 2
                intent.putExtra("GOAL3", goal3);// Pass the goal to the next activity
                startActivity(intent);
            }
        });
    }
}

