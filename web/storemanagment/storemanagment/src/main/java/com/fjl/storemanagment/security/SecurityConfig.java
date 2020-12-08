package com.fjl.storemanagment.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityManager accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	 http.sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
         			.authorizeRequests()
                    .antMatchers("/js/**",
                            "/css/**",
                            "/img/**",	
                            "/webjars/**",
                            "/API/**",
                    		"/swagger-ui.html#/**").permitAll()
                    .antMatchers("/Market/**").hasRole("USER")
                    .antMatchers("/Stock/**","/Sales/**").hasAnyRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    			.inMemoryAuthentication()
    			.withUser("user").password(passwordEncoder().encode("password")).roles("USER")
    			.and()
    			.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}