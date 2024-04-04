package cz.cvut.fit.tjv.ohraband.farming_monitor.business;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cz.cvut.fit.tjv.ohraband.farming_monitor.business.UserService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.dao.UserJpaRepository;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTests
{
    @Autowired
    UserService service;

    @MockBean
    UserJpaRepository userRepository;

    UserServiceTests() {
        service = new UserService(userRepository);
    }

    @Test
    public void testFindAllUsers()
    {
        List<User> list = new ArrayList<>();
        User user1 = new User("def", "abc");
        User user2 = new User("fdsfsd", "abc");
        User user3 = new User("gfhtrhr", "abc");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(userRepository.findAll()).thenReturn(list);

        //Real test
        Collection<User> userList = service.readAll();
        assertEquals(3, userList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testCreateUser()
    {
        User user = new User("def", "abc");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        when(userRepository.existsById(1)).thenReturn(true);
        service.create(user);
        verify(userRepository, times(1)).save(user);
    }
}
