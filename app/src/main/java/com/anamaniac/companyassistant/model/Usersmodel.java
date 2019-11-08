package com.anamaniac.companyassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Usersmodel implements Parcelable {
    private String Firstname;
    private String Surname;
    private String Email;
    private String Phonenumber;
    private String Url;

    public Usersmodel(){

    }

    protected Usersmodel(Parcel in) {
        Firstname = in.readString();
        Surname = in.readString();
        Email = in.readString();
        Phonenumber = in.readString();
        Url = in.readString();
    }

    public static final Creator<Usersmodel> CREATOR = new Creator<Usersmodel>() {
        @Override
        public Usersmodel createFromParcel(Parcel in) {
            return new Usersmodel(in);
        }

        @Override
        public Usersmodel[] newArray(int size) {
            return new Usersmodel[size];
        }
    };

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.Phonenumber = phonenumber;
    }
    public String getUrl(){return Url;}
    public void setUrl(String url)
    {
        this.Url = url;
    }
    public Usersmodel(String firstname, String surname, String email, String phone,String url) {
        Firstname = firstname;
        Surname = surname;
        Email = email;
        Phonenumber = phone;
        Url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Firstname);
        dest.writeString(Surname);
        dest.writeString(Email);
        dest.writeString(Phonenumber);
        dest.writeString(Url);
    }
}