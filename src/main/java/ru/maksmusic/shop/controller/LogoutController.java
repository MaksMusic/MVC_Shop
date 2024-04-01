package ru.maksmusic.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Получаем текущую сессию (если она существует) и удаляем атрибуты пользователя
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("username");
            session.invalidate(); // Завершаем сессию
        }

        // Редиректим пользователя на главную страницу или другую страницу по вашему выбору
        return "redirect:/"; // Имя URL для перенаправления
    }
}
