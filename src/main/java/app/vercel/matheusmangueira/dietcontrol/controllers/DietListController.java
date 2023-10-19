package app.vercel.matheusmangueira.dietcontrol.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.vercel.matheusmangueira.dietcontrol.dietList.model.DietListModel;
import app.vercel.matheusmangueira.dietcontrol.repositories.IDietListRepository;
import app.vercel.matheusmangueira.dietcontrol.repositories.IUserRepository;
import app.vercel.matheusmangueira.dietcontrol.services.DietService;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("diet")
public class DietListController {

   @Autowired
   private IDietListRepository dietListRepository;

   @Autowired
   private IUserRepository userRepository;

   @Autowired
   private DietService dietService;

   @PostMapping("/{id}")
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

   @GetMapping("/{id}")
   public ResponseEntity findById(@Valid @PathVariable UUID id) {
      Optional<UserModel> userOptional = this.userRepository.findById(id);

      if (userOptional.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado");
      }

      UserModel user = userOptional.get();
      List<DietListModel> dietListModels = this.dietListRepository.findByUser(user);

      return ResponseEntity.ok(dietListModels);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity delete(@Valid @PathVariable UUID id) {
      Optional<DietListModel> dietListModelOptional = this.dietListRepository.findById(id);

      if (dietListModelOptional.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum item encontrado");
      }

      this.dietListRepository.deleteById(id);
      return ResponseEntity.ok("Item deletado com sucesso");
   }

   @PutMapping("/{id}/{idDiet}")
   @Transactional
   public ResponseEntity updateDiet(@Valid @PathVariable UUID id,
         @PathVariable UUID idDiet, @RequestBody DietListModel data) {

      if (id == null || idDiet == null || data == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
      }

      var user = this.userRepository.findById(id);
      var diet = this.dietListRepository.findById(idDiet);

      if (user.isEmpty() && diet.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dieta não encontrado");
      }

      UserModel userFound = user.get();
      DietListModel dietFound = diet.get();

      // Verificar se o ID do usuário corresponde ao ID da dieta
      if (!userFound.getId().equals(id) && !dietFound.getId().equals(data.getId())) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acesso não autorizado para atualizar esta dieta");
      }

      return ResponseEntity.ok(dietService.updateDietService(data, id, idDiet));

   }
}
