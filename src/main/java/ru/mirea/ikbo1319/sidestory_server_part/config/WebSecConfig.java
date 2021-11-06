package ru.mirea.ikbo1319.sidestory_server_part.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.sql.DataSource;

@Configuration
@EnableJdbcHttpSession
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationFailureHandler securityErrorHandler;
    private final ConcurrentSessionStrategy concurrentSessionStrategy;
    private final SessionRegistry sessionRegistry;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "info", "/instruction", "/main", "content", "/registration?**", "/static/**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/profile?*", "game_pages/**/**/**", "/addNovelToProfile?**",
                            "/hadReadNovelToProfile?**", "/deleteHadReadNovel?**", "/deleteNowReadNovel?**").hasRole("USER")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/main",true)
                        .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true).permitAll().logoutSuccessUrl("/main")
                .and()
                    .sessionManagement()
                    //Указываем макимальное возможное количество сессий(тут указано не 1, т.к. мы будем пользоваться своей кастомной стратегией, объяснение будет ниже)
                    .maximumSessions(3)
                    //При превышение количества активных сессий(3) выбрасывается исключение  SessionAuthenticationException
                    .maxSessionsPreventsLogin(true)
                    //Указываем как будут регестрироваться наши сессии(тогда во всем приложение будем использовать именно этот бин)
                    .sessionRegistry(sessionRegistry).and()
                    //Добавляем нашу кастомную стратегию для проверки кличества сессий
                    .sessionAuthenticationStrategy(concurrentSessionStrategy)
                    //Добавляем перехватчик для исключений
                    .sessionAuthenticationFailureHandler(securityErrorHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password, active from t_user where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from t_user u inner join t_roles ur on u.id = ur.user_id where u.username=?");
    }

    //для инвалидации сессий при логауте
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public static SessionRegistry sessionRegistry(JdbcIndexedSessionRepository sessionRepository) {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

}
