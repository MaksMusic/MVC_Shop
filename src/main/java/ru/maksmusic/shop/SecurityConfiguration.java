package ru.maksmusic.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // отключаем защиту от межсайтовой подделки запросов
                // Cross-Site Request Forgery (CSRF)
                //.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                // указываем на то, какие страницы должны быть защищены аутентификацией.
                                .requestMatchers("/admin").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                // указываем на то, какие страницы не должны быть защищены аутентификацией
                                .requestMatchers("/authentication", "/logout", "/registration", "/index")
                                .permitAll()
                                // любые страницы не описанные в матчерах выше
                                // будут доступны пользователям с ролями user и admin
                                .anyRequest()
                                .hasAnyRole("USER", "ADMIN")
                )
                .formLogin((formLogin) ->
                        formLogin
                                // Указываем какой url запрос будет отправляться при заходе на защищенные страницы
                                .loginPage("/")
                                // Указываем на какой адрес будут отправляться данные с формы.
                                // Нам уже не нужно будет создавать метод в контроллере и обрабатывать данные с формы.
                                // Мы задали url, который используется по умолчанию для обработки формы аутентификации по средствам Spring Security.
                                // Spring Security будет ждать объект с формы аутентификации и затем сверять логин и пароль с данными в БД
                                .loginProcessingUrl("/login")
                                // Указываем на какой url необходимо направить пользователя после успешной аутентификации.
                                // Вторым аргументом указывается true чтобы перенаправление шло в любом случае послу успешной аутентификации
                                .defaultSuccessUrl("/product", true)
                                .permitAll()
                                // Указываем куда необходимо перенаправить пользователя при проваленной аутентификации.
                                // В запрос будет передан объект error, который будет проверять на форме и при наличии данного объекта в запросе выводится сообщение "Неправильный логин или пароль"
                                .failureUrl("/authentication?error")
                )
                .logout((logout) ->
                        logout
                                // аналогично, что и выше
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/authentication")
                                .permitAll()
                );
        return http.build();
    }

}