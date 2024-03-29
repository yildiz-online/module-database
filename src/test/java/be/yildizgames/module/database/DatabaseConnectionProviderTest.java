/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */
package be.yildizgames.module.database;

import be.yildizgames.module.database.dummy.DummyDatabaseConnectionProvider;
import be.yildizgames.module.database.dummy.DummyDriver;
import be.yildizgames.module.database.dummy.DummySystem;
import org.h2.Driver;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        void withNoLogin() {
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
            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, false));
        }

        @Test
        void withNoPassword() {
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
            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, false));
        }

        @Test
        void withNoURI() {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();

            DatabaseSystem withoutUri = new DatabaseSystem() {

                @Override
                public String getDriver() {
                    return "com.mysql.cj.jdbc.Driver";
                }

                @Override
                public DriverProvider driverProvider() {
                    return DummyDriver::new;
                }

                @Override
                public String getUrl(DbProperties p) {
                    return null;
                }

                @Override
                public boolean requirePool() {
                    return false;
                }
            };

            assertThrows(AssertionError.class, () -> new DummyDatabaseConnectionProvider(withoutUri, properties, false));
        }

        @Test
        void withNull() {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            assertThrows(NullPointerException.class, () -> new DummyDatabaseConnectionProvider(null, properties, false));
        }

        @Test
        void withNullProperties() {
            assertThrows(NullPointerException.class, () -> new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), null, false));
        }
    }

    @Nested
    class GetConnection {

        @Test
        void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, false);
            assertNotNull(dcp.getConnection());
        }

        @Test
        void withDebugMode() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, false);
            dcp.setDebugMode();
            assertNotNull(dcp.getConnection());
        }

        @Test
        void withError() {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            assertThrows(SQLException.class, () -> new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, true).getConnection());
        }
    }

    @Nested
    class GetDriver {

        @Test
        void happyFlow() {
            assertEquals("org.h2.Driver", new DummySystem(Driver::new).getDriver());
        }
    }

    @Nested
    class Sanity {

        @Test
        void happyFlow() throws SQLException {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, false);
            dcp.sanity();
        }

        @Test
        void withError() {
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider dcp = new DummyDatabaseConnectionProvider(new DummySystem(Driver::new), properties, true);
            assertThrows(SQLException.class, dcp::sanity);
        }
    }
}

