package cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter;

import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.FarmingAreaDto;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.FarmingArea;

import java.util.ArrayList;
import java.util.Collection;

public class FarmingAreaConverter{

    public static FarmingArea toModel(FarmingAreaDto FarmingAreaDto) {
        return new FarmingArea( FarmingAreaDto.getNameArea());
    }

    public static FarmingAreaDto fromModel(FarmingArea FarmingArea) {
        return new FarmingAreaDto(FarmingArea.getId(), FarmingArea.getName()); //,FarmingArea.getFieldmonitors()
    }
    public static Collection<FarmingArea> toModelMany(Collection<FarmingAreaDto> FarmingAreaDtos) {
        Collection<FarmingArea> farmingAreas = new ArrayList<>();
        FarmingAreaDtos.forEach((u) -> farmingAreas.add(toModel(u)));
        return farmingAreas;
    }

    public static Collection<FarmingAreaDto> fromModelMany(Collection<FarmingArea> FarmingArea) {
        Collection<FarmingAreaDto> farmingAreaDtos = new ArrayList<>();
        FarmingArea.forEach((u) -> farmingAreaDtos.add(fromModel(u)));
        return farmingAreaDtos;
    }
}
