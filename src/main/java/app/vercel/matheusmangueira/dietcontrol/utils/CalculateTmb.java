package app.vercel.matheusmangueira.dietcontrol.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.vercel.matheusmangueira.dietcontrol.dtos.MacrosDto;
import app.vercel.matheusmangueira.dietcontrol.repositories.IUserRepository;

@Component
public class CalculateTmb {

   @Autowired
   private IUserRepository userRepository;

   public Double ObjectUserCalc(UUID id, Double basal) {
      var user = this.userRepository.findById(id).orElseThrow();
      var objectUser = user.getObjectUser().toString();

      System.out.println(basal + " basal ");

      switch (objectUser) {
         case "EMAGRECER":
            basal -= 450;
            break;
         case "GANHO_PESO":
            basal += 450;
            break;
         case "MANTER":
            break;

         default:
            System.out.println("Objetivo desconhecido");
            break;
      }

      System.out.println(basal + " basal ");

      return basal;
   }

   public Double ActivityLevel(UUID id) {
      var user = this.userRepository.findById(id).orElseThrow();
      var level = user.getActivityLevel().toString();
      Double tmb = 0.0;

      switch (level) {
         case "SEDENTARIO":
            tmb = 1.2;
            break;
         case "LEVEMENTE_ATIVO":
            tmb = 1.375;
            break;
         case "MODERADAMENTE_ATIVO":
            tmb = 1.55;
            break;
         case "MUITO_ATIVO":
            tmb = 1.725;
            break;
         case "EXTREMAMENTE_ATIVO":
            tmb = 1.9;
            break;

         default:
            System.out.println("Nível de atividade desconhecido");
            break;
      }

      return tmb;
   }

   public Double BasalCalculate(UUID id) {
      var user = this.userRepository.findById(id).orElseThrow();

      if (user.getSex().toString() == "MASCULINO") {
         var basal = 66.5 + (13.75 * user.getWeight()) + (5.003 * user.getHeight()) - (6.755 * user.getAge());

         var level = ActivityLevel(id);
         var levelXBasal = level * basal;

         var result = ObjectUserCalc(id, levelXBasal);

         CalculateMacros(id, result);

         return result;
      }

      var basal = 655.1 + (9.563 * user.getWeight()) + (1.850 * user.getHeight()) - (4.676 * user.getAge());

      var level = ActivityLevel(id);
      var levelXBasal = level * basal;

      var result = ObjectUserCalc(id, levelXBasal);

      CalculateMacros(id, result);

      return result;

   }

   public MacrosDto CalculateMacros(UUID id, Double basal) {
      var user = this.userRepository.findById(id).orElseThrow();

      var protein = user.getWeight() * 1.8;
      var fat = user.getWeight() * 1.2;
      var carbs = (basal - (protein * 4) - (fat * 9)) / 4;

      return new MacrosDto(protein, fat, carbs);

   }

}

