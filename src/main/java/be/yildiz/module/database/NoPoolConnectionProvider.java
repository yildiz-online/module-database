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

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Properties;

/**
 * @author Grégory Van den Borre
 */
public class NoPoolConnectionProvider extends DataBaseConnectionProvider {

    private final Driver driver;

    private Connection connection;

    /**
     * Create a new Database connection provider.
     *
     * @param system     Database system to use.
     * @param properties Properties holding connection data.
     * @throws NullPointerException if a parameter is null.
     * @throws SQLException         If the given DB system is not managed.
     */
    public NoPoolConnectionProvider(DatabaseSystem system, DbProperties properties) throws SQLException {
        super(system, properties);
        try {
            Class.forName(system.getDriver());
            this.driver = system.getDriverProvider().getDriver();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found: " + system.getDriver(), e);
        }

    }

    @Override
    protected Connection getConnectionImpl() throws SQLException {
        if(this.connection == null || this.connection.isClosed()) {
            this.connection = this.driver.connect(this.getUri(), new Properties());
        }
        return this.connection;
    }

    @Override
    public void close() throws Exception {
        if(this.getSystem() == DBSystem.DERBY_IN_MEMORY) {
            try {
                this.driver.connect(this.getUri().replace("create", "drop"), new Properties());
            } catch (SQLNonTransientConnectionException e) {
                //Expected exception when closing in memory derby.
            }
        } else {
            this.connection.close();
        }
    }
}
