/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.database;

import com.mysql.cj.jdbc.Driver;
import org.jooq.SQLDialect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class DatabaseConnectionProviderTest {

    public static class Constructor {

        @Test
        public void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            Assert.assertFalse(dcp.isDebug());
            Assert.assertEquals(properties.getDbUser(), dcp.getLogin());
            Assert.assertEquals(properties.getDbPassword(), dcp.getPassword());
        }

        @Test(expected = AssertionError.class)
        public void withNoLogin() throws SQLException {
            DbProperties properties = new DbProperties() {
                @Override
                public String getDbUser() {
                    return null;
                }

                @Override
                public int getDbPort() {
                    return 0;
                }

                @Override
                public String getDbPassword() {
                    return "ok";
                }

                @Override
                public String getDbHost() {
                    return "ok";
                }

                @Override
                public String getDbName() {
                    return "ok";
                }
            };
            new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
        }

        @Test(expected = AssertionError.class)
        public void withNoPassword() throws SQLException {
            DbProperties properties = new DbProperties() {
                @Override
                public String getDbUser() {
                    return "ok";
                }

                @Override
                public int getDbPort() {
                    return 0;
                }

                @Override
                public String getDbPassword() {
                    return null;
                }

                @Override
                public String getDbHost() {
                    return "ok";
                }

                @Override
                public String getDbName() {
                    return "ok";
                }
            };
            new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
        }

        @Test(expected = AssertionError.class)
        public void withNoURI() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();

            DatabaseSystem withoutUri = new DatabaseSystem() {
                @Override
                public SQLDialect getDialect() {
                    return SQLDialect.MYSQL;
                }

                @Override
                public String getDriver() {
                    return "com.mysql.cj.jdbc.Driver";
                }

                @Override
                public DriverProvider getDriverProvider() {
                    return Driver::new;
                }

                @Override
                public String getUrl(DbProperties p) {
                    return null;
                }
            };

            new DummyDatabaseConnectionProvider(withoutUri, properties, false);
        }


        @Test
        public void withMysql() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            Assert.assertEquals(SQLDialect.MYSQL, dcp.getDialect());
            Assert.assertEquals(DataBaseConnectionProvider.DBSystem.MYSQL, dcp.getSystem());
            Assert.assertEquals("jdbc:mysql://" + properties.getDbHost() + ":" + properties.getDbPort() + "/" + properties.getDbName() + "?zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone="+ Calendar.getInstance().getTimeZone().getID(), dcp.getUri());
        }

        @Test
        public void withDerby() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY, properties, false);

            Assert.assertEquals(SQLDialect.DERBY, dcp.getDialect());
            Assert.assertEquals(DataBaseConnectionProvider.DBSystem.DERBY, dcp.getSystem());
            Assert.assertEquals("jdbc:derby:target/database/" + properties.getDbName() + ";", dcp.getUri());
        }

        @Test(expected = AssertionError.class)
        public void withNull() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            new DummyDatabaseConnectionProvider(null, properties, false);
        }

        @Test(expected = AssertionError.class)
        public void withNullProperties() throws SQLException {
            new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY, null, false);
        }
    }

    public static class GetConnection  {

        @Test
        public void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            Assert.assertNotNull(dcp.getConnection());
        }

        @Test
        public void withDebugMode() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            dcp.setDebugMode();
            Assert.assertNotNull(dcp.getConnection());
        }

        @Test(expected = SQLException.class)
        public void withError() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, true).getConnection();
        }
    }

    public static class GetDriver {

        @Test
        public void mysql() {
            Assert.assertEquals("com.mysql.cj.jdbc.Driver", DataBaseConnectionProvider.DBSystem.MYSQL.getDriver());
        }

        @Test
        public void derby() {
            Assert.assertEquals("org.apache.derby.jdbc.EmbeddedDriver", DataBaseConnectionProvider.DBSystem.DERBY.getDriver());
        }
    }

    public static class Sanity {

        @Test
        public void happyFlow() throws SQLException{
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            dcp.sanity();
        }

        @Test(expected = SQLException.class)
        public void withError() throws SQLException{
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, true);
            dcp.sanity();
        }
    }

}
