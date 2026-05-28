package com.example.EmployeeManagementSystem.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUplodeController {

    @GetMapping("csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/upload")
   public ResponseEntity<String> uplodeFile(@RequestParam MultipartFile file){

        String uplodeDir ="uploads/";
        File fileDir = new File(uplodeDir);

        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        Path path =Paths.get(uplodeDir,file.getOriginalFilename());
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("File uploaded",HttpStatus.OK);
    }
}
