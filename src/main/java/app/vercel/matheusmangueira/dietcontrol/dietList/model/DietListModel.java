package app.vercel.matheusmangueira.dietcontrol.dietList.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.vercel.matheusmangueira.dietcontrol.user.model.UserModel;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_diet_list")
@Table(name = "tb_diet_list")
@EqualsAndHashCode(of = "id")
public class DietListModel {

   @Id
   @GeneratedValue(generator = "UUID")
   private UUID id;

   @NotNull
   private String foodName;

   @NotNull
   private Double protein;

   @NotNull
   private Double carb;

   @NotNull
   private Double fat;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   @JsonIgnore
   private UserModel user;

}
