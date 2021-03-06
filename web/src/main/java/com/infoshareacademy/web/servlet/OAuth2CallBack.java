package com.infoshareacademy.web.servlet;


import com.infoshareacademy.OAuth.GoogleAuthHelper;
import com.infoshareacademy.dto.UserDTO;
import com.infoshareacademy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;

@WebServlet("/oauth2callback")
public class OAuth2CallBack extends HttpServlet {

    @Inject
    UserService userService;

    GoogleAuthHelper googleAuthHelper = new GoogleAuthHelper();

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("REQEST: {}", req.getRequestURI());
        String param = req.getParameter("code");
        String user = googleAuthHelper.getUserInfoJson(param);

        logger.info("User: " + user);
        JsonObject userInfoJson = Json.createReader(new StringReader(user)).readObject();
        String email = userInfoJson.getString("email");

        if (userService.findUserByEmail(email) == null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(userInfoJson.getString("email"));
            userDTO.setName(userInfoJson.getString("name"));
            userDTO.setRole("User");
            userService.addUserToEntity(userDTO);
            req.getSession().setAttribute("email", userDTO.getEmail());
            req.getSession().setAttribute("name", userDTO.getName());
            req.getSession().setAttribute("role", userDTO.getRole());
            resp.sendRedirect("");
            logger.info("User Added");
        } else {
            req.getSession().setAttribute("email", userService.findUserByEmail(email).getEmail());
            req.getSession().setAttribute("name", userService.findUserByEmail(email).getName());
            req.getSession().setAttribute("role", userService.findUserByEmail(email).getRole());
            resp.sendRedirect("");
        }
    }
}

