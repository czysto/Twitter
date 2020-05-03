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
 * Servlet responsible for removing tweets
 */
@WebServlet(name = "removeTweetServlet", value = "/removeTweet")
public class TweetRemoveServlet extends HttpServlet {
    private TweetDao tweetDao;

    public TweetRemoveServlet() {
        tweetDao = TweetDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");
        final Optional<Long> userId = Utils.getUserIdFromSession(req);

        if (id != null) {
            final Long lId = Long.valueOf(id);
            Optional<Tweet> tweet = tweetDao.get(lId);
            if (tweet.isPresent() && userId.isPresent()) {
                if (tweet.get().getUser().getId().equals(userId.get())) {
                    tweetDao.remove(tweet.get());
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
