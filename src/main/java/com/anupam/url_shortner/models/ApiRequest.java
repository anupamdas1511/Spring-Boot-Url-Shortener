package com.anupam.url_shortner.models;

import lombok.Data;

@Data
public class ApiRequest {
    private String url;
    private String key;
}
