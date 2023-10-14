package app.vercel.matheusmangueira.dietcontrol.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import app.vercel.matheusmangueira.dietcontrol.dtos.UserDto;
import app.vercel.matheusmangueira.dietcontrol.repository.IUserRepository;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;
import app.vercel.matheusmangueira.dietcontrol.utils.CalculateTmb;
import app.vercel.matheusmangueira.dietcontrol.utils.UserMapper;
import jakarta.validation.Valid;

@Service
public class UserService {

   @Autowired
   private IUserRepository userRepository;

   @Autowired
   private CalculateTmb utils;

   public List<UserModel> getAllUsers() {
      return this.userRepository.findAll();
   }

   public UserModel createUser(UserModel userModel) {
      return this.userRepository.save(userModel);
   }

   public UserDto findUserById(@Valid UUID id) {
      var basal = this.utils.BasalCalculate(id);
      var macros = this.utils.CalculateMacros(id, basal);
      var user = this.userRepository.findById(id).orElseThrow();

      return UserMapper.toDto(user, id, basal, macros);
   }
}
