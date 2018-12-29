package com.example.studentmanager_system.Util;


/**
 * 保存学生信息的实体类
 */
public class Student {
    private String name;
    private String sex;
    private String id; //学号
    private String password;
    private String number; //手机号
    private int MathScore;
    private int ChineseScore;
    private int EnglishScore;
    private int order;//名次


    public Student(String name, String sex, String id, String password, String number, int mathScore, int chineseScore, int englishScore, int order) {
        this.name = name;
        this.sex = sex;
        this.id = id;
        this.password = password;
        this.number = number;
        MathScore = mathScore;
        ChineseScore = chineseScore;
        EnglishScore = englishScore;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }

    public int getMathScore() {
        return MathScore;
    }

    public int getChineseScore() {
        return ChineseScore;
    }

    public int getEnglishScore() {
        return EnglishScore;
    }

    public int getOrder() {
        return order;
    }
}
