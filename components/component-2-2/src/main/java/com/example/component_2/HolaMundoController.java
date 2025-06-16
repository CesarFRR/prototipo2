package com.example.component_2;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HolaMundoController {

    @QueryMapping
    public String holaMundo() {
        return "¡Hola Mundo desde GraphQL y Spring Boot!";
    }
}