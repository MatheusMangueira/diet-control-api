package app.vercel.matheusmangueira.dietcontrol.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.vercel.matheusmangueira.dietcontrol.dtos.UserDto;
import app.vercel.matheusmangueira.dietcontrol.repositories.IUserRepository;
import app.vercel.matheusmangueira.dietcontrol.services.UserService;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private IUserRepository userRepository;

   @Autowired
   private UserService userService;

   @PostMapping("/calculate-diet")
   public ResponseEntity<UserModel> create(@Valid @RequestBody UserModel userModel, BindingResult result) {

      if (result.hasErrors()) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userModel);
      }

      var userCreated = this.userRepository.save(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
   }

   @GetMapping("/{id}")
   public ResponseEntity findById(@Valid @PathVariable UUID id) {
      var user = this.userRepository.findById(id);

      if (user.isPresent()) {
         UserDto userDto = this.userService.findUserById(id);
         return ResponseEntity.ok(userDto);
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity delete(@Valid @PathVariable UUID id) {
      var user = this.userRepository.findById(id);

      if (user.isPresent()) {
         this.userRepository.deleteById(id);
         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
      }

   }

}
