package com.greenerself.sustainability.auth.controller;

import com.greenerself.sustainability.auth.service.IAuthService;
import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.util.vo.LoginRequest;
import com.greenerself.sustainability.util.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.greenerself.sustainability.constants.ApplicationConstants.SecurityEndpoints.LOGIN;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<ResponseVO> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        ResponseVO resp = new ResponseVO();
        resp.setData(token);
        resp.setSuccess(true);
        resp.setActionMessage("Login successful");
        return ResponseEntity.ok(resp);
//        } else {
//            return ResponseEntity.status(401).body(new ResponseMessage("Invalid credentials"));
//        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseVO> register(@RequestBody User user) {
        authService.register(user);
        ResponseVO resp = new ResponseVO();
        resp.setData(user.getUsername());
        resp.setSuccess(true);
        resp.setActionMessage("User registered successfully");
        return ResponseEntity.ok(resp);
//        } else {
//            return ResponseEntity.status(400).body(new ResponseMessage("Registration failed"));
//        }
    }
}
