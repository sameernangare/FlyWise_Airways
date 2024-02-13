package com.example.airline_reservation.controllers;


import javax.validation.Valid;

import com.example.airline_reservation.dtos.SigninRequest;
import com.example.airline_reservation.dtos.SigninResponse;
import com.example.airline_reservation.dtos.Signup;
import com.example.airline_reservation.security.JwtUtils;
import com.example.airline_reservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserSigninSignupContoller {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils utils;

    @Autowired
    private AuthenticationManager mgr;

    // sign up
    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@RequestBody @Valid Signup dto) {
        System.out.println("in sign up " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegistration(dto));
    }

    /*
     * request payload : Auth req DTO : email n password resp payload : In case of
     * success : Auth Resp DTO : mesg + JWT token + SC 200 IN case of failure : SC
     * 401
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signinUser(@RequestBody @Valid SigninRequest reqDTO) {
        System.out.println("in signin " + reqDTO);
        // simply invoke authenticate(...) on AuthMgr
        // i/p : Authentication => un verifed credentials
        // i/f --> Authentication --> imple by UsernamePasswordAuthToken
        // throws exc OR rets : verified credentials (UserDetails i.pl class: custom
        // user details)

        Authentication verifiedAuth = mgr
                .authenticate(new UsernamePasswordAuthenticationToken
                        (reqDTO.getEmail(), reqDTO.getPassword()));
        System.out.println(verifiedAuth.getClass());// Custom user details
        // => auth success
        return ResponseEntity
                .ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
    }
}