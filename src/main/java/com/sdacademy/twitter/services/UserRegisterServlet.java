package com.sdacademy.twitter.services;

import com.sdacademy.twitter.dao.UserDao;
import com.sdacademy.twitter.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Class responsible for user registration
 */
@WebServlet(name = "registerServlet", value = "/register")
public class UserRegisterServlet extends HttpServlet {

    private UserDao userDao;

    public UserRegisterServlet() {
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String nick = req.getParameter("nick");
        final String email = req.getParameter("mail");
        final String pass1 = req.getParameter("pass1");
        final String pass2 = req.getParameter("pass2");

        if (isNullOrEmpty(nick)) {
            resp.sendRedirect("register.jsp?err=nonick");
            return;
        }

        if (isNullOrEmpty(email)) {
            resp.sendRedirect("register.jsp?err=nomail");
            return;
        }

        if (isNullOrEmpty(pass1) || isNullOrEmpty(pass2)) {
            resp.sendRedirect("register.jsp?err=nopass");
            return;
        }

        if (!pass1.equals(pass2)) {
            resp.sendRedirect("register.jsp?err=passnotmatch");
            return;
        }

        Optional<User> id = userDao.add(User.builder().email(email).nick(nick).password(pass1).creationTS(System.currentTimeMillis()).build());
        resp.addCookie(new Cookie(UserDao.USER_SESSION, id.get().getId().toString()));
        resp.sendRedirect("index.jsp");
    }

    private boolean isNullOrEmpty(final String val) {
        return (val == null || val == "");
    }
}
