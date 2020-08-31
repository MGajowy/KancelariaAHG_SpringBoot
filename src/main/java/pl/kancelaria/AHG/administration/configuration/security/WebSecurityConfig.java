package pl.kancelaria.AHG.administration.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.kancelaria.AHG.user.services.UserDetailsServiceImpl;

import java.util.Arrays;

/**
 * @author Michal
 * @created 20/08/2020
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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
                .antMatchers("/rest/kategorie/pub/wszystkieKategorie").hasRole("USER")
                //.antMatchers("/uzytkownicy/secured/rejestracja").permitAll()

                .and()
                .formLogin().defaultSuccessUrl("/").permitAll()
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
auth.userDetailsService(userDetailsService);

    }
}
