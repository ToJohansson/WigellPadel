package tobiasjohansson.wigellpadel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v5/customers").hasRole("ADMIN")
                .antMatchers("/api/v5/register").hasRole("ADMIN")
                .antMatchers("/api/v5/deletebooking/{id}").hasRole("ADMIN")
                .antMatchers("/api/v5/updateinfo").hasRole("ADMIN")
                .antMatchers("/api/v5/bookings/{id}").hasRole("ADMIN")

                .antMatchers("/api/v5/availability").hasRole("USER")
                .antMatchers("/api/v5/mybookings/{id}").hasRole("USER")
                .antMatchers("/api/v5/bookings").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(adminUser());
        manager.createUser(user());
        return manager;
    }

    @Bean
    public UserDetails adminUser() {
        return User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
    }

    @Bean
    public UserDetails user() {
        return User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}