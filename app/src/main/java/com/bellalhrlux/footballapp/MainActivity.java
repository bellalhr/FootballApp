package com.bellalhrlux.footballapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bellalhrlux.footballapp.home.HomeActivity;

public class MainActivity extends AppCompatActivity {
    Button taskOneBtn, taskTwoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
}
