package cz.cvut.fit.tjv.ohraband.farming_monitor.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "farmingArea")
public class FarmingArea {

    @Id
    @GeneratedValue(generator = "farming_id")
    private Integer idArea = 0;

    @Column(nullable = true)
    private String nameArea;

    @ManyToMany(mappedBy = "farmingAreas")
    private Set<Fieldmonitor> fieldmonitors;


    public FarmingArea( String nameArea) {
        this.nameArea = nameArea;
    }

    public FarmingArea( String nameArea, Set<Fieldmonitor> fieldmonitors) {
        this.nameArea = nameArea;
        this.fieldmonitors = fieldmonitors;
    }

    public FarmingArea() {
    }


    public int getId() {
        return idArea;
    }

    public String getName() {
        return nameArea;
    }

    public Set<Fieldmonitor> getFieldmonitors() {
        return fieldmonitors;
    }

    public void setId(int idArea) {
        this.idArea = idArea;
    }

    public void setName(String nameArea) {
        this.nameArea = nameArea;
    }

    public void setFieldmonitors(Set<Fieldmonitor> fieldmonitors) {
        this.fieldmonitors = fieldmonitors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FarmingArea that = (FarmingArea) o;
        return idArea == that.idArea;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArea);
    }
}
