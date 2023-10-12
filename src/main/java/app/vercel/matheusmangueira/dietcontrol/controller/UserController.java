package app.vercel.matheusmangueira.dietcontrol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.vercel.matheusmangueira.dietcontrol.dtos.UserDto;
import app.vercel.matheusmangueira.dietcontrol.repository.IUserRepository;
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
   public ResponseEntity<UserDto> findAll(@Valid @PathVariable UUID id) {
     
      return ResponseEntity.ok(this.userService.findUserById(id));
   }
}
