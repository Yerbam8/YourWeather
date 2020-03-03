package pl.sda.pk.YourWeather.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = LocationAlreadyExistException.class)
    public ResponseEntity<Object> exceptionNoUniqueItem(
            LocationAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
