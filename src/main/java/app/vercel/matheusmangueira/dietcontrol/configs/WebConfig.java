package app.vercel.matheusmangueira.dietcontrol.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
   
   
   public void AddCorsMappings(CorsRegistry registry  ){
      
      registry.addMapping("/**");

   }

      
}
