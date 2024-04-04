package com.example.web.data;

import com.example.web.model.UserDto;
import com.example.web.model.UserWebModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Component
public class UserClient
{
    private static final String ONE_URI = "/{id}";
    private final WebClient userWebClient;

    public UserClient(@Value("${farming_monitor_backend_url}") String backendUrl)
    {
        this.userWebClient = WebClient.create(backendUrl + "/user");
    }

    public Mono<UserWebModel> create (UserDto user)
    {
        return userWebClient.post().contentType(MediaType.APPLICATION_JSON).bodyValue(user)
                .retrieve().bodyToMono(UserWebModel.class);
    }

    public Flux<UserWebModel> readAll()
    {
        return userWebClient.get().retrieve().bodyToFlux(UserWebModel.class);
    }

    public Mono<UserWebModel> readById (Integer id)
    {
        return userWebClient.get().uri(ONE_URI, id).retrieve().bodyToMono(UserWebModel.class);
    }

    public Mono<UserWebModel> update (UserDto user)
    {
        return userWebClient.put().uri(ONE_URI, user.idUser).contentType(MediaType.APPLICATION_JSON).
                bodyValue(user).retrieve().bodyToMono(UserWebModel.class);
    }

    public Mono<Void> delete (Integer id)
    {
        return userWebClient.delete().uri(ONE_URI, id).retrieve().bodyToMono(Void.TYPE);
    }

}