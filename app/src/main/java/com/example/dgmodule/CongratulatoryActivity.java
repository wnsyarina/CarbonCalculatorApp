package com.example.dgmodule;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CongratulatoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulatory);

        // Initialize the button
        Button afterCongratsButton = findViewById(R.id.afterCongratsButton);

        // Set OnClickListener for the button
        afterCongratsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to MainActivity
                Intent intent = new Intent(CongratulatoryActivity.this, MainActivity.class);
                startActivity(intent);

                // Optional: Close the current activity if you don't want it in the back stack
                finish();
            }
        });
    }
}