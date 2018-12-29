package com.example.studentmanager_system.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanager_system.R;
import com.example.studentmanager_system.Util.myDatabaseHelper;


/**
 * 学生登录界面
 */
public class student_login_activity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button login;
    private myDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login_layout);


        name = (EditText) findViewById(R.id.student_loging_activity_name_input);
        password = (EditText) findViewById(R.id.student_login_activity_password_input);
        login = (Button) findViewById(R.id.student_login_activity_login);

        dbHelper = myDatabaseHelper.getInstance(this);

        //点击登录按钮,作如下判断
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentId = name.getText().toString();
                String studentPassword = password.getText().toString();
                //当学生学号和密码都不为空的时候，开始判断
                if (!TextUtils.isEmpty(studentId)&& !TextUtils.isEmpty(studentPassword)){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.rawQuery("select password from student where id=?", new String[]{studentId});
                    if (cursor.moveToNext()){
                        String password = cursor.getString(cursor.getColumnIndex("password"));
                        if (password.equals(studentPassword)){
                            //启动学生登录后的界面并将学生的账户（id）传过去
                            Intent intent = new Intent(student_login_activity.this, studentActivity.class);
                            intent.putExtra("id", name.getText().toString());
                            startActivity(intent);
                        }else{
                            Toast.makeText(student_login_activity.this,"密码错误请重新输入",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(student_login_activity.this, "该学号未注册，请联系老师", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
