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

import be.yildiz.common.log.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class C3P0ConnectionProviderTest {

    public static class Constructor {

        @Test
        public void happyFlow() throws Exception {
            Logger.disable();
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            try(DataBaseConnectionProvider p = new C3P0ConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, properties)) {
            }
        }

        @Test(expected = AssertionError.class)
        public void withNullSystem() throws SQLException {
            Logger.disable();
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            new C3P0ConnectionProvider(null, properties);
        }

        @Test(expected = AssertionError.class)
        public void withNullProperties() throws SQLException {
            Logger.disable();
            new C3P0ConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, null);
        }
    }

    public static class GetConnection {

        @Test
        public void happyFlow() throws Exception {
            Logger.disable();
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            try(DataBaseConnectionProvider p = new C3P0ConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, properties)) {
                Assert.assertNotNull(p.getConnection());
            }
        }
    }

    public static class Close {

        @Test
        public void happyFlow() throws Exception {
            Logger.disable();
            DbProperties properties = new DummyDatabaseConnectionProvider.DefaultProperties();
            DataBaseConnectionProvider p = new C3P0ConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, properties);
            p.getConnection();
            p.close();
        }
    }
}
