 package com.catalina.webspringbootshop.dto;

 public class UserUpdate {

    private String username, email, city, password, password_confirmation, first_name, last_name, state, address, address2;
    private int postal_code;

    public UserUpdate() {
    }

    public String getCity() {
        return city;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public boolean passwordMatch() {

        return getPassword() != null && getPassword_confirmation() != null &&
                getPassword().equals(getPassword_confirmation());
    }

    public boolean isValidDetails() {
        return !getUsername().isEmpty() && !getEmail().isEmpty()
                && !getUsername().isEmpty() && !getPassword().isEmpty()
                && !getPassword_confirmation().isEmpty();
    }

    public String toString() {
        return "Username = " + getUsername() +
                "\nEmail = " + getEmail() +
                "\nName = " + getUsername() +
                "\nPassword = " + getPassword() +
                "\nPassword Confirm = " + getPassword_confirmation();
    }

}
