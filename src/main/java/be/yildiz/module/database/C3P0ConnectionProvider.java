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
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * C3P0 implementation for a connection provider.
 *
 * @author Grégory Van den Borre
 */
public final class C3P0ConnectionProvider extends DataBaseConnectionProvider {

    /**
     * Time in seconds.
     */
    private static final int ONE_HOUR = 3600;

    /**
     * Time in seconds.
     */
    private static final int HALF_HOUR = 1800;

    /**
     * C3P0 data source.
     */
    private final ComboPooledDataSource cpds;

    /**
     * Build a C3P0 connection provider, set the default C3P0 logger silent, the max idle time is 1 hour, autocommit is set to true.
     *
     * @param system     Database system to use.
     * @param properties Properties holding connection data.
     * @throws SQLException         If an exception occurs when building the object.
     * @throws NullPointerException if a parameter is null.
     */
    public C3P0ConnectionProvider(final DatabaseSystem system, final DbProperties properties) throws SQLException {
        super(system, properties);
        Properties p = new Properties(System.getProperties());
        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", Logger.getLogLevel().name());
        System.setProperties(p);
        this.cpds = new ComboPooledDataSource();
        try {
            this.cpds.setDriverClass(system.getDriver());
        } catch (PropertyVetoException e) {
            throw new SQLException("Cannot load pool driver.", e);
        }
        this.cpds.setJdbcUrl(this.getUri());
        this.cpds.setUser(this.getLogin());
        this.cpds.setPassword(this.getPassword());
        this.cpds.setMaxIdleTime(ONE_HOUR);
        this.cpds.setMaxIdleTimeExcessConnections(HALF_HOUR);
        this.cpds.setAutoCommitOnClose(true);
    }

    @Override
    protected Connection getConnectionImpl() throws SQLException {
        return this.cpds.getConnection();
    }

    @Override
    public void close() throws Exception {
        this.cpds.close();
    }
}
