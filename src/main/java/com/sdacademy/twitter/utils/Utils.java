package com.sdacademy.twitter.utils;

import com.sdacademy.twitter.dao.CommentDao;
import com.sdacademy.twitter.dao.UserDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Class contains utilities used during JSP parsing
 */
public final class Utils {
    private Utils() {
    }

    public static void activateSession(final HttpServletRequest request) {
        Optional<Long> id = getUserIdFromSession(request);
        request.setAttribute("sessionOk", id.isPresent());
        if (id.isPresent()) {
            request.setAttribute("userId", id.get());
        }
    }

    public static Optional<Long> getUserIdFromSession(final HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (UserDao.USER_SESSION.equals(cookie.getName())) {
                    return Optional.of(Long.valueOf(cookie.getValue()));
                }
            }
        }
        return Optional.empty();
    }
}
