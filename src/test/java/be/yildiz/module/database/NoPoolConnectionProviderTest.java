package be.yildiz.module.database;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class NoPoolConnectionProviderTest {

    public static class Constructor {

        @Test
        public void happyFlow() throws SQLException {
            new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, new TestingDatabaseInit.TestingDbProperties());
        }

        @Test(expected = IllegalArgumentException.class)
        public void withSystemNull() throws SQLException {
            new NoPoolConnectionProvider(null, new TestingDatabaseInit.TestingDbProperties());
        }

        @Test(expected = IllegalArgumentException.class)
        public void withPropertiesNull() throws SQLException {
            new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, null);
        }
    }

    public static class GetConnection {

        @Test
        public void happyFlow() throws SQLException {
            DataBaseConnectionProvider c = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDatabaseInit.TestingDbProperties());
            Assert.assertNotNull(c.getConnection());
        }
    }

    public static class Close {

        @Test
        public void happyFlow() throws Exception {
            DataBaseConnectionProvider c = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDatabaseInit.TestingDbProperties());
            c.getConnection();
            c.close();
        }

        @Test(expected = SQLException.class)
        public void withNoConnection() throws Exception {
            DataBaseConnectionProvider c = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDatabaseInit.TestingDbProperties());
            c.close();
        }
    }
}
