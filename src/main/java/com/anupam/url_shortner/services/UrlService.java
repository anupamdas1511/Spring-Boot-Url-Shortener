package com.anupam.url_shortner.services;

import com.anupam.url_shortner.models.ApiRequest;

import java.util.Optional;

public interface UrlService {
    Optional<String> shortenUrl(ApiRequest request);
    Optional<String> getUrl(String key);
    Optional<String> updateUrl(String key, String url);
}
