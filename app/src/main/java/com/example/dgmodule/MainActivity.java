package com.example.dgmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private DGDatabase dailyGoalsdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database
        dailyGoalsdb = DGDatabase.getDatabase(this);

        // Initialize UI components
        EditText goal1EditText = findViewById(R.id.goal1);
        EditText goal2EditText = findViewById(R.id.goal2);
        EditText goal3EditText = findViewById(R.id.goal3);
        Button saveGoalButton = findViewById(R.id.saveGoalButton);

        // Set click listener for the Save Goal button
        saveGoalButton.setOnClickListener(v -> {
            // Get text from EditText
            String goal1 = goal1EditText.getText().toString();
            String goal2 = goal2EditText.getText().toString();
            String goal3 = goal3EditText.getText().toString();

            if (goal1.isEmpty() || goal2.isEmpty() || goal3.isEmpty()) {
                Toast.makeText(this, "Please fill all goals!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save data into the database
            Executors.newSingleThreadExecutor().execute(() -> {
                Goal goal = new Goal();
                goal.goal1 = goal1;
                goal.goal2 = goal2;
                goal.goal3 = goal3;
                dailyGoalsdb.goalDao().insert(goal); // Insert data into the database
            });

            // Optional: Navigate to DisplayActivity after saving
            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            startActivity(intent);
        });
    }
}