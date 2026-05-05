package com.code;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for handling HTTP request data parsing and manipulation.
 * Provides methods for extracting parameters, parsing IDs, and encoding/decoding values.
 */
public class RequestHandler {

    /**
     * Parses a user ID from the URL path.
     * Expected format: /123 (ID after the first slash)
     * @param pathInfo the path info from the request
     * @return the parsed ID, or null if invalid format
     */
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

    /**
     * Gets a parameter value from request or from a map (for PUT/DELETE requests).
     * @param request the HTTP request
     * @param params the map of parameters (used as fallback)
     * @param name the parameter name
     * @return the parameter value, or null if not found
     */
    public static String getParam(HttpServletRequest request, Map<String, String> params, String name) {
        String value = request.getParameter(name);
        if (value != null) {
            return value;
        }
        return params.get(name);
    }

    /**
     * Reads the form body from a PUT/DELETE request and parses it into key-value pairs.
     * @param request the HTTP request
     * @return map of parsed form parameters
     * @throws IOException if reading the request fails
     */
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

    /**
     * Sanitizes a string by trimming whitespace and handling null values.
     * @param value the input string
     * @return trimmed string, or empty string if null
     */
    public static String sanitizeString(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    /**
     * Returns the first non-blank value or a fallback value.
     * @param value the primary value to check
     * @param fallback the fallback value if primary is blank
     * @return either value (trimmed) or fallback
     */
    public static String firstNonBlank(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value.trim();
    }

    /**
     * URL encodes a value for safe inclusion in URLs.
     * @param value the value to encode
     * @return the URL-encoded value
     */
    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (java.io.UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }
}

