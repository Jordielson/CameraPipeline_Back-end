package com.camerapipeline.camera_pipeline.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        description = "JWT Token Authentication",
        scheme = "bearer"
)
@OpenAPIDefinition(
    info = @Info(
        title = "CameraPipeline application programming interface (API)",
        version = "v1.0",
        description = "Restful API to manage the CameraPipeline Application",
        license = @License(name = "Apache License Version 2.0", 
        url = "https://www.apache.org/licenses/LICENSE-2.0"),
        contact = @Contact(
            url = "https://camera-pipeline.netlify.app/", 
            name = "Camera Pipeline", 
            email = "camerapipeline@gmail.com"
        )
    )    
)
public class SwaggerConfig {
}
