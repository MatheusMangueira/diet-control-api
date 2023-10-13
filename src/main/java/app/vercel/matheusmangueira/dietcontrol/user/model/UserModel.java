package app.vercel.matheusmangueira.dietcontrol.user.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.vercel.matheusmangueira.dietcontrol.dietList.model.DietListModel;
import app.vercel.matheusmangueira.dietcontrol.user.enums.ActivityLevel;
import app.vercel.matheusmangueira.dietcontrol.user.enums.ObjectUser;
import app.vercel.matheusmangueira.dietcontrol.user.enums.Sex;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_users")
@Table(name = "tb_users")
@EqualsAndHashCode(of = "id")
public class UserModel {

   @Id
   @GeneratedValue(generator = "UUID")
   private UUID id;

   @NotNull
   @NotBlank
   @Size(min = 3, max = 50)
   private String name;

   @Enumerated
   private Sex sex;

   @Enumerated
   private ObjectUser ObjectUser;

   @NotNull
   @Min(1)
   private int age;

   @NotNull
   private int weight;

   @NotNull
   private double height;

   @Enumerated
   private ActivityLevel activityLevel;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   @JsonIgnore
   private List<DietListModel> dietList;

   @CreationTimestamp
   private LocalDateTime createdAt;

}
