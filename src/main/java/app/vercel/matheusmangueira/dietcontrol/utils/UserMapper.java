package app.vercel.matheusmangueira.dietcontrol.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import app.vercel.matheusmangueira.dietcontrol.dtos.MacrosDto;
import app.vercel.matheusmangueira.dietcontrol.dtos.UserDto;
import app.vercel.matheusmangueira.dietcontrol.repository.IUserRepository;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;

public class UserMapper {

   public static UserDto toDto(
         UserModel user,
         UUID id,
         Double basal,
         MacrosDto macros) {

      return new UserDto(
            user.getId(),
            user.getName(),
            user.getSex(),
            user.getAge(),
            user.getWeight(),
            user.getHeight(),
            user.getActivityLevel(),
            user.getObjectUser(),
            basal,
            macros);
   }
}
