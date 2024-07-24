package com.anupam.url_shortner.controllers;

import com.anupam.url_shortner.models.ApiRequest;
import com.anupam.url_shortner.services.UrlService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UrlController {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<?> shortenUrl(@RequestBody ApiRequest request) {
        String baseUrl = getBaseUrl();
        Optional<String> key = urlService.shortenUrl(request);
        if (key.isEmpty()) {
            return ResponseEntity.badRequest().body("Given Key already exists");
        }

        String shortenedUrl = baseUrl + key.get();
        return ResponseEntity.ok(shortenedUrl);
    }

    @GetMapping("/{key}")
    public ResponseEntity<Void> redirectToUrl(@PathVariable String key) {
        // Find URL with the given key
        Optional<String> redirectUrl = urlService.getUrl(key);

        if (redirectUrl.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.LOCATION, redirectUrl.get());
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            // Handle URL not found, redirect to an error page or return a 404 status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUrl(@RequestBody ApiRequest request) {
        String baseUrl = getBaseUrl();
        Optional<String> key = urlService.updateUrl(request.getKey(), request.getUrl());
        if (key.isEmpty()) {
            return ResponseEntity.badRequest().body("Key Not Found");
        }

        String shortenedUrl = baseUrl + key.get();
        return ResponseEntity.ok(shortenedUrl);
    }

    public String getBaseUrl() {
        String scheme = httpServletRequest.getScheme(); // http or https
        String serverName = httpServletRequest.getServerName(); // localhost or domain
        int serverPort = httpServletRequest.getServerPort(); // port number
        String contextPath = httpServletRequest.getContextPath(); // context path (if any)

        // Construct the base URL
        if (serverName.equals("localhost")) {
            return scheme + "://" + serverName + ":" + serverPort + contextPath;
        }
        return scheme + "://" + serverName + "/" + contextPath;
    }
}
