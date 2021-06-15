package com.example.mathappt;

public class Users {
    public String email;
    public String password;
    public String gender;
    public String username;
    public String id;
    Users()
    {

    }
    Users(String email,String password,String gender,String name)
    {
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.username=name;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setId(String id){this.id=id;}
    public void setPassword(String password)
    {
        this.password=password;
    }
    public void setGender(String gender)
    {
        this.gender=gender;
    }
    public void setName(String name)
    {
        this.username=name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String getGender()
    {
        return this.gender;
    }
    public String getName()
    {
        return this.username;
    }
    public String getId(){return this.id;}
}
