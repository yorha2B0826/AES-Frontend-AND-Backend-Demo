package com.example.demo.controller;

import com.example.demo.domain.UserDetailsFormDTO;
import com.example.demo.utils.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AESController {

    private static final String SECRET_KEY = "1234567890123456"; // 16-byte key for AES-128
    private static final String IV = "1234567890123456"; // 16-byte IV for AES
    private final ObjectMapper objectMapper;

    public AESController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/process")
    public String process(@RequestBody Map<String, String> requestBody) {
        try {
            String encryptedData = requestBody.get("data");
            String decryptedData = AESUtil.decrypt(encryptedData, SECRET_KEY, IV);
            System.out.println("Decrypted data from client: " + decryptedData);

            UserDetailsFormDTO userDetails = objectMapper.readValue(decryptedData, UserDetailsFormDTO.class);
            System.out.println("UserDetailsFormDTO: " + userDetails);

            String responseMessage = "Hello, " + userDetails.getName() + " too";

            return AESUtil.encrypt(responseMessage, SECRET_KEY, IV);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }
}

