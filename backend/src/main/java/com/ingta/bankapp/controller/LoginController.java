package com.ingta.bankapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	@PostMapping("/login")
    public  Map<String, Boolean> login(@RequestBody Map<String, Object> req) {
        String email =  (String)req.get("email");
        String password = (String)req.get("password");
        HashMap<String, Boolean> loginResponse = new HashMap<>();
        
        if (email.equals("admin@email.com") && password.equals("123456")) {
        	loginResponse.put("connected", true);
            return loginResponse;
        }
        else {
        	loginResponse.put("connected", false);
            return loginResponse;
        }
        
    }
	
}
