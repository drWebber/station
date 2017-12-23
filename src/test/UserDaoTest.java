package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import station.dao.mysql.user.UserDaoImpl;
import station.domain.user.Role;
import station.domain.user.User;
import station.exception.DaoException;
import test.DataSource.TestConnector;

public class UserDaoTest {
    @Test
    public void userDaoImplTest() {
        Connection connection;
        try {
            connection = TestConnector.getConnection();
            UserDaoImpl userDao = new UserDaoImpl(connection);
            User user = new User();
            user.setLogin("testLogin1"); /* UQ */
            user.setName("vasya");
            user.setPassword("123");
            user.setPatronymic("Petrovich");
            user.setRole(Role.SUBSCRIBER);
            user.setSurname("Petr");
            Long id = userDao.create(user);
            user.setId(id); /* присвоить id после создания! */
            
            User resultUser = userDao.read(id);
            Assert.assertEquals(user, resultUser);
            
            user.setSurname("Иванович");
            userDao.update(user);
            resultUser = userDao.read(id);
            Assert.assertEquals(user, resultUser);
            
            userDao.delete(id);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
