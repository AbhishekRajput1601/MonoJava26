package com.code;

public class User {
    private int id;
    private String name;
    private int age;
    private String mobileNo;
    private String departmentName;
    private String courseName;

    public User() {}


    public User(int id, String name, int age, String mobileNo, String departmentName, String courseName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobileNo = mobileNo;
        this.departmentName = departmentName;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mobileNo='" + mobileNo + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
