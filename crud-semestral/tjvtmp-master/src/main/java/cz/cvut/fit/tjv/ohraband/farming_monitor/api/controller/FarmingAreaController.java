package cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller;

import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.FarmingAreaConverter;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.EntityStateException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FarmingAreaService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FarmingAreaService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.FarmingArea;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class FarmingAreaController {

    private final FarmingAreaService farmingAreaService;

    FarmingAreaController(FarmingAreaService farmingAreaService) {
        this.farmingAreaService = farmingAreaService;
    }



    @GetMapping("/farmingarea")
    Collection<FarmingAreaDto> GetFarmingAreas() {
        return FarmingAreaConverter.fromModelMany(farmingAreaService.readAll());
    }

    @PostMapping("/farmingarea")
    FarmingAreaDto CreateFarmingArea(@RequestBody FarmingAreaDto newFarmingArea) throws EntityStateException {
        FarmingArea farmingAreaModel = FarmingAreaConverter.toModel(newFarmingArea);
        this.farmingAreaService.create(farmingAreaModel);
//        userModel = this.userService.readById(userModel.getId()).orElseThrow(
//                () -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "User Not Found")
//        );
        return FarmingAreaConverter.fromModel(farmingAreaModel);
    }





    @GetMapping("/farmingarea/{id}")
    FarmingAreaDto GetfarmingAreaById (@PathVariable Integer id){
        return FarmingAreaConverter.fromModel(
                farmingAreaService.readById(id)
                        .orElseThrow(NoEntityFoundException::new)
        );
    }


    @PutMapping("/farmingarea/{id}")
    FarmingAreaDto UpdateFarmingAreaById(@RequestBody FarmingAreaDto farmingAreaDto, @PathVariable Integer id) {
        farmingAreaService.readById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "FarmingArea Not Found")
                );
        FarmingArea farmingArea = FarmingAreaConverter.toModel(farmingAreaDto);
        try {
            this.farmingAreaService.update(farmingArea);
        } catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "FarmingArea ID is not unique", exception);
        }
        return farmingAreaDto;
    }


    @DeleteMapping("/farmingarea/{id}")
    void DeleteFarmingAreaById (@PathVariable Integer id){
        farmingAreaService.deleteById(id);
    }

}
