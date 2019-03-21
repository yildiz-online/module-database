/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.module.database.dummy;

import be.yildizgames.module.database.DataBaseConnectionProvider;
import be.yildizgames.module.database.DatabaseSystem;
import be.yildizgames.module.database.DbProperties;
import be.yildizgames.module.database.DriverProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class DummyDatabaseConnectionProvider extends DataBaseConnectionProvider {


    private final boolean throwError;

    public DummyDatabaseConnectionProvider(DatabaseSystem system, DbProperties properties, boolean throwError) {
        super(system, properties, false);
        this.throwError = throwError;
    }

    public static DataBaseConnectionProvider getDummyProvider(DriverProvider driverProvider) {
        return new DummyDatabaseConnectionProvider(new DummySystem(driverProvider), new DefaultProperties(), false);
    }

    @Override
    protected Connection getConnectionImpl() throws SQLException {
        if (this.throwError) {
            throw new SQLException("error");
        }
        return new DummyConnection();
    }

    @Override
    public void close() {

    }

    public final static class DefaultProperties implements DbProperties {

        @Override
        public String getDbUser() {
            return "myUser";
        }

        @Override
        public int getDbPort() {
            return 7894;
        }

        @Override
        public String getDbPassword() {
            return "myPwd";
        }

        @Override
        public String getDbRootUser() {
            return "myUser";
        }

        @Override
        public String getDbRootPassword() {
            return "myPwd";
        }

        @Override
        public String getDbHost() {
            return "localhost";
        }

        @Override
        public String getDbName() {
            return "myDatabase";
        }

        @Override
        public String getSystem() {
            return "h2";
        }
    }
}

