package org.example.servlet.framework.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler {
    void handle(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
