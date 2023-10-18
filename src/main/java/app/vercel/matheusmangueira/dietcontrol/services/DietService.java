package app.vercel.matheusmangueira.dietcontrol.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.vercel.matheusmangueira.dietcontrol.dietList.model.DietListModel;
import app.vercel.matheusmangueira.dietcontrol.repository.IDietListRepository;
import app.vercel.matheusmangueira.dietcontrol.repository.IUserRepository;
import jakarta.validation.Valid;

@Service
public class DietService {

   @Autowired
   private IDietListRepository dietRepository;

   public DietListModel updateDietService(@Valid DietListModel data, UUID id, UUID dietId) {

      Optional<DietListModel> dietOptional = this.dietRepository.findById(dietId);

      if (dietOptional.isPresent()) {
         System.out.println("SEGUNDO DATA ");

         DietListModel existingDiet = dietOptional.get();

         if (data.getFoodName() != null) {
            existingDiet.setFoodName(data.getFoodName());
         }
         if (data.getCarb() != null) {
            existingDiet.setCarb(data.getCarb());
         }
         if (data.getProtein() != null) {
            existingDiet.setProtein(data.getProtein());
         }
         if (data.getFat() != null) {
            existingDiet.setFat(data.getFat());
         }
         if (data.getQuantity() != null) {
            existingDiet.setQuantity(data.getQuantity());
         }
         this.dietRepository.save(existingDiet);

         System.out.println("TERCEIRO DATA " + data);
         return data;

      }

      System.out.println("QUARTO DATA " + dietOptional.isPresent());
      return null;

   }
   // UserModel user = userOptional.get();

   // System.out.println("USER " + user);

   // List<DietListModel> dietListModels = this.dietRepository.findByUser(user);

   // System.out.println("DIET " + dietListModels);

   // Optional<DietListModel> dietOptional = dietListModels.stream().filter(diet ->
   // diet.getId().equals(userId))
   // .findFirst(); // Corrigido para usar id

   // System.out.println("dietOptional " + dietOptional);

   // if (dietOptional.isPresent()) {
   // DietListModel obj = dietOptional.get();

   // System.out.println("SEGUNDO DATA " + obj);

   // if (data.getFoodName() != null) {
   // obj.setFoodName(data.getFoodName());
   // }
   // if (data.getCarb() != null) {
   // obj.setCarb(data.getCarb());
   // }
   // if (data.getProtein() != null) {
   // obj.setProtein(data.getProtein());
   // }
   // if (data.getFat() != null) {
   // obj.setFat(data.getFat());
   // }
   // if (data.getQuantity() != null) {
   // obj.setQuantity(data.getQuantity());
   // }
   // this.dietRepository.save(obj);

   // System.out.println("TERCEIRO DATA " + obj);
   // return obj;

   // }

   // System.out.println("QUARTO DATA " + data);
   // return null;

}
