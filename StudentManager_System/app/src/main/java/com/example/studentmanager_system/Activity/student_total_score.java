package com.example.studentmanager_system.Activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.studentmanager_system.R;
import com.example.studentmanager_system.Util.Student;
import com.example.studentmanager_system.Util.StudentScoreAdapter;
import com.example.studentmanager_system.Util.myDatabaseHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 显示学生总成绩排名的activity

 */
public class student_total_score extends Activity {
    private ListView total_score;
    private List<Student> list = new ArrayList<Student>();
    private myDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.student_toal_score_layout);
        dbHelper = myDatabaseHelper.getInstance(this);
        initInfo();//初始化数据
        StudentScoreAdapter adapter = new StudentScoreAdapter(student_total_score.this, R.layout.student_score_item, list);
        total_score = (ListView) findViewById(R.id.total_list_view);
        total_score.setAdapter(adapter);

    }


    //初始化数据
    private void initInfo() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from student order by mathScore+chineseScore+englishScore desc", null);
        int i = 0;
        while (cursor.moveToNext()) {
            i++;
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            int mathScore = cursor.getInt(cursor.getColumnIndex("mathScore"));
            int chineseScore = cursor.getInt(cursor.getColumnIndex("chineseScore"));
            int englishScore = cursor.getInt(cursor.getColumnIndex("englishScore"));
            db.execSQL("update  student set ranking=? where id=?",new String[]{String.valueOf(i),id});//将排名插入数据库
            list.add(new Student(chineseScore, englishScore, id, mathScore, name, number, password, sex, i));
        }
        cursor.close();

    }


}
