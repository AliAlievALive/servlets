package org.example.servlet.framework.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.app.controller.UserController;
import org.example.servlet.framework.handler.Handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FrontServlet extends HttpServlet {
    private final Map<String, Handler> handlers = new HashMap<>();
    final Handler notFoundHandler = (req, resp) -> resp.sendError(404);

    final Handler internalServerErrorHandler = (req, resp) -> resp.sendError(500);

    @Override
    public void init() {
        final UserController userController = new UserController();
        handlers.put("/users.me", userController::me);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        final String path = req.getRequestURI().substring(req.getContextPath().length());
        final Handler handler = Optional.ofNullable(handlers.get(path))
                .orElse(notFoundHandler);
        try {
            handler.handle(req, resp);
        } catch (Exception e) {
            try {
                internalServerErrorHandler.handle(req, resp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
