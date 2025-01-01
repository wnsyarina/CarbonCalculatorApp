package com.example.dgmodule;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.Executors;

public class DisplayActivity extends AppCompatActivity {

    private DGDatabase dailyGoalsdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Initialize the database
        dailyGoalsdb = DGDatabase.getDatabase(this);

        // Initialize TextViews
        TextView goalDisplay1 = findViewById(R.id.goalDisplay1);
        TextView goalDisplay2 = findViewById(R.id.goalDisplay2);
        TextView goalDisplay3 = findViewById(R.id.goalDisplay3);

        // Initialize CheckBoxes
        CheckBox checkBoxGoal1 = findViewById(R.id.checkBoxGoal1);
        CheckBox checkBoxGoal2 = findViewById(R.id.checkBoxGoal2);
        CheckBox checkBoxGoal3 = findViewById(R.id.checkBoxGoal3);

        // Fetch the latest goals from the database
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Goal> goals = dailyGoalsdb.goalDao().getAllGoals();
            if (!goals.isEmpty()) {
                Goal latestGoal = goals.get(goals.size() - 1); // Fetch the latest entry
                runOnUiThread(() -> {
                    goalDisplay1.setText(latestGoal.goal1);
                    goalDisplay2.setText(latestGoal.goal2);
                    goalDisplay3.setText(latestGoal.goal3);
                });
            } else {
                runOnUiThread(() -> {
                    goalDisplay1.setText("No goals to display");
                    goalDisplay2.setText("No goals to display");
                    goalDisplay3.setText("No goals to display");
                });
            }
        });

        // Listener for checkboxes
        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
            if (checkBoxGoal1.isChecked() && checkBoxGoal2.isChecked() && checkBoxGoal3.isChecked()) {
                Intent intent = new Intent(DisplayActivity.this, CongratulatoryActivity.class);
                startActivity(intent);
            }
        };

        checkBoxGoal1.setOnCheckedChangeListener(listener);
        checkBoxGoal2.setOnCheckedChangeListener(listener);
        checkBoxGoal3.setOnCheckedChangeListener(listener);

        // Initialize "Clear All" Button
        Button clearButton = findViewById(R.id.DGclearButton);

        // Set OnClickListener for "Clear All" Button
        clearButton.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                dailyGoalsdb.goalDao().deleteAllGoals(); // Clear database
                runOnUiThread(() -> {
                    Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            });
        });
    }
}