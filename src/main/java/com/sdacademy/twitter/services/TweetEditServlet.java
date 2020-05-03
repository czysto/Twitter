package com.sdacademy.twitter.services;

import com.sdacademy.twitter.dao.TweetDao;
import com.sdacademy.twitter.model.Tweet;
import com.sdacademy.twitter.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet responsible for editing tweet
 */
@WebServlet(name = "editTweetServlet", value = "/editTweet")
public class TweetEditServlet extends HttpServlet {
    TweetDao tweetDao;

    public TweetEditServlet() {
        tweetDao = TweetDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String message = req.getParameter("message");
        final Long id = Long.valueOf(req.getParameter("tweetId"));
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        Optional<Tweet> tweetFromDb = tweetDao.get(id);
        if (tweetFromDb.isPresent()) {
            final Tweet tweet = tweetFromDb.get();
            if (userId.isPresent()) {
                if (userId.get().equals(tweet.getUser().getId())) {
                    tweet.setMessage(message);
                    tweetDao.update(tweet);
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
