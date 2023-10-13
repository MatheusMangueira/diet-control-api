package app.vercel.matheusmangueira.dietcontrol.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HendlerError {

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<?> handle404(EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
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

   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handle500(Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
   }

}
