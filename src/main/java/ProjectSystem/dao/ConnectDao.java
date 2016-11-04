package ProjectSystem.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.*;

public class ConnectDao {

    public static SessionFactory sessionFactory = null;

    public static void ConnectDB() throws SQLException, ClassNotFoundException {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void closeConnect() throws SQLException {
        sessionFactory.close();
    }
}
