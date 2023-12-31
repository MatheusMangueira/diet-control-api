package app.vercel.matheusmangueira.dietcontrol.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

}
