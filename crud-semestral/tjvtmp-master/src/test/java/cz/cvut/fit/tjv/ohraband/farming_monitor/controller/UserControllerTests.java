package cz.cvut.fit.tjv.ohraband.farming_monitor.controller;


import cz.cvut.fit.tjv.ohraband.farming_monitor.business.EntityStateException;
import cz.cvut.fit.tjv.ohraband.farming_monitor.business.UserService;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserControllerTests {

    @MockBean
    UserService userService;

    MockMvc mockMvc;

    @Autowired
    public UserControllerTests(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAll() throws Exception {
        User user1 = new User("abc","abc");
        User user2 = new User("xyz","abc");
        List<User> users = List.of(user1, user2);

        Mockito.when(userService.readAll()).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("abc")))
                .andExpect(jsonPath("$[1].firstName", Matchers.is("xyz")));
    }

    @Test
    public void testGetOne() throws Exception {
        User user = new User("abc","abc");


        Mockito.when(userService.readById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.is("abc")));


        Mockito.when(userService.readById(not(eq(1)))).thenReturn(Optional.empty());

        mockMvc.perform(get("/user/65465"))
                .andExpect(status().isNotFound());
    }



    @Test
    public void testUpdateNotExisting() throws Exception {
        doThrow(new EntityStateException(null)).when(userService).update(any(User.class));

        mockMvc.perform(put("/user/987654987")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"abc\", \"lastName\":\"abc\"}"))
                .andExpect(status().isNotFound());
    }
}
