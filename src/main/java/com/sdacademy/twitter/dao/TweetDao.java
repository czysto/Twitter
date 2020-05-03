package com.sdacademy.twitter.dao;

import com.sdacademy.twitter.model.Tweet;

/**
 * Class responsible for managing tweets
 */
public final class TweetDao extends AbstractDao<Tweet> {
    private static TweetDao __instance;

    private TweetDao() {
        super();
    }

    /**
     * Method returns instance of Tweet DAO
     *
     * @return the instance
     */
    public static TweetDao getInstance() {
        if (__instance == null) {
            __instance = new TweetDao();
        }
        return __instance;
    }

    @Override
    protected Class<Tweet> getClazz() {
        return Tweet.class;
    }
}