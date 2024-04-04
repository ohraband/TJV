package cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller;

import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;

import java.util.Set;

public class UserDto {


    public Integer idUser;

    public String firstName;

    public String lastName;

    public Set<Fieldmonitor> fieldmonitors;

    public UserDto() {
    }


    public UserDto(Integer idUser, String firstName, String lastName, Set<Fieldmonitor> fieldomonitors) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fieldmonitors = fieldomonitors;
    }


    public UserDto(Integer idUser, String firstName, String lastName) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public UserDto( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setUsername(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Fieldmonitor> getFieldmonitors() {
        return fieldmonitors;
    }

    public void setFieldmonitors(Set<Fieldmonitor> fieldmonitors) {
        this.fieldmonitors = fieldmonitors;
    }
}

