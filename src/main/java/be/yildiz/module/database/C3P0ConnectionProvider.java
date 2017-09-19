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

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.derby.jdbc.Driver42;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Properties;

/**
 * C3P0 implementation for a connection provider.
 *
 * @author Grégory Van den Borre
 */
public final class C3P0ConnectionProvider extends DataBaseConnectionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(C3P0ConnectionProvider.class);

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

    private boolean open = false;

    /**
     * Build a C3P0 connection provider, set the default C3P0 logger silent, the max idle time is 1 hour, autocommit is set to true.
     *
     * @param system     Database system to use.
     * @param properties Properties holding connection data.
     * @throws SQLException         If an exception occurs when building the object.
     * @throws NullPointerException if a parameter is null.
     */
    public C3P0ConnectionProvider(final DatabaseSystem system, final DbProperties properties, boolean root) throws SQLException {
        super(system, properties, root);
        System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "WARNING");
        System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        this.cpds = new ComboPooledDataSource();
        try {
            this.cpds.setDriverClass(system.getDriver());
        } catch (PropertyVetoException e) {
            LOGGER.error("Error in pool", e);
            throw new SQLException("Cannot load pool driver.", e);
        }
        this.cpds.setJdbcUrl(this.getUri());
        this.cpds.setUser(this.getLogin());
        this.cpds.setPassword(this.getPassword());
        this.cpds.setMaxIdleTime(ONE_HOUR);
        this.cpds.setMaxIdleTimeExcessConnections(HALF_HOUR);
        this.cpds.setAutoCommitOnClose(true);
    }

    public C3P0ConnectionProvider(final DatabaseSystem system, final DbProperties properties) throws SQLException {
        this(system, properties, false);
    }

    @Override
    protected Connection getConnectionImpl() throws SQLException {
        if(!open && this.getSystem() == DBSystem.DERBY_IN_MEMORY) {
            LOGGER.debug("Create derby connection: " + this.getUri() + "create=true;");
            this.cpds.setMinPoolSize(1);
            this.cpds.setMaxPoolSize(1);
            this.cpds.setInitialPoolSize(1);
            this.cpds.setJdbcUrl(this.getUri() + "create=true;");
            Connection c = this.cpds.getConnection();
            this.cpds.setJdbcUrl(this.getUri());
            this.open = true;
            this.cpds.setMaxPoolSize(15);
            return c;
        }
        return this.cpds.getConnection();
    }

    @Override
    public void close() throws Exception {
        if(open) {
            if (this.getSystem() == DBSystem.DERBY_IN_MEMORY) {
                try {
                Driver42.activeDriver().connect(this.getUri() + "drop=true;", new Properties());
                } catch (SQLNonTransientConnectionException e) {
                    //Expected exception when closing in memory derby.
                }
            }
            this.cpds.close();
            this.open = false;
            LOGGER.info("Closed database connection pool.");
        }
    }
}
