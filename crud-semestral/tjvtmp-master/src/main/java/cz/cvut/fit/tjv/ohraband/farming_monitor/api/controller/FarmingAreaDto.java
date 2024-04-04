package cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller;

import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FarmingAreaDto {


    private Integer idArea;

    private String nameArea;

    private Collection<Fieldmonitor> fieldmonitors;

    public FarmingAreaDto() {
    }


    public FarmingAreaDto( String nameArea) {
        this.nameArea = nameArea;
    }



//    public FarmingAreaDto(Integer idArea, String nameArea, Collection<Fieldmonitor> fieldmonitors) {
//        this.idArea= idArea;
//        this.nameArea = nameArea;
//        this.fieldmonitors= fieldmonitors;
//    }

    public FarmingAreaDto( Integer idArea, String nameArea) {
        this.idArea= idArea;
        this.nameArea = nameArea;
    }


    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public Collection<Fieldmonitor> getFieldmonitors() {
        return fieldmonitors;
    }

    public void setFieldmonitors(Collection<Fieldmonitor> fieldmonitors) {
        this.fieldmonitors = fieldmonitors;
    }
}

