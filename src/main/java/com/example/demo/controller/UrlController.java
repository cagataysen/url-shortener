package com.example.demo.controller;

import com.example.demo.entity.Url;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UrlService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private UrlService urlService;

    UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public Url shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        return urlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException, IOException {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        response.sendRedirect(originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }
}
