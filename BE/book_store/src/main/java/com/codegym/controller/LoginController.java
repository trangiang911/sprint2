package com.codegym.controller;

import com.codegym.dto.user.payload.JwtResponse;
import com.codegym.dto.user.payload.LoginRequest;
import com.codegym.dto.user.payload.ResponseMessage;
import com.codegym.service.IRoleService;
import com.codegym.service.IUserRoleService;
import com.codegym.service.IUsersService;
import com.codegym.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUsersService iUsersService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserRoleService iUserRoleService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> getSignIn(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
//        new LoginRequest().validate(loginRequest, bindingResult);
        Map<String, String> errorMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(
                            err -> errorMap.put(err.getField(), err.getDefaultMessage())
                    );
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Failed", errorMap, new ArrayList<>()));
        }

        if (this.iUsersService.checkEmail(loginRequest.getUsername()).isEmpty()) {
            errorMap.put("notExists", "Tài khoản chưa tồn tại trong hệ thống");
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Failed", errorMap, new ArrayList<>()));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtKey(authentication);
        User user = (User) authentication.getPrincipal();
        List<String> role = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt, loginRequest.getUsername(), loginRequest.getPassword(), role)
        );
    }
}
