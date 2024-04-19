package ru.maksmusic.shop.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maksmusic.shop.entity.User;
import ru.maksmusic.shop.entity.dto.UserDto;
import ru.maksmusic.shop.repository.UserRepository;

import java.util.logging.Logger;
//@RequestParam("confirmPassword") String confirmPassword:
// Этот параметр используется для получения значения пароля подтверждения из запроса.

// Аннотация @RequestParam("confirmPassword") указывает,
// что значение должно быть извлечено из параметра запроса с именем "confirmPassword" и преобразовано в строку.

@Controller
public class RegController {


    UserRepository userRepository;

    public RegController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String regUser(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword) {
        if (bindingResult.hasErrors()) {
            // Если есть ошибки валидации, обработайте их здесь
            // Например, вы можете добавить их в модель и вернуть страницу регистрации с ошибками
            return "registration"; // Название страницы с формой регистрации
        }

        // Ваша логика регистрации пользователя
        Logger.getLogger(RegController.class.getName()).info(user.toString());

        return "redirect:/lk"; // Перенаправление на страницу входа после успешной регистрации
    }



}
