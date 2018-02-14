/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

import be.yildizgames.common.logging.LogFactory;
import org.jdbcdslog.ConnectionLoggingProxy;
import org.jooq.SQLDialect;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provide connections to the database system.
 *
 * @author Grégory Van den Borre
 */
public abstract class DataBaseConnectionProvider implements AutoCloseable {

    private static final Logger LOGGER = LogFactory.getInstance().getLogger(DataBaseConnectionProvider.class);

    /**
     * Selected Database system.
     */
    private final DatabaseSystem system;
    /**
     * Connection URI.
     */
    private String uri;
    /**
     * Database connection login.
     */
    private final String login;
    /**
     * Data base connection password.
     */
    private final String password;

    /**
     * <code>true</code> will log every sql request.
     */
    private boolean debug;

    /**
     * Create a new Database connection provider.
     *
     * @param system Database system to use.
     * @param properties Properties holding connection data.
     * @param root Flag to check if the connection is root or not.
     * @throws AssertionError if a parameter is null or invalid.
     */
    //@Ensures ("this.system == system")
    //@Ensures ("this.system != null")
    //@Ensures ("this.login == properties.dbUser")
    //@Ensures ("this.password == properties.dbPassword")
    protected DataBaseConnectionProvider(final DatabaseSystem system, final DbProperties properties, boolean root) {
        if (properties == null) {
            throw new AssertionError("Properties cannot be null.");
        }
        if (system == null) {
            throw new AssertionError("system cannot be null.");
        }
        Properties p = new Properties(System.getProperties());
        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "info");
        p.put("org.jooq.no-logo", "true");
        System.setProperties(p);
        this.login = root ? properties.getDbRootUser() : properties.getDbUser();
        this.password = root ? properties.getDbRootPassword() : properties.getDbPassword();
        this.uri = system.getUrl(properties);
        this.system = system;
        assert this.invariant();
    }

    /**
     * Activate debug mode to log all queries.
     */
    public final void setDebugMode() {
        this.debug = true;
    }

    /**
     * Simple check on database.
     *
     * @throws SQLException thrown if connection failed.
     */
    public final void sanity() throws SQLException {
        LOGGER.info("Checking database connection...");
        try (Connection c = this.getConnection()) {
            LOGGER.info("Checking database connection successful.");
        } catch (SQLException e) {
            LOGGER.error("Database connection failed.");
            throw e;
        }
    }

    /**
     * Retrieve a connection to the data base. Do not forget to release it with
     * Connection.close()
     *
     * @return The created database connection.
     * @throws SQLException In case the connection retrieving throws it.
     */
    public final Connection getConnection() throws SQLException {
        Connection c = this.getConnectionImpl();
        if (this.debug) {
            c = ConnectionLoggingProxy.wrap(c);
        }
        return c;
    }

    /**
     * @return The dialect for this system.
     */
    public final SQLDialect getDialect() {
        return this.system.getDialect();
    }

    public final String getDriver() {
        return this.system.getDriver();
    }

    public final String getUri() {
        return uri;
    }

    protected final DatabaseSystem getSystem() {
        return system;
    }

    protected final String getLogin() {
        return login;
    }

    protected final String getPassword() {
        return password;
    }

    protected final boolean isDebug() {
        return debug;
    }

    /**
     * @return A database connection from the underlying implementation.
     * @throws SQLException When connection cannot be retrieved.
     */
    protected abstract Connection getConnectionImpl() throws SQLException;

    private boolean invariant() {
        if (this.login == null) {
            LOGGER.error("login cannot be null.");
            return false;
        }
        if (this.password == null) {
            LOGGER.error("password cannot be null.");
            return false;
        }
        if (this.uri == null) {
            LOGGER.error("uri cannot be null.");
            return false;
        }
        return true;
    }
}
