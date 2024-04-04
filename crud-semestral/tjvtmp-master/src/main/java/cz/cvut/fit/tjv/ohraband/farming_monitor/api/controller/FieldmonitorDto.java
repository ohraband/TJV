package cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller;


import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.FarmingAreaConverter;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FarmingAreaService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.FarmingArea;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FieldmonitorDto{


    private Integer idFieldmonitor;

    private String username;

    private LocalDate dateOfCreation;

    private LocalDate dateOfTermination;

    private User user = null;

    private Collection<FarmingAreaDto> farmingAreas = new HashSet<>();

    public FieldmonitorDto() {
    }

    public FieldmonitorDto(Integer idFieldmonitor, String username, LocalDate dateOfCreation, LocalDate dateOfTermination, User user, Collection<FarmingArea> farmingAreas) {
        this.idFieldmonitor = idFieldmonitor;
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
        this.user = user;
        Collection<FarmingAreaDto> farmingAreasDto = new ArrayList<>();
        farmingAreas.forEach((u) -> farmingAreasDto.add(FarmingAreaConverter.fromModel(u)));
        this.farmingAreas = farmingAreasDto;
    }


    public FieldmonitorDto(String username, LocalDate dateOfCreation, LocalDate dateOfTermination, User user, Collection<FarmingArea> farmingAreas) {
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
        this.user = user;
        Collection<FarmingAreaDto> farmingAreasDto = new ArrayList<>();
        farmingAreas.forEach((u) -> farmingAreasDto.add(FarmingAreaConverter.fromModel(u)));
        this.farmingAreas = farmingAreasDto;
    }

    public FieldmonitorDto(Integer idFieldmonitor, String username, LocalDate dateOfCreation, LocalDate dateOfTermination, User user) {
        this.idFieldmonitor = idFieldmonitor;
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
        this.user = user;
    }

    public FieldmonitorDto(String username, LocalDate dateOfCreation, LocalDate dateOfTermination, User user) {
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
        this.user = user;
    }


    public FieldmonitorDto(String username, LocalDate dateOfCreation, LocalDate dateOfTermination) {
        this.username = username;
        this.dateOfCreation = dateOfCreation;
        this.dateOfTermination = dateOfTermination;
    }




    public Integer getIdFieldmonitor() {
        return idFieldmonitor;
    }

    public void setIdFieldmonitor(Integer idFieldmonitor) {
        this.idFieldmonitor = idFieldmonitor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(LocalDate dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<FarmingAreaDto> getFarmingAreas() {
        return farmingAreas;
    }

    public void setFarmingAreas(Collection<FarmingAreaDto> farmingAreas) {
        this.farmingAreas = farmingAreas;
    }
}

