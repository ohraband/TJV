package cz.cvut.fit.tjv.ohraband.farming_monitor.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "fieldmonitor")
public class Fieldmonitor {

    @Id
    @GeneratedValue(generator = "fieldmonitorGenerator_id")
    private Integer idFieldmonitor = 0;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDate dateOfCreation;

    @Column(nullable = true)
    private LocalDate dateOfTermination;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "farmingAreaFieldmonitor" ,
                joinColumns = @JoinColumn(name = "fieldmonitor" ),
                inverseJoinColumns = @JoinColumn(name = "farmingArea" ))
    private Set<FarmingArea> farmingAreas = new HashSet<>();




    public Fieldmonitor() {
    }

    public Fieldmonitor(String username, LocalDate dateOfCreation, LocalDate dateOfTermination, User user) {
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
        this.user = user;
    }

    public Fieldmonitor(String username, LocalDate dateOfCreation, LocalDate dateOfTermination) {
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
    }


//    public Fieldmonitor(Integer idFieldmonitor, String username, LocalDate dateOfCreation, LocalDate dateOfTermination, User user, Set<FarmingArea> farmingAreas) {
//        this.idFieldmonitor = idFieldmonitor;
//        this.username = username;
//        this.dateOfCreation = dateOfCreation;
//        this.dateOfTermination = dateOfTermination;
//        this.user = user;
//        this.farmingAreas = farmingAreas;
//    }




    public Integer getIdFieldmonitor() {
        return idFieldmonitor;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public LocalDate getDateOfTermination() {
        return dateOfTermination;
    }

    public User getUser() {
        return user;
    }

    public Set<FarmingArea> getFarmingAreas() {
        return farmingAreas;
    }

    public void setIdFieldmonitor(Integer idFieldmonitor) {
        this.idFieldmonitor = idFieldmonitor;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setDateOfTermination(LocalDate dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFarmingArea(Set<FarmingArea> farmingAreas) {
        this.farmingAreas = farmingAreas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fieldmonitor that = (Fieldmonitor) o;
        return idFieldmonitor.equals(that.idFieldmonitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFieldmonitor);
    }
}
