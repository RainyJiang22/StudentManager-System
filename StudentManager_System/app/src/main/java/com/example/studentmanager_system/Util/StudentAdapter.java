package com.example.studentmanager_system.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentmanager_system.R;
import com.example.studentmanager_system.Util.Student;

import java.util.List;


/**
 * 自定义学生信息适配器（基本信息）
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    private int resourceId;

    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.student_name = (TextView) view.findViewById(R.id.student_name);
            viewHolder.student_id = (TextView) view.findViewById(R.id.student_id);
            viewHolder.student_image = (ImageView) view.findViewById(R.id.student_image);
            viewHolder.student_image.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.student_name.setText(student.getName());
        viewHolder.student_id.setText(student.getId());
        String sex = student.getSex();
        if (sex.equals("男")) {
            viewHolder.student_image.setImageResource(R.drawable.man);

        } else if (sex.equals("女")) {
            viewHolder.student_image.setImageResource(R.drawable.woman);
        }


        return view;

    }

    class ViewHolder {
        ImageView student_image;
        TextView student_name;
        TextView student_id;

    }

}
