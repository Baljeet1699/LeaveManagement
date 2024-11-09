package com.HR.LeaveManagementSystem.controllers;

import com.HR.LeaveManagementSystem.entities.Employee;
import com.HR.LeaveManagementSystem.exceptions.ApiException;
import com.HR.LeaveManagementSystem.payloads.EmployeeDto;
import com.HR.LeaveManagementSystem.payloads.JwtAuthRequest;
import com.HR.LeaveManagementSystem.payloads.JwtAuthResponse;
import com.HR.LeaveManagementSystem.security.JwtHelper;
import com.HR.LeaveManagementSystem.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins="*")
public class AuthController {


    @Autowired
    private JwtHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{

        this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setEmployee(this.modelMapper.map((Employee)userDetails, EmployeeDto.class));

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.CREATED);

    }


    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {

            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        }catch(BadCredentialsException e) {

            System.out.println("Invalid details");
            throw new ApiException("Invalid username or password");

        }

    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> registerUser(@RequestBody EmployeeDto employeeDto){
        EmployeeDto registeredEmployee = this.employeeService.registerNewEmployee(employeeDto);
        return new ResponseEntity<EmployeeDto>(registeredEmployee,HttpStatus.CREATED);

    }

    @GetMapping("/ai/generate/{message}")
    public String generate(@PathVariable String message) {
        return ollamaChatModel.call(message);
    }

}
