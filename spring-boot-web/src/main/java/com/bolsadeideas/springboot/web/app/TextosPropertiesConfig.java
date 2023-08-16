package com.bolsadeideas.springboot.web.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration //Clase de configuracion de Spring
@PropertySources({
        @PropertySource("classpath:textos.properties")
})
public class TextosPropertiesConfig {
}
