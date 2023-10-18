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

         return data;

      }

      return null;

   }

}
