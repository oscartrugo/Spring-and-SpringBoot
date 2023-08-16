package com.oscartrugo.springboot.di.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("miServicioComplejo")
public class MiServicioComplejo implements IServicio {

    @Override //Sobreescribiendo el método operacion
    public String operacion() {
        return "Ejecutando algún proceso importante complejo...";
    }
}
