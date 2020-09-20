/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.RestController;

import StudioMedico.Model.User;
import StudioMedico.Repository.AuthoritieRepository;
import StudioMedico.Repository.UserRepository;
import StudioMedico.Service.UserService;
import StudioMedico.Utilities.JwtResponse;
import StudioMedico.Utilities.JwtUtils;
import StudioMedico.Utilities.LoginRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritieRepository authoritieRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String p = authentication.getName();
        User user = userRepository.findByUsername(p);

        return ResponseEntity.ok(new JwtResponse(jwt, user.getUsername(), user.getAuthoritie().getAuthoritie()));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> postAsUser(@RequestBody @Valid User user) {
        userService.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
