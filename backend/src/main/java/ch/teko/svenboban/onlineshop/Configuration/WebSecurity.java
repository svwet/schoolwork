package ch.teko.svenboban.onlineshop.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * @author sven.wetter@edu.teko.ch
 */

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(jdbcTemplate.getDataSource()).usersByUsernameQuery(
                "select username,password,enabled from users where username=?").authoritiesByUsernameQuery(
                        "select username, authority from authorities where username=?").passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http)  throws Exception {



        http.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();


        /**
        http
                .authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
         **/

        http.authorizeRequests().mvcMatchers("/rest/*").authenticated().and().httpBasic();
    }

    @Bean
    @Override
    public JdbcUserDetailsManager userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setJdbcTemplate(jdbcTemplate);
        return manager;
    }
}
