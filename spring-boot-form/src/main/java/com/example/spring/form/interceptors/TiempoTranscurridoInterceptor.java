package com.example.spring.form.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equalsIgnoreCase("post")){
            return true; //Se omite y no se aplica el código
        }

        if(handler instanceof HandlerMethod){
            HandlerMethod metodo = (HandlerMethod) handler;
            logger.info("Es un método del controlador: " + metodo.getMethod().getName());
        }

        logger.info("TiempoTranscurridoInterceptor: preHandle() entrando ...");
        logger.info("Interceptando ..." +  handler);
        long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);
        Random random = new Random();
        Integer demora = random.nextInt(500); //Entre 0 y 500 (el valor introducido)
        Thread.sleep(demora);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long tiempoInicio = (long) request.getAttribute("tiempoInicio");
        long tiempoFin = System.currentTimeMillis();
        long tiempoTranscurrido = tiempoFin - tiempoInicio;

        if(handler instanceof HandlerMethod && modelAndView != null){
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }

        logger.info("Tiempo transcurrido: " + tiempoTranscurrido + " milisegundos.");
        logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo ...");
    }
}
