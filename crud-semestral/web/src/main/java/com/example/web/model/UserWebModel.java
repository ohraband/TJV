package com.example.web.model;

public class UserWebModel extends UserDto
{
    private boolean unspecifiedError;

    public UserWebModel(){}

    public UserWebModel(boolean someKindOfError, UserDto user)
    {
        super(user.idUser, user.firstName, user.lastName);
        this.unspecifiedError = someKindOfError;
    }

    public boolean isUnspecifiedError()
    {
        return unspecifiedError;
    }

    public void setUnspecifiedError(boolean unspecifiedError)
    {
        this.unspecifiedError = unspecifiedError;
    }

    @Override
    public String toString() {
        return "UserWebModel{" +
                "id=" + idUser +
                ", username='" + firstName + '\'' +
                ", password='" + lastName + '\'' +
                ", unspecifiedError=" + unspecifiedError +
                '}';
    }
}
