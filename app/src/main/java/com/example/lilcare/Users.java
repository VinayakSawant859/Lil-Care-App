package com.example.lilcare;

public class Users {

    private String childPhoto;
    private String childName;
    private String childAge;
    private String parentName;
    private String parentEmail;
    private String newPassword;
    private String confPassword;
    private String Phone1;
    private String Phone2;
    private String pAddress;
    private String medicState;

    public Users(){}
    public Users(String childPhoto,
                 String childName, String childAge,
                 String parentName, String parentEmail,
                 String newPassword, String confPassword,
                 String Phone1, String Phone2,
                 String pAddress, String medicState){

        this.childPhoto = childPhoto;
        this.childName = childName;
        this.childAge = childAge;
        this.parentName = parentName;
        this.parentEmail = parentEmail;
        this.newPassword = newPassword;
        this.confPassword = confPassword;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.pAddress = pAddress;
        this.medicState = medicState;
    }

    public String getChildPhoto() {
        return childPhoto;
    }

    public void setChildPhoto(String childPhoto) {
        this.childPhoto = childPhoto;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String phone1) {
        Phone1 = phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String phone2) {
        Phone2 = phone2;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getMedicState() {
        return medicState;
    }

    public void setMedicState(String medicState) {
        this.medicState = medicState;
    }
}
