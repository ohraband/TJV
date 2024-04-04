package cz.cvut.fit.tjv.ohraband.farming_monitor.system;

import cz.cvut.fit.tjv.ohraband.farming_monitor.RestServer;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.UserController;
import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.UserDto;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.FieldmonitorService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.UserService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserSystemTests
{
    @MockBean
    private UserService userService;
    @MockBean
    private FieldmonitorService fieldmonitorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateReadDelete() throws Exception {
        User user1 = new User("F1", "L1");
        User user2 = new User("F2", "L2");

        List<User> users = List.of(user1, user2);
        Mockito.when(userService.readAll()).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

}
