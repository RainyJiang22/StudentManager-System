package com.example.studentmanager_system.Activity;

import android.app.Activity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.studentmanager_system.R;


/**
 * 学生主界面
 */
public class studentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);
    }
}