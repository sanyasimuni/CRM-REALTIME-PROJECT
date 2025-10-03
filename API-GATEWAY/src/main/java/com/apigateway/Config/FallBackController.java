package com.apigateway.Config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class FallBackController {
	
	@GetMapping("/fallback/accounts")
    public ResponseEntity<String> accountFallback() {
        return ResponseEntity.ok("Account Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/contact")
    public ResponseEntity<String> contactFallback() {
        return ResponseEntity.ok("Contact Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/leads")
    public ResponseEntity<String> leadsFallback() {
        return ResponseEntity.ok("Lead Service is currently unavailable. Please try again later.");
    }

}
