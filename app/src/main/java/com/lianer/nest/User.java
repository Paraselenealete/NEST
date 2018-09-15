package com.lianer.nest;

import android.databinding.BaseObservable;

public class User extends BaseObservable{
  private String firstName;
  private String lastName;

  public User(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
  }

  public String getFirstName() {
      return this.firstName;
  }

  public String getLastName() {
      return this.lastName;
  }

  public void setLastName(String lastName) {
      this.lastName = lastName;
      notifyChange();
  }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
      notifyChange();
  }

    public void clickMe() {
        setLastName("点击事件实现");
    }
}