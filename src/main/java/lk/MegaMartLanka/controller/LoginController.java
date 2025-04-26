package lk.MegaMartLanka.controller;

import lk.MegaMartLanka.dto.AuthReturnDto;
import lk.MegaMartLanka.dto.LoginDto;
import lk.MegaMartLanka.entity.User;
import lk.MegaMartLanka.security.JwtUtil;
import lk.MegaMartLanka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("api/v1/MegaMartLanka")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthReturnDto login(@RequestBody LoginDto user) {
        List<User> users = userService.getAll ();

        if (users.isEmpty ()) {
            User admin = new User ();
            admin.setUsername ("admin");
            admin.setPassword ("admin123");
            admin.setFullname ("Admin Admin");
            admin.setUserType ("admin");
            userService.create (admin);
        }

        Authentication authentication = authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (user.getUsername (), user.getPassword ()));

        SecurityContextHolder.getContext ().setAuthentication (authentication);

        String jwtToken = jwtUtils.generateJwtToken (authentication);

        AuthReturnDto dto = new AuthReturnDto ();
        dto.setJwtToken (jwtToken);
        dto.setUsertype (userService.getByUsername (user.getUsername ()).getUserType ());
        return dto;
    }
}