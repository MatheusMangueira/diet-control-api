package app.vercel.matheusmangueira.dietcontrol.dtos;

import java.util.List;
import java.util.UUID;

import app.vercel.matheusmangueira.dietcontrol.user.enums.ActivityLevel;
import app.vercel.matheusmangueira.dietcontrol.user.enums.ObjectUser;
import app.vercel.matheusmangueira.dietcontrol.user.enums.Sex;

public record UserDto(
      UUID id,
      String name,
      Sex sex,
      int age,
      int weight,
      Double height,
      ActivityLevel activityLevel,
      ObjectUser objectUser,
      Double basal,
      MacrosDto macros)

{

}
