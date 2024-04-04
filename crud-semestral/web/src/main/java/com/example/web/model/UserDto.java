package com.example.web.model;


public class UserDto
{
    public Integer idUser;
    public String firstName;
    public String lastName;

    public UserDto()
    {
    }

    public UserDto(Integer idUser, String username, String password)
    {
        this.idUser = idUser;
        this.firstName = username;
        this.lastName = password;
    }

    public UserDto(String username, String password)
    {
        this.firstName = username;
        this.lastName = password;
    }

    public Integer getidUser()
    {
        return idUser;
    }

    public void setidUser(Integer idUser)
    {
        this.idUser = idUser;
    }

    public String getfirstName()
    {
        return firstName;
    }

    public void setfirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getlastName()
    {
        return lastName;
    }

    public void setlastName(String lastName)
    {
        this.lastName = lastName;
    }
}
