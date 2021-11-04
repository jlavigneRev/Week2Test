package com.jameslavigne;

public class UserDaoFactory {
        private static UserDao dao = null;

        private UserDaoFactory() {
        }

        public static UserDao getUserDao() {
            if (dao == null)
                dao = new UserDaoImp();
            return dao;
        }
}
