package com.sdacademy.twitter.dao;

import com.sdacademy.twitter.model.Comment;

/**
 * Class responsible for managing comments
 */
public final class CommentDao extends AbstractDao<Comment> {
    private static CommentDao __instance;

    private CommentDao() {
        super();
    }

    /**
     * Method returns instance of Comment DAO
     *
     * @return the instance
     */
    public static CommentDao getInstance() {
        if (__instance == null) {
            __instance = new CommentDao();
        }
        return __instance;
    }

    @Override
    protected Class<Comment> getClazz() {
        return Comment.class;
    }
}