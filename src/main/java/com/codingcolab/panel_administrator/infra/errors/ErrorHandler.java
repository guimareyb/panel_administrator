package com.codingcolab.panel_administrator.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    /*
        Este manejador de excepciones se activa cuando se utiliza el método return[ENTIDAD] de los controladores.
        Devuelve un estado 404 (NOT FOUND) cuando el ID de la entidad a retornar no se encuentra registrado en la
        base de datos.
    */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity tryError404(){
        return ResponseEntity.notFound().build();
    }


    /*
        Este método maneja la excepción MethodArgumentNotValidException, que se produce cuando falla la validación
        de argumentos en un controlador.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity tryError400(MethodArgumentNotValidException e) {
        // Se obtienen los errores de validación de los campos que han fallado
        var errors = e.getFieldErrors()
                .stream()
                // Se mapean estos errores a un tipo de objeto ValidationErrorData
                // que se utiliza para estructurar los errores de validación
                .map(ValidationErrorData::new)
                .toList();

        // Se crea una respuesta ResponseEntity con un cuerpo que contiene la lista de errores de validación
        // ResponseEntity.badRequest() indica que la solicitud no pudo ser procesada debido a errores de validación
        return ResponseEntity.badRequest().body(errors);
    }


    //**DTO** Este registro se llama ValidationErrorData y encapsula información sobre errores de validación
    private record ValidationErrorData(String campo, String error) {

        // Constructor que recibe un objeto FieldError y extrae información para crear un ValidationErrorData
        public ValidationErrorData(FieldError error) {
            // Se inicializa el registro ValidationErrorData con el campo y el mensaje de error del FieldError recibido
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
