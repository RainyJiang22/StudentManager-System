package com.example.studentmanager_system.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanager_system.R;
import com.example.studentmanager_system.Util.myDatabaseHelper;

/**
 * 学生主界面
 */
public class studentActivity extends Activity {
    private Button select;
    private Button changePassword;
    private myDatabaseHelper dbHelper;
    private String ID;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.student_layout);

        select = (Button) findViewById(R.id.student_activity_selectInfo);
        changePassword = (Button) findViewById(R.id.student_activity_changePassword);
        dbHelper = myDatabaseHelper.getInstance(this);
        intent = getIntent();
        //以AlertDialog的形式显示个人详细信息
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(studentActivity.this);
                builder.setTitle("个人信息");
                ID = intent.getStringExtra("id");//获取传入的学号用于查询详细信息
                StringBuilder sb = new StringBuilder();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from student where id=?", new String[]{ID});
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    int mathScore = cursor.getInt(cursor.getColumnIndex("mathScore"));
                    int chineseScore = cursor.getInt(cursor.getColumnIndex("chineseScore"));
                    int englishScore = cursor.getInt(cursor.getColumnIndex("englishScore"));
                    int ranking = cursor.getInt(cursor.getColumnIndex("ranking"));
                    sb.append("姓名：" + name + "\n");
                    sb.append("学号：" + id + "\n");
                    sb.append("手机号：" + number + "\n");
                    sb.append("密码：" + password + "\n");
                    sb.append("数学成绩：" + mathScore + "\n");
                    sb.append("语文成绩：" + chineseScore + "\n");
                    sb.append("英语成绩：" + englishScore + "\n");
                    int sum = mathScore + chineseScore + englishScore;//总成绩
                    sb.append("总成绩：" + sum + "\n");
                    sb.append("名次：" + ranking + "\n");
                }
                cursor.close();
                builder.setMessage(sb.toString());
                builder.create().show();
            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(studentActivity.this);
                LayoutInflater factory = LayoutInflater.from(studentActivity.this);
                final View view = factory.inflate(R.layout.change_password_layout, null);
                builder.setView(view);
                builder.setTitle("修改密码");
                builder.setNegativeButton("取消", null);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    EditText firstPassword = (EditText) view.findViewById(R.id.student_change_password);
                    EditText secondPassword = (EditText) view.findViewById(R.id.student_change_password_second_password);
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String first = firstPassword.getText().toString();
                        String second = secondPassword.getText().toString();
                        if (!TextUtils.isEmpty(first) && !TextUtils.isEmpty(second)) {
                            if (first.matches("[0-9]{6}") && second.matches("[0-9]{6}")) {
                                if (second.equals(first)) {
                                    ID = intent.getStringExtra("id");//获取传入的学号用于修改密码
                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    db.execSQL("update student set password=? where id=?", new String[]{second, ID});
                                    Toast.makeText(studentActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(studentActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(studentActivity.this, "密码必须为6位数字", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(studentActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                builder.create().show();
            }
        });


    }
}
