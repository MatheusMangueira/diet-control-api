package app.vercel.matheusmangueira.dietcontrol.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import app.vercel.matheusmangueira.dietcontrol.dietList.model.DietListModel;
import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;

public interface IDietListRepository extends JpaRepository<DietListModel, UUID> {

   List<DietListModel> findByUser(UserModel user);

}
