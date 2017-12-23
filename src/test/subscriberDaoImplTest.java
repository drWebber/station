package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import station.dao.mysql.user.SubscriberDaoImpl;
import station.domain.user.Administrator;
import station.domain.user.Prefix;
import station.domain.user.Subscriber;
import station.exception.DaoException;
import test.DataSource.TestConnector;

public class subscriberDaoImplTest {
    @Test
    public void userDaoImplTest() {
        Connection connection;
        try {
            connection = TestConnector.getConnection();
            SubscriberDaoImpl subscriberDao = new SubscriberDaoImpl(connection);
            Subscriber source = new Subscriber();
            source.setId(25L);  /* 25L magic number - должен быть в users, 
                                 * но отсутствовать в subscribers */
            source.setPassportId("060489EM2");
            source.setBirthDay(new Date(0));
            source.setAddress("210033, ул. Берестеня 39, кв. 7");
            Prefix prefix = new Prefix();
            prefix.setId(212);
            source.setPrefix(prefix);
            source.setPhoneNum(240077); /* UQ (PREFIX+NUM)  */
            Administrator admin = new Administrator();
            admin.setId(14L);
            source.setAdministrator(admin);
            Long id = subscriberDao.create(source);
            source.setId(id); /* присвоить id после создания! */
            
            System.out.println(source);
            Subscriber result = subscriberDao.read(id);
            System.out.println(result);
            Assert.assertEquals(source, result);
            
            source.setPassportId("2KJJ34");
            subscriberDao.update(source);
            result = subscriberDao.read(id);
            Assert.assertEquals(source, result);
            
            subscriberDao.delete(id);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
