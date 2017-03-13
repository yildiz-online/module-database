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


import be.yildiz.common.exeption.UnhandledSwitchCaseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class DatabaseConnectionProviderFactoryTest {

    public static class Create {

        @Test
        public void mysql() throws SQLException {
            DbProperties properties = givenADbProperties("mysql");
            DataBaseConnectionProvider p = new DatabaseConnectionProviderFactory().create(properties);
            Assert.assertEquals(DataBaseConnectionProvider.DBSystem.MYSQL, p.getSystem());
        }

        @Test
        public void derby() throws SQLException {
            DbProperties properties = givenADbProperties("derby-file");
            DataBaseConnectionProvider p = new DatabaseConnectionProviderFactory().create(properties);
            Assert.assertEquals(DataBaseConnectionProvider.DBSystem.DERBY, p.getSystem());
        }

        @Test
        public void derbyInMemory() throws SQLException {
            DbProperties properties = givenADbProperties("derby-memory");
            DataBaseConnectionProvider p = new DatabaseConnectionProviderFactory().create(properties);
            Assert.assertEquals(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, p.getSystem());
        }

        @Test(expected = AssertionError.class)
        public void withNull() throws SQLException {
            new DatabaseConnectionProviderFactory().create(null);
        }

        @Test(expected = UnhandledSwitchCaseException.class)
        public void unknown() throws SQLException {
            DbProperties properties = givenADbProperties("unknown");
            DataBaseConnectionProvider p = new DatabaseConnectionProviderFactory().create(properties);
        }
    }

    private static DbProperties givenADbProperties(String system) {
        return new DbProperties() {
            @Override
            public String getDbUser() {
                return "user";
            }

            @Override
            public int getDbPort() {
                return 0;
            }

            @Override
            public String getDbPassword() {
                return "pass";
            }

            @Override
            public String getDbHost() {
                return "localhost";
            }

            @Override
            public String getDbName() {
                return "database";
            }

            @Override
            public String getSystem() {
                return system;
            }
        };
    }
}