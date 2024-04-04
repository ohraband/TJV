package cz.cvut.fit.tjv.ohraband.farming_monitor.api.converter;

import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.UserDto;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserConverter {

    public static User toModel(UserDto userDto) {
        return new User( userDto.getFirstName(), userDto.getLastName(),userDto.getFieldmonitors());
    }

    public static UserDto fromModel(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(),user.getFieldmonitors());
    }
    public static Collection<User> toModelMany(Collection<UserDto> userDtos) {
        Collection<User> users = new ArrayList<>();
        userDtos.forEach((u) -> users.add(toModel(u)));
        return users;
    }

    public static Collection<UserDto> fromModelMany(Collection<User> users) {
        Collection<UserDto> userDtos = new ArrayList<>();
        users.forEach((u) -> userDtos.add(fromModel(u)));
        return userDtos;
    }
}
