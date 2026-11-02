package com.springboot.friendfinder.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.stereotype.Component;

@Component
@OpenAPIDefinition(
        info = @Info(
                title = "Friend Finder",
                description = "It like As Facebook",
                contact = @Contact(
                        name = "Eng: Ahmed Mohamed Habiby Emam",
                        email = "ahmednsra329@gmail.com",
                        url = "http://localhost:4200/"
                ),
                license = @License(
                        name = "All copyright is berserved",
                        url = "http://localhost:4200/"
                ),
                version = "V1.0"
        )
)
public class SwaggerControl {
}
