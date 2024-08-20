package com.example.demo.repository;


import com.example.demo.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);

}
