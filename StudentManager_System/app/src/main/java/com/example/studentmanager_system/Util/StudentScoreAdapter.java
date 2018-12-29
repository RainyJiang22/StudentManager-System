package com.example.studentmanager_system.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.studentmanager_system.R;

import java.util.List;


/**
 * 自定义学生总分数适配器
 */
public class StudentScoreAdapter extends ArrayAdapter<Student> {
    private int resourceId;

    public StudentScoreAdapter(Context context, int resource, List<Student> objects) {
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
            viewHolder.student_order = (TextView) view.findViewById(R.id.student_score_order);
            viewHolder.student_name = (TextView) view.findViewById(R.id.student_score_name);
            viewHolder.student_id = (TextView) view.findViewById(R.id.student_score_id);
            viewHolder.student_total_score = (TextView) view.findViewById(R.id.student_score_total);


            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        int order = student.getOrder();
        viewHolder.student_order.setText(String.valueOf(order));
        viewHolder.student_name.setText(student.getName());
        viewHolder.student_id.setText(student.getId());
        int sum = student.getChineseScore() + student.getEnglishScore() + student.getMathScore();
        viewHolder.student_total_score.setText(String.valueOf(sum));
        return view;

    }

    class ViewHolder {
        TextView student_name;
        TextView student_id;
        TextView student_total_score;
        TextView student_order;

    }
}
