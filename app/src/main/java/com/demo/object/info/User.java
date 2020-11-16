package com.demo.object.info;

public class User {
    private int Id;
    private String username;
    private  String password;
    private String fullname;
    private String birthday;
    private boolean gender;
    private String gmail;
    private String phone;
    private String address;
    private String dateJoin;
    private boolean status;

    public User(int id, String username, String password, String fullname, String birthday, boolean gender, String gmail, String phone, String address, String dateJoin, boolean status) {
        Id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.gmail = gmail;
        this.phone = phone;
        this.address = address;
        this.dateJoin = dateJoin;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
