package com.greenerself.sustainability.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiUrlBuilder {

    public static String buildUrl(String baseUrl, String apiKey, Map<String, String> queryParams) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?apiKey=").append(apiKey);

        queryParams.forEach((key, value) -> {
            urlBuilder.append("&")
                    .append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(value, StandardCharsets.UTF_8));
        });

        return urlBuilder.toString();
    }
}
