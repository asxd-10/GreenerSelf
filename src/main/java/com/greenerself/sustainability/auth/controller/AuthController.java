package com.greenerself.sustainability.auth.controller;

import com.greenerself.sustainability.auth.service.IAuthService;
import com.greenerself.sustainability.util.vo.LoginRequest;
import com.greenerself.sustainability.util.vo.ResponseVO;
import com.greenerself.sustainability.util.vo.UserRegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.greenerself.sustainability.constants.ApplicationConstants.ControllerEndpoints.AUTH;
import static com.greenerself.sustainability.constants.ApplicationConstants.ControllerEndpoints.MAIN;
import static com.greenerself.sustainability.constants.ApplicationConstants.SecurityEndpoints.LOGIN;
import static com.greenerself.sustainability.constants.ApplicationConstants.SecurityEndpoints.REGISTER;

@RestController
@RequestMapping(AUTH)
@ControllerAdvice
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

    @PostMapping(REGISTER)
    public ResponseEntity<ResponseVO> register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        authService.register(userRegistrationRequest);
        ResponseVO resp = new ResponseVO();
        resp.setData(userRegistrationRequest.getUsername());
        resp.setSuccess(true);
        resp.setActionMessage("User registered successfully");
        return ResponseEntity.ok(resp);
//        } else {
//            return ResponseEntity.status(400).body(new ResponseMessage("Registration failed"));
//        }
    }
}
