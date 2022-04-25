package org.example.servlet.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserController {
    public void me(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.getWriter().write("me");
    }
}
