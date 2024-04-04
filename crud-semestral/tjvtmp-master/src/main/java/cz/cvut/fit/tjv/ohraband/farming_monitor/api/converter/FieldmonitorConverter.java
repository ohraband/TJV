package cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter;

import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.FarmingAreaDto;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.FieldmonitorDto;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.FarmingArea;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


public class FieldmonitorConverter{

    public static Fieldmonitor toModel(FieldmonitorDto fieldmonitorDto) {
        return new Fieldmonitor(fieldmonitorDto.getUsername(), fieldmonitorDto.getDateOfCreation(), fieldmonitorDto.getDateOfTermination(), fieldmonitorDto.getUser());
    }

    public static FieldmonitorDto fromModel(Fieldmonitor fieldmonitor) {
        return new FieldmonitorDto(fieldmonitor.getIdFieldmonitor(), fieldmonitor.getUsername(), fieldmonitor.getDateOfCreation(), fieldmonitor.getDateOfTermination(),fieldmonitor.getUser()
                    ,fieldmonitor.getFarmingAreas());
    }

    public static Collection<Fieldmonitor> toModelMany(Collection<FieldmonitorDto> fieldmonitorDtos) {
        Collection<Fieldmonitor> fieldmonitors = new ArrayList<>();
        fieldmonitorDtos.forEach((u) -> fieldmonitors.add(toModel(u)));
        return fieldmonitors;
    }

    public static Collection<FieldmonitorDto> fromModelMany(Collection<Fieldmonitor> Fieldmonitor) {
        Collection<FieldmonitorDto> fieldmonitorDtos = new ArrayList<>();
        Fieldmonitor.forEach((u) -> fieldmonitorDtos.add(fromModel(u)));
        return fieldmonitorDtos;
    }
}
