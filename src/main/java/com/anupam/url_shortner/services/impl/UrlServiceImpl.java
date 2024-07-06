package com.anupam.url_shortner.services.impl;

import com.anupam.url_shortner.models.ApiRequest;
import com.anupam.url_shortner.models.Url;
import com.anupam.url_shortner.repositories.UrlRepository;
import com.anupam.url_shortner.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Optional<String> shortenUrl(ApiRequest request) {

        Optional<Url> existingUrl = Optional.ofNullable(urlRepository.findByKey(request.getKey()));
        if (existingUrl.isPresent()) {
            return Optional.empty();
        }
        Url url = new Url();
        url.setUrl(request.getUrl());
        url.setKey(request.getKey());
        url.setCreatedAt(LocalDateTime.now());
        urlRepository.save(url);
        return Optional.ofNullable(url.getKey());
    }

    @Override
    public Optional<String> getUrl(String key) {
        Optional<Url> url = Optional.ofNullable(urlRepository.findByKey(key));
        return Optional.ofNullable(url.orElse(new Url()).getUrl());
    }

    @Override
    public Optional<String> updateUrl(String key, String url) {
        Optional<Url> existingUrl = Optional.ofNullable(urlRepository.findByKey(key));
        if (existingUrl.isEmpty()) {
            return Optional.empty();
        }

        Url exUrl = existingUrl.get();
        exUrl.setUrl(url);
        urlRepository.save(exUrl);
        return Optional.ofNullable(exUrl.getKey());
    }
}
