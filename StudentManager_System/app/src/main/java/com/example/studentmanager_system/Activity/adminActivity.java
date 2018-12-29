package com.example.studentmanager_system.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentmanager_system.R;

/**
 * 管理员主界面
 */
public class adminActivity extends Activity {
    private Button select;//查询学生信息按钮
    private Button add;//添加学生信息按钮
    private Button order;//查看总成绩排名按钮
    private TextView forceOffline;//强制下线

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);


        //初始化相关控件
        select = (Button) findViewById(R.id.admin_activity_select);
        add = (Button) findViewById(R.id.admin_activity_add);
        order = (Button) findViewById(R.id.admin_activity_order);
        forceOffline = (TextView) findViewById(R.id.admin_activity_forceOffline);

        /**
         * 查询学生信息
         */
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminActivity.this, studentinfoActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 添加学生信息
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminActivity.this, add_studentinfoActivity.class);
                intent.putExtra("haveData","false");
                startActivity(intent);
            }
        });


        /**
         * 查询学生总成绩
         */
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminActivity.this,student_total_score.class);
                startActivity(intent);
            }
        });

        /**
         * 强制下线
         */
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.OfflineBradcast");
                sendBroadcast(intent);
            }
        });

    }
}
