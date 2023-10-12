package app.vercel.matheusmangueira.dietcontrol.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.vercel.matheusmangueira.dietcontrol.user.dto.UserDto;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;
import app.vercel.matheusmangueira.dietcontrol.user.repository.IUserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private IUserRepository userRepository;

   @PostMapping("/calculate-diet")
   public ResponseEntity create(@Valid @RequestBody UserModel userModel, BindingResult result) {

      if (result.hasErrors()) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro. Campo obrigatorio: " + result.getAllErrors());
      }

      var userCreated = this.userRepository.save(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
   }

   @GetMapping("/all")
   public ResponseEntity findAll() {
      return ResponseEntity.ok(this.userRepository.findAll());
   }
}
