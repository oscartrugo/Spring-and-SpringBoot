package com.example.springboot.error.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice //Maneja errores, las excepciones, captura los lanzamientos de excepcion
public class ErrorHandlerController {
    @ExceptionHandler(ArithmeticException.class)
    public String aritmeticaError(ArithmeticException ex, Model model){
        model.addAttribute("error", "Error de aritmética");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/aritmetica";
    }
}
