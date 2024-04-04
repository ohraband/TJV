package cz.cvut.fit.tjv.ohraband.farming_monitor.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "gfuUserAccount")
public class User {

    @Id
    @GeneratedValue(generator = "user_id")
    private Integer idUser = 0;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany
    @JoinColumn(name = "fieldmonitorId")
    private Set<Fieldmonitor> fieldmonitors;


    public User( String firstName, String lastName,Set<Fieldmonitor> fieldmonitors ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fieldmonitors = fieldmonitors;
    }


    public User( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public User() {
    }


    public Integer getId() {
        return idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Fieldmonitor> getFieldmonitors() {
        return fieldmonitors;
    }

    public void setId(Integer idUser) {
        this.idUser = idUser;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String lastName) {
        this.lastName = lastName;
    }

    public void setFieldmonitors(Set<Fieldmonitor> fieldmonitors) {
        this.fieldmonitors = fieldmonitors;
    }

}
