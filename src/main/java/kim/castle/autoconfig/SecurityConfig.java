package kim.castle.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import kim.castle.security.ClientTokenBasedRemeberMeServices;
import kim.castle.security.DefaultAuthenticationSuccessHandler;
import kim.castle.security.DefaultDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String REMEMBER_KEY = "remember-key";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().requestCache().disable().authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/bower_components/**", "/img/**", "/swagger-ui.html",
                        "/swagger-resources/**", "/v2/api-docs", "/webjars/**")
                .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/").permitAll().and().logout().permitAll().and()
                .rememberMe().key(REMEMBER_KEY).rememberMeServices(rememberMeServices());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new DefaultDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        return new ClientTokenBasedRemeberMeServices(REMEMBER_KEY, userDetailsService(), persistentTokenRepository());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

}
