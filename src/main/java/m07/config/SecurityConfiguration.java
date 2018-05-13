package m07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    super.configure(auth);
    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select id,password, enabled from customers where id = ?")
        .authoritiesByUsernameQuery("select customerId, role from role where customerId=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    super.configure(http);
    http.csrf().disable();
    http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
    http.authorizeRequests().antMatchers("/customer/**").access("hasRole('ROLE_USER')");
    http.authorizeRequests().anyRequest().permitAll();
    http.authorizeRequests().and().formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/j_spring_security_check")
        .usernameParameter("id").passwordParameter("password")
        // sau khi submit form login thanh cong
        .defaultSuccessUrl("/admin/report/")
        .failureUrl("/login?error=failed").and().exceptionHandling().accessDeniedPage("/login?error=deny")
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
//    super.configure(web);
    web.ignoring().antMatchers("/resources/**");
    web.ignoring().antMatchers("/favicon.ico");
  }
}
