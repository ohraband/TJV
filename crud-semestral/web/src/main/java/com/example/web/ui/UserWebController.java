package com.example.web.ui;

import com.example.web.data.UserClient;
import com.example.web.model.UserDto;
import com.example.web.model.UserWebModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/users")
public class UserWebController
{
    private final UserClient userClient;

    public UserWebController(UserClient userClient)
    {
        this.userClient = userClient;
    }

    @GetMapping
    public String list (Model model)
    {
        model.addAttribute("userlist", userClient.readAll());
        return "users";
    }

    @GetMapping("/signup")
    public String registerForm (Model model)
    {
        model.addAttribute("userWebModel", new UserWebModel());
        return "userSignUp";
    }

    @PostMapping("/signup")
    public String registerCallback (@ModelAttribute UserWebModel userWebModel, Model model)
    {
        Mono<UserWebModel> userWebModelMono = userClient.create(userWebModel).onErrorResume(WebClientResponseException.Conflict.class, e -> Mono.just(new UserWebModel(true, userWebModel)));
        model.addAttribute("userWebModel", userWebModelMono);
        return "userSuccess";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model)
    {
        model.addAttribute("userToEdit", userClient.readById(id));
        return "userEdit";
    }


    @PostMapping("/edit")
    public String editCallback (@ModelAttribute UserDto userDto, Model model)
    {
        model.addAttribute("userToEdit", userClient.update(userDto));
        return "userSuccess";
    }

    @GetMapping("/delete")
    public String delete (@RequestParam Integer id, Model model)
    {
        model.addAttribute("userToDelete",userClient.delete(id));
        return "userDelete";
    }

}