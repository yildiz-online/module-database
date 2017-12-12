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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grégory Van den Borre
 */
class DatabaseConnectionProviderTest {

    @Nested
    class Constructor {

       /* @Test
        void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            assertFalse(dcp.isDebug());
            assertEquals(properties.getDbUser(), dcp.getLogin());
            assertEquals(properties.getDbPassword(), dcp.getPassword());
        }*/

        @Test
        void withNoLogin() throws SQLException {
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

                @Override
                public String getSystem() {
                    return "derby-memory";
                }

                @Override
                public String getDbRootUser() {
                    return "ok";
                }

                @Override
                public String getDbRootPassword() {
                    return "ok";
                }
            };
            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false));
        }

        @Test
        void withNoPassword() throws SQLException {
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

                @Override
                public String getSystem() {
                    return "derby-memory";
                }

                @Override
                public String getDbRootUser() {
                    return "ok";
                }

                @Override
                public String getDbRootPassword() {
                    return "ok";
                }
            };
            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false));
        }

        @Test
        void withNoURI() throws SQLException {
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

            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(withoutUri, properties, false));
        }

        @Test
        void withMysql() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            assertEquals(SQLDialect.MYSQL, dcp.getDialect());
            assertEquals(DataBaseConnectionProvider.DBSystem.MYSQL, dcp.getSystem());
            assertEquals("jdbc:mysql://" + properties.getDbHost() + ":" + properties.getDbPort() + "/" + properties.getDbName() + "?zeroDateTimeBehavior=convertToNull&createDatabaseIfNotExist=true&nullNamePatternMatchesAll=true&useSSL=false&serverTimezone=" + Calendar.getInstance().getTimeZone().getID(), dcp.getUri());
        }

        @Test
        void withDerby() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY, properties, false);

            assertEquals(SQLDialect.DERBY, dcp.getDialect());
            assertEquals(DataBaseConnectionProvider.DBSystem.DERBY, dcp.getSystem());
            assertEquals("jdbc:derby:target/database/" + properties.getDbName() + ";", dcp.getUri());
        }

        @Test
        void withPostgres() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.POSTGRES, properties, false);

            assertEquals(SQLDialect.POSTGRES, dcp.getDialect());
            assertEquals(DataBaseConnectionProvider.DBSystem.POSTGRES, dcp.getSystem());
            assertEquals("jdbc:postgresql://" + properties.getDbHost() + ":" + properties.getDbPort()
                    + "/" + properties.getDbName(), dcp.getUri());
        }

        @Test
        void withNull() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(null, properties, false));
        }

        @Test
        void withNullProperties() throws SQLException {
            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY, null, false));
        }
    }

    class GetConnection {

        @Test
        void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            assertNotNull(dcp.getConnection());
        }

        @Test
        void withDebugMode() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            dcp.setDebugMode();
            assertNotNull(dcp.getConnection());
        }

        @Test
        void withError() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            assertThrows(SQLException.class, () -> new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, true).getConnection());
        }
    }

    @Nested
    class GetDriver {

        @Test
        void mysql() {
            assertEquals("com.mysql.cj.jdbc.Driver", DataBaseConnectionProvider.DBSystem.MYSQL.getDriver());
        }

        @Test
        void derby() {
            assertEquals("org.apache.derby.jdbc.EmbeddedDriver", DataBaseConnectionProvider.DBSystem.DERBY.getDriver());
        }

        @Test
        void postgres() {
            assertEquals("org.postgresql.Driver", DataBaseConnectionProvider.DBSystem.POSTGRES.getDriver());
        }
    }

    @Nested
    class Sanity {

        @Test
        void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, false);
            dcp.sanity();
        }

        @Test
        void withError() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(DataBaseConnectionProvider.DBSystem.MYSQL, properties, true);
            assertThrows(SQLException.class, dcp::sanity);
        }
    }

}
