package cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller;


import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.FieldmonitorConverter;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.EntityStateException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FieldmonitorService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.UserService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter.UserConverter;
import org.springframework.web.server.ResponseStatusException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.exception.NoEntityFoundException;

import java.util.Collection;
import java.util.Objects;


@RestController
public class UserController {


    private final UserService userService;
    private final FieldmonitorService fieldmonitorService;

    UserController(UserService userService, FieldmonitorService fieldmonitorService) {
        this.userService = userService;
        this.fieldmonitorService = fieldmonitorService;
    }




    @GetMapping("/user")
    public Collection<UserDto> getUsers ()
    {
        return UserConverter.fromModelMany(userService.readAll());
    }

    @PostMapping("/user")
    public UserDto CreateUser(@RequestBody UserDto newUser) throws EntityStateException {
        User userModel = UserConverter.toModel(newUser);
        this.userService.create(userModel);
//        userModel = this.userService.readById(userModel.getId()).orElseThrow(
//                () -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "User Not Found")
//        );
        return UserConverter.fromModel(userModel);
    }


    @GetMapping("/user/{id}")
    public UserDto GetUserById(@PathVariable Integer id){
        return UserConverter.fromModel(
                userService.readById(id)
                        .orElseThrow(NoEntityFoundException::new)
        );
    }


    @PutMapping("/user/{id}")
    UserDto UpdateUserById(@RequestBody UserDto userDto, @PathVariable Integer id)
    {
        User olduser = userService.readById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        User newuser = UserConverter.toModel(userDto);
        if (newuser.getFirstName() == null){
            newuser.setName(olduser.getFirstName());
        }
        if (newuser.getLastName() == null){
            newuser.setSurname(olduser.getLastName());
        }
        newuser.setId(id);
        try {
            this.userService.update(newuser);
        }
        catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong.");
        }
        return UserConverter.fromModel(newuser);
    }

    @DeleteMapping("/user/{id}")
    public void DeleteUserById(@PathVariable Integer id){
        User oldUserModel = userService.readById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        var fieldmonitors = fieldmonitorService.readAll();
        for (Fieldmonitor monitor : fieldmonitors){
            if(Objects.equals(monitor.getUser().getId(), id)){
                fieldmonitorService.deleteById(monitor.getIdFieldmonitor());
            }

        }
        userService.deleteById(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "");
    }

//    @DeleteMapping("/user/{id}")
//    void deleteUserId (@PathVariable Integer id)
//    {
//        userService.deleteById(id);
//        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "");
//    }

    @GetMapping("/user/{idUser}/fieldmonitor/{idFieldmonitor}")
    Fieldmonitor GetUserFieldmonitorById(@PathVariable Integer idUser, @PathVariable Integer idFieldmonitor){
        User user = userService.readById(idUser).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found")
        );

        Fieldmonitor fieldmonitor = fieldmonitorService.readById(idFieldmonitor).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );

        if(!fieldmonitor.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fieldmonitor of User Not Found");
        }
        return  fieldmonitor;
    }



    @PutMapping("/user/{idUser}/fieldmonitor")
    Fieldmonitor CreateUserFieldmonitor(@PathVariable Integer idUser, @RequestBody FieldmonitorDto fieldmonitorDto){
        User user = userService.readById(idUser).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found")
        );

        fieldmonitorDto.setUser(user);
        Fieldmonitor fieldmonitor = FieldmonitorConverter.toModel(fieldmonitorDto);
        this.fieldmonitorService.create(FieldmonitorConverter.toModel(fieldmonitorDto));

        if(!fieldmonitor.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Don't include user to auto assign.");
        }
        userService.update(user);
        return  fieldmonitor;
    }



    @DeleteMapping("/user/{idUser}/fieldmonitor/{idFieldmonitor}")
    void DeleteUserFieldmonitorById(@PathVariable Integer idUser, @PathVariable Integer idFieldmonitor){
        User user = userService.readById(idUser).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found")
        );

        Fieldmonitor fieldmonitor = fieldmonitorService.readById(idFieldmonitor).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Fieldmonitor Not Found")
        );

        if(!fieldmonitor.getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fieldmonitor of User Not Found");
        }


        fieldmonitor.setUser(null);
        fieldmonitorService.deleteById(fieldmonitor.getIdFieldmonitor());
        userService.update(user);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deleted");
    }
}
