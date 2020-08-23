package pl.kancelaria.AHG.administration.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Michal
 * @created 20/08/2020
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                //.antMatchers("uzytkownicy/secured/listaUzytkownikow").hasRole("ADMIN")
                //.antMatchers("/uzytkownicy/secured/rejestracja").permitAll()

                .and()
                .formLogin().loginPage("/").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().logoutSuccessUrl("/");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        User userAdmin = new User("Jan",
//                getPasswordEncoder().encode("Jan123"),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
//        User userUser = new User("Kazik",
//                getPasswordEncoder().encode("Kazik123"),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//
//        auth.jdbcAuthentication().withUser(userAdmin);
//        auth.jdbcAuthentication().withUser(userUser);


    }
}
