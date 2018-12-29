package com.example.studentmanager_system.Util;

/**
 * 保存学生信息的实体类
 */
public class Student {
    private String name;
    private String sex;
    private String id;//学号
    private String password;//学生登录密码
    private String number;//手机号
    private int MathScore;
    private int ChineseScore;
    private int EnglishScore;
    private int order;//名次

    public Student(int chineseScore, int englishScore, String id, int mathScore, String name, String number, String password, String sex,int order) {

        this.id = id;
        this.name = name;
        this.number = number;
        this.password = password;
        this.sex = sex;
        ChineseScore = chineseScore;
        EnglishScore = englishScore;
        MathScore = mathScore;
        this.order=order;
    }

    public int getChineseScore() {
        return ChineseScore;
    }
    public int getEnglishScore() {
        return EnglishScore;
    }
    public String getId() {
        return id;
    }
    public int getMathScore() {
        return MathScore;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getPassword() {
        return password;
    }
    public String getSex() {
        return sex;
    }
    public int getOrder() {
        return order;
    }


}
