package com.example.strangers.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Serializable, Parcelable{
	
	private static final long serialVersionUID = 1L;
	
	
	/* ******************** *
	 * ****** Fields ****** *
	 * ******************** */
	
	private String login;
	private String password;

	
	/* ************************* *
	 * ****** Constructor ****** *
	 * ************************* */
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	/* ********************* *
	 * ****** Getters ****** *
	 * ********************* */
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}

	
	/* ********************* *
	 * ****** Setters ****** *
	 * ********************* */
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/* ********************************** *
	 * ****** Parcelable function ****** *
	 * ********************************** */

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(this.login);
		out.writeString(this.password);
	}

	// this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private User(Parcel in) {
        this.login = in.readString();
        this.password = in.readString();
        
    }
	
}
