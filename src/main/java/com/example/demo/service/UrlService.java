package com.example.demo.service;

import com.example.demo.entity.Url;
import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url shortenUrl(String originalUrl) {

        String shortUrl = generateShortUrl(originalUrl);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);

        return urlRepository.save(url);
    }

    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL bulunamadı"));
    }

    private String generateShortUrl(String originalUrl) {
        return Base64.getUrlEncoder().encodeToString(originalUrl.getBytes()).substring(0, 6);
    }
}
