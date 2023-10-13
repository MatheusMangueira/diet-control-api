package app.vercel.matheusmangueira.dietcontrol.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
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

import app.vercel.matheusmangueira.dietcontrol.dietList.model.DietListModel;
import app.vercel.matheusmangueira.dietcontrol.repository.IDietListRepository;
import app.vercel.matheusmangueira.dietcontrol.repository.IUserRepository;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("diet")
public class DietListController {

   @Autowired
   private IDietListRepository dietListRepository;

   @Autowired
   private IUserRepository userRepository;

   @PostMapping("/create/{id}")
   public ResponseEntity create(@Valid @PathVariable UUID id, @RequestBody DietListModel dietListModel) {

      Optional<UserModel> userOptional = this.userRepository.findById(id);

      if (userOptional.isPresent()) {
         UserModel user = userOptional.get();
         dietListModel.setUser(user);
         this.dietListRepository.save(dietListModel);
         return ResponseEntity.status(HttpStatus.CREATED).body(dietListModel);
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
      }

   }

   @GetMapping("/list/{id}")
   public ResponseEntity findAll(@Valid @PathVariable UUID id) {
      Optional<UserModel> userOptional = this.userRepository.findById(id);

      if (userOptional.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado");
      }

      UserModel user = userOptional.get();
      List<DietListModel> dietListModels = this.dietListRepository.findByUser(user);

      return ResponseEntity.ok(dietListModels);
   }

}
