package com.example.studentmanager_system.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentmanager_system.R;
import com.example.studentmanager_system.Util.myDatabaseHelper;


/**
 * 登录主界面，主界面就先把数据库中的表先建好
 */
public class MainActivity extends AppCompatActivity {
   private long exit_time; //用于实现按两次返回键退出
    private Button admin;
    private Button student;
    private myDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin = (Button) findViewById(R.id.main_activity_admin);
        student = (Button) findViewById(R.id.main_activity_student);
        dbHelper = myDatabaseHelper.getInstance(this);
        dbHelper.getWritableDatabase();


        //跳转到管理员登录界面
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, admin_login_activity.class);
                startActivity(intent);
            }
        });


        //跳转到学生登录界面
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this,student_login_activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    //按两次back键退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //获取按键并比较两次按back的时间大于2s不退出，否则退出
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exit_time > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exit_time = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
