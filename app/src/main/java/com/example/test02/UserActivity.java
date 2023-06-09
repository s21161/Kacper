package com.example.test02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class UserActivity extends AppCompatActivity implements View.OnClickListener  {

    private TextView mEmailTextView;
    private TextView mTokenTextView;
    private Button heatChart;
    private Button newDevice;
    private Button notifications;
    private String token; // Deklaracja zmiennej token

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mEmailTextView = findViewById(R.id.emailTextView);
        mTokenTextView = findViewById(R.id.tokenTextView);

        // odczytaj token z Intentu i wyświetl go w TextView
        token = getIntent().getStringExtra("auth_token"); // Przypisanie wartości do zmiennej token
        mTokenTextView.setText(token);

        // odczytaj email z SharedPreferences i wyświetl go w TextView
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        mEmailTextView.setText("Witaj " + email);

        heatChart = findViewById(R.id.button_chart);
        heatChart.setOnClickListener(this);

        newDevice = findViewById(R.id.button_add_device);
        newDevice.setOnClickListener(this);

        notifications = findViewById(R.id.button_notifications);
        notifications.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_chart:
                Intent deviceIntent = new Intent(this, ChartActivity.class);
                deviceIntent.putExtra("auth_token", token);
                startActivity(deviceIntent);
                break;
            case R.id.button_add_device:
                startActivity(new Intent(this, NewDeviceActivity.class));
                break;
            case R.id.button_notifications:
                startActivity(new Intent(this, NotificationsActivity.class));
                break;
        }
    }
}