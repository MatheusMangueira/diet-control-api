package app.vercel.matheusmangueira.dietcontrol.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HendlerError {

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<?> handle404() {
      return ResponseEntity.notFound().build();
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<?> handle400(MethodArgumentNotValidException ex) {

      var erros = ex.getFieldErrors();

      return ResponseEntity.badRequest().body(erros.stream().map(DataError::new).toList());

   }

   public record DataError(String field, String message) {

      public DataError(FieldError error) {
         this(error.getField(), error.getDefaultMessage());
      }

   }

}
