package cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller;

import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.FarmingAreaConverter;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.FieldmonitorConverter;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.UserConverter;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.EntityStateException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FarmingAreaService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FieldmonitorService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.UserService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.FarmingArea;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class FieldmonitorController {

    @Autowired
    private FarmingAreaService farmingAreaService;
    @Autowired
    private FieldmonitorService fieldmonitorService;

//    FieldmonitorController(FieldmonitorService fieldmonitorService, FarmingAreaService farmingAreaService) {
//        this.fieldmonitorService = fieldmonitorService;
//        this.farmingAreaService = farmingAreaService;
//    }


    @GetMapping("/fieldmonitor")
    Collection<FieldmonitorDto> GetFieldmonitors() {
        return FieldmonitorConverter.fromModelMany(fieldmonitorService.readAll());
    }


//    @PostMapping("/fieldmonitor")
//    FieldmonitorDto CreateMonitor(@RequestBody FieldmonitorDto newFieldmonitor) throws EntityStateException {
//        Fieldmonitor fieldmonitorModel = FieldmonitorConverter.toModel(newFieldmonitor);
//        this.fieldmonitorService.create(fieldmonitorModel);
////        userModel = this.userService.readById(userModel.getId()).orElseThrow(
////                () -> new ResponseStatusException(
////                        HttpStatus.NOT_FOUND, "User Not Found")
////        );
//        return FieldmonitorConverter.fromModel(fieldmonitorModel);
//    }



    @GetMapping("/fieldmonitor/{id}")
    FieldmonitorDto GetFieldmonitorById (@PathVariable Integer id){
        return FieldmonitorConverter.fromModel(
                fieldmonitorService.readById(id)
                        .orElseThrow(NoEntityFoundException::new)
        );
    }




    @PutMapping("/fieldmonitor/{id}")
    FieldmonitorDto UpdateFieldmonitorById(@RequestBody FieldmonitorDto fieldmonitorDto, @PathVariable Integer id) {
        fieldmonitorService.readById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );
        Fieldmonitor fieldmonitor = FieldmonitorConverter.toModel(fieldmonitorDto);
        try {
            this.fieldmonitorService.update(fieldmonitor);
        } catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fieldmonitor ID is not unique", exception);
        }
        return fieldmonitorDto;
    }


    @DeleteMapping("/fieldmonitor/{id}")
    void DeleteFieldmonitorById (@PathVariable Integer id){

        Fieldmonitor fieldmonitor = fieldmonitorService.readById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );
        fieldmonitor.setUser(null);
        fieldmonitorService.deleteById(id);
    }


    @GetMapping("/fieldmonitor/{idFieldmonitor}/farmingarea/{idArea}")
    FarmingArea GetFarmingAreaFromFieldmonitorById(@PathVariable Integer idArea, @PathVariable Integer idFieldmonitor){
        FarmingArea farmingArea = farmingAreaService.readById(idArea).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "FarmingArea Not Found")
        );

        Fieldmonitor fieldmonitor = fieldmonitorService.readById(idFieldmonitor).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );

        if(!fieldmonitor.getFarmingAreas().contains(farmingArea)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farming Area isn't here.");
        }

        return farmingArea;
    }


    @PutMapping("/fieldmonitor/{idFieldmonitor}/farmingarea")
    Fieldmonitor AddFarmingAreaToFieldmonitorById(@RequestBody Integer idArea, @PathVariable Integer idFieldmonitor){
        Fieldmonitor fieldmonitor = fieldmonitorService.readById(idFieldmonitor).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );

        FarmingArea farmingArea = farmingAreaService.readById(idArea).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "FarmingArea Not Found")
        );

        fieldmonitor.getFarmingAreas().add(farmingArea);
        fieldmonitorService.update(fieldmonitor);
        farmingArea.getFieldmonitors().add(fieldmonitor);
        farmingAreaService.update(farmingArea);

        if(!fieldmonitor.getFarmingAreas().contains(farmingArea)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farming Area isn't here.");
        }

        return fieldmonitor;
    }


    @DeleteMapping("/fieldmonitor/{idFieldmonitor}/farmingarea/{idArea}")
    void DeleteFarmingAreaFromFieldmonitorById(@PathVariable Integer idArea, @PathVariable Integer idFieldmonitor){
        FarmingArea farmingArea = farmingAreaService.readById(idArea).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "FarmingArea Not Found")
        );

        Fieldmonitor fieldmonitor = fieldmonitorService.readById(idFieldmonitor).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );

        if(!fieldmonitor.getFarmingAreas().contains(farmingArea)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farming Area isn't here.");
        }

        //farmingArea.getFieldmonitors().remove(fieldmonitor);
        //farmingAreaService.update(farmingArea);
        fieldmonitor.getFarmingAreas().remove(farmingArea);
        fieldmonitorService.update(fieldmonitor);

    }

}
