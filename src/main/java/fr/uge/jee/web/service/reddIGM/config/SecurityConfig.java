package fr.uge.jee.web.service.reddIGM.config;

import fr.uge.jee.web.service.reddIGM.filters.JwtRequestFilter;
import fr.uge.jee.web.service.reddIGM.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf()

                .disable().authorizeRequests()

                // No need authentication.
                .antMatchers("/login").permitAll() //
                .antMatchers(HttpMethod.POST, "/login").permitAll() //


                .antMatchers(HttpMethod.GET,"/comments/**").permitAll() //
                .antMatchers(HttpMethod.GET,"/posts/**").permitAll() //
                .antMatchers(HttpMethod.GET,"/subjects/**").permitAll() //



                // No need authentication.
                .antMatchers("/register").permitAll() //
                .antMatchers(HttpMethod.POST, "/register").permitAll() //
                .antMatchers("/register").permitAll() //

                .antMatchers(HttpMethod.GET,"/users/").permitAll() //
                .antMatchers(HttpMethod.GET,"/users/**").permitAll() //
                .antMatchers(HttpMethod.POST,"/users/**").permitAll() //

                // Need authentication.
                .anyRequest().authenticated()

                // Set the session security
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
