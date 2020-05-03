package com.sdacademy.twitter.dao;

import com.sdacademy.twitter.model.User;
import com.sdacademy.twitter.utils.HibernateUtils;
import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * User DAO, connection to the DB
 */
public final class UserDao extends AbstractDao<User> {
    private static UserDao __instance;

    public static final String USER_SESSION = "mysessioncookie";

    private UserDao() {
        super();

    }

    /**
     * Method returns instance of the User DAO
     *
     * @return the instance of the User DAO
     */
    public static UserDao getInstance() {
        if (__instance == null) {
            __instance = new UserDao();
        }
        return __instance;
    }

    /**
     * Method validate user by Email address and password.
     *
     * @param email    the user email address
     * @param password the user password
     * @return enity if user exists, EMPTY otherwise
     */
    public Optional<User> checkUserByEmailAndPassword(final @NonNull String email, final @NonNull String password) {
        Session session = HibernateUtils.getHibernateSession();
        try {
            String hql = "from User u where upper(u.email) = upper(:email) and u.password = :password";
            List<User> users = session.createQuery(hql)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .list();

            if (users.size() == 1) {
                return Optional.of(users.get(0));
            }
            return Optional.empty();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }
}
