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

        @Test
        public void afterClosed() throws SQLException {
            DataBaseConnectionProvider c = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDatabaseInit.TestingDbProperties());
            c.getConnection().close();
            c.getConnection();
            Assert.assertFalse(c.getConnection().isClosed());
        }
    }

    public static class Close {

        @Test
        public void happyFlowDerbyInMemory() throws Exception {
            DataBaseConnectionProvider c = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDatabaseInit.TestingDbProperties());
            c.getConnection();
            c.close();
        }

        @Test
        public void happyFlowDerby() throws Exception {
            DataBaseConnectionProvider c = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_CREATE, new TestingDatabaseInit.TestingDbProperties());
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
