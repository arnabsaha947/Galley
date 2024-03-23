package com.example.Galley.Controller;

import com.example.Galley.Model.JwtRequestModel;
import com.example.Galley.Model.JwtResponseModel;
import com.example.Galley.Utility.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    private JwtResponseModel jwtResponse;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> login(@RequestBody JwtRequestModel jwtRequest){

        this.doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);

        jwtResponse = new JwtResponseModel();
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setJwtToken(token);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (Exception ex){
            System.out.println("Invalid username or password in doAuthenticate "+ex);
        }

    }
}
