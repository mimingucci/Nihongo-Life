package com.nihongo.admin.security;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	
	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		
//		return authProvider;
//	}
	
   @Bean
   public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
   
   @Bean
   public AuthenticationManager authenticationManager(
           AuthenticationConfiguration authConfig) throws Exception {
       return authConfig.getAuthenticationManager();
   }
   
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  // http.authenticationProvider(authenticationProvider());
       http.csrf().disable()
       .authorizeHttpRequests()
       .anyRequest().permitAll();
//       .antMatchers("/states/list_by_country/**").hasAnyAuthority("Admin", "Salesperson")
//		.antMatchers("/users/**", "/settings/**", "/countries/**", "/states/**").hasAuthority("Admin")
//		.antMatchers("/categories/**", "/brands/**").hasAnyAuthority("Admin", "Editor")
//		
//		.antMatchers("/products/new", "/products/delete/**").hasAnyAuthority("Admin", "Editor")
//		
//		.antMatchers("/products/edit/**", "/products/save", "/products/check_unique")
//			.hasAnyAuthority("Admin", "Editor", "Salesperson")
//			
//		.antMatchers("/products", "/products/", "/products/detail/**", "/products/page/**")
//			.hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
//			
//		.antMatchers("/products/**").hasAnyAuthority("Admin", "Editor")
//		
//		.antMatchers("/orders", "/orders/", "/orders/page/**", "/orders/detail/**").hasAnyAuthority("Admin", "Salesperson", "Shipper")
//		
//		.antMatchers("/products/detail/**", "/customers/detail/**").hasAnyAuthority("Admin", "Editor", "Salesperson", "Assistant")
//
//		.antMatchers("/customers/**", "/orders/**", "/get_shipping_cost", "/reports/**").hasAnyAuthority("Admin", "Salesperson")
//		
//		.antMatchers("/orders_shipper/update/**").hasAuthority("Shipper")
//		
//		.antMatchers("/reviews/**").hasAnyAuthority("Admin", "Assistant")
//       .antMatchers("/login").permitAll()
//       .anyRequest().authenticated()
//           .and()
//           .formLogin()
//               .loginPage("/login")
//               .loginProcessingUrl("/login")
//               .defaultSuccessUrl("/test")
//               .usernameParameter("email")
//               .permitAll()
//            .and().logout().permitAll()
//            .and()
//               .rememberMe()
//                   .key("toivaban12345")
//                   .tokenValiditySeconds(7 * 24 * 60 * 60);
       http.headers().frameOptions().sameOrigin();
       return http.build();
   }
   
  
//   @Bean
//   public WebSecurityCustomizer webSecurityCustomizer() {
//       return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//    		   
//    		   //ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//   }
}
