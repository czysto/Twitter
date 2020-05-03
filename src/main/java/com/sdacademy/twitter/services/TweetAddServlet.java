package com.sdacademy.twitter.services;

import com.sdacademy.twitter.dao.TweetDao;
import com.sdacademy.twitter.dao.UserDao;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet responsible for adding tweet
 */
@WebServlet(name = "addTweetServlet" , value = "/addTweet")
public class TweetAddServlet extends HttpServlet {
    private UserDao userDao;
    private TweetDao tweetDao;

    public TweetAddServlet() {
        userDao = UserDao.getInstance();
        tweetDao = TweetDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String message = req.getParameter("message");
        final Optional<Long> userId = Utils.getUserIdFromSession(req);
        if (userId.isPresent()) {
            Optional<User> user = userDao.get(userId.get());
            if (user.isPresent()) {
                final Tweet tweet = Tweet.builder()
                        .message(message)
//                        .reTweet(null)
                        .user(user.get())
                        .creationTS(System.currentTimeMillis())
                        .build();
                tweetDao.add(tweet);
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
