package app.vercel.matheusmangueira.dietcontrol.user.dto;

import java.util.UUID;

import org.springframework.stereotype.Service;

import app.vercel.matheusmangueira.dietcontrol.user.enums.ActivityLevel;
import app.vercel.matheusmangueira.dietcontrol.user.enums.Sex;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class UserDto {

   private UUID id;
   private String name;
   private Sex sex;
   private int age;
   private int weight;
   private double height;
   private ActivityLevel activityLevel;

}
