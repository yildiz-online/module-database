/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */
package be.yildizgames.module.database;

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.exception.technical.TechnicalException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
public class DatabaseConnectionProviderFactoryTest {

    @Nested
    public class Create {

        /*@Test
        void mysql() throws SQLException {
            DbProperties properties = givenADbProperties("mysql");
            DataBaseConnectionProvider p = DatabaseConnectionProviderFactory.getInstance().create(properties);
            assertEquals(DataBaseConnectionProvider.DBSystem.MYSQL, p.getSystem());
        }

        @Test
        void derby() throws SQLException {
            DbProperties properties = givenADbProperties("derby-file");
            DataBaseConnectionProvider p = DatabaseConnectionProviderFactory.getInstance().create(properties);
            assertEquals(DataBaseConnectionProvider.DBSystem.DERBY, p.getSystem());
        }

        @Test
        void derbyInMemory() throws SQLException {
            DbProperties properties = givenADbProperties("derby-memory");
            DataBaseConnectionProvider p = DatabaseConnectionProviderFactory.getInstance().create(properties);
            assertEquals(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, p.getSystem());
        }

        @Test
        void postgres() throws SQLException {
            DbProperties properties = givenADbProperties("postgres");
            DataBaseConnectionProvider p = DatabaseConnectionProviderFactory.getInstance().create(properties);
            assertEquals(DataBaseConnectionProvider.DBSystem.POSTGRES, p.getSystem());
        }*/

        @Test
        public void withNull() {
            assertThrows(ImplementationException.class, () -> DatabaseConnectionProviderFactory.getInstance().create(null));
        }

        @Test
        public void unknown() {
            DbProperties properties = givenADbProperties("unknown");
            assertThrows(TechnicalException.class, () -> DatabaseConnectionProviderFactory.getInstance().create(properties));
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

            @Override
            public String getDbRootUser() {
                return getDbUser();
            }

            @Override
            public String getDbRootPassword() {
                return getDbPassword();
            }
        };
    }
}
