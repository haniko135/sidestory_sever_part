package ru.mirea.ikbo1319.sidestory_server_part.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import javax.sql.DataSource;

@Configuration
@EnableJdbcHttpSession
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "info", "/instruction", "/main", "content", "/registration?**", "/static/**", "/profileAva", "/usersImages/**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                        .antMatchers("/profile?*", "game_pages/**/**/**", "/addNovelToProfile?**",
                        "/hadReadNovelToProfile?**", "/deleteHadReadNovel?**", "/deleteNowReadNovel?**")
                        .hasRole("USER")
                .and()
                    .authorizeRequests()
                        .antMatchers( "/admin?**", "/addNovel?**", "/deleteNovel?**","/addPage?**", "/deletePage?**")
                        .hasAnyRole("ADMIN", "MODERATOR")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/main", true)
                        .permitAll()
                .and()
                    .rememberMe()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/main")
                        .invalidateHttpSession(true)
                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)))
                        .permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password, active from t_user where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from t_user u inner join t_roles ur on u.id = ur.user_id where u.username=?");
    }


}
