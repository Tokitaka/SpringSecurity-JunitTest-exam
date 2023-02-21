package shop.mtcoding.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.exam.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.exam.dto.UserReqDto.LoginReqDto;
import shop.mtcoding.exam.handler.ex.CustomException;
import shop.mtcoding.exam.model.User;
import shop.mtcoding.exam.service.UserService;
import shop.mtcoding.exam.util.CustomInterceptor;

@Controller
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("principal");
        return "redirect:/loginForm";
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요");
        }
        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 입력해주세요");
        }

        userService.join(joinReqDto);

        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginReqDto loginReqDto) {
        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }

        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요");
        }

        User principal = userService.login(loginReqDto);

        session.setAttribute("principal", principal);

        return "redirect:/main";
    }
}
