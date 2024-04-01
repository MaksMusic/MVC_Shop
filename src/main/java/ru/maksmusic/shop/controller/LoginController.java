package ru.maksmusic.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;


import org.springframework.security.access.prepost.PreAuthorize;


@Controller
public class LoginController {

    @GetMapping("/login")
    @PreAuthorize("isAuthenticated()")
    public String loginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, аутентифицирован ли пользователь
        if (authentication != null && authentication.isAuthenticated()) {
            // Если пользователь аутентифицирован, получаем его имя
            String username = authentication.getName();

            // Передаем имя пользователя в модель
            model.addAttribute("username", username);
        }

        return "login"; // Имя шаблона HTML страницы
    }
}
