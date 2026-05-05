package com.code;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class RequestHandler {

    public static Integer parseIdFromPath(String pathInfo) {
        if (pathInfo == null || !pathInfo.startsWith("/")) {
            return null;
        }
        try {
            return Integer.parseInt(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String getParam(HttpServletRequest request, Map<String, String> params, String name) {
        String value = request.getParameter(name);
        if (value != null) {
            return value;
        }
        return params.get(name);
    }

    public static Map<String, String> readFormBody(HttpServletRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        StringBuilder body = new StringBuilder();

        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        if (body.length() == 0) {
            return params;
        }

        String[] pairs = body.toString().split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf('=');
            if (idx > -1) {
                String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name());
                String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name());
                params.put(key, value);
            }
        }
        return params;
    }


    public static String sanitizeString(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    public static String firstNonBlank(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value.trim();
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (java.io.UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }
}

