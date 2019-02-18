package me.seungwoo.springbootjwt.controller;

import lombok.RequiredArgsConstructor;
import me.seungwoo.springbootjwt.domain.AuthenticationToken;
import me.seungwoo.springbootjwt.domain.User;
import me.seungwoo.springbootjwt.util.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-01-28
 * Time: 14:29
 */
@RestController
@RequiredArgsConstructor
public class MainController {

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;


    @GetMapping("/token")
    public ResponseEntity<AuthenticationToken> login(@ModelAttribute User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationToken(jwt));
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        return "home";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello";
    }

    @GetMapping("/obt")
    public String obt(HttpServletRequest request) {
        return "obt";
    }
}
