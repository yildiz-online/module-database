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
import be.yildiz.common.util.StringUtil;
import com.mysql.cj.jdbc.Driver;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.jdbcdslog.ConnectionLoggingProxy;
import org.jooq.SQLDialect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

/**
 * Provide connections to the database system.
 *
 * @author Grégory Van den Borre
 */
@Getter(value = AccessLevel.PROTECTED)
public abstract class DataBaseConnectionProvider implements AutoCloseable {

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
     * @param system     Database system to use.
     * @param properties Properties holding connection data.
     * @throws AssertionError if a parameter is null or invalid.
     */
    //@Ensures ("this.system == system")
    //@Ensures ("this.system != null")
    //@Ensures ("this.login == properties.dbUser")
    //@Ensures ("this.password == properties.dbPassword")
    protected DataBaseConnectionProvider(final DatabaseSystem system, final DbProperties properties) {
        if(properties == null) {
            throw new AssertionError("Properties cannot be null.");
        }
        if(system == null) {
            throw new AssertionError("system cannot be null.");
        }
        Properties p = new Properties(System.getProperties());
        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", Logger.getLogLevel().name());
        p.put("org.jooq.no-logo", "true");
        System.setProperties(p);
        this.system = system;
        this.login = properties.getDbUser();
        this.password = properties.getDbPassword();
        this.uri = system.getUrl(properties);
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
        Logger.info("Checking database connection...");
        try (Connection c = this.getConnection()){
            Logger.info("Checking database connection successful.");
        } catch (SQLException e) {
            Logger.error("Database connection failed.");
            throw e;
        }
    }

    /**
     * Retrieve a connection to the data base. Do not forget to release it with Connection.close()
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
     * @return A database connection from the underlying implementation.
     * @throws SQLException When connection cannot be retrieved.
     */
    protected abstract Connection getConnectionImpl() throws SQLException;

    /**
     * @return The dialect for this system.
     */
    public SQLDialect getDialect() {
        return this.system.getDialect();
    }

    private boolean invariant() {
        if(this.login == null) {
            Logger.error("login cannot be null.");
            return false;
        }
        if(this.password == null) {
            Logger.error("password cannot be null.");
            return false;
        }
        if(this.uri == null) {
            Logger.error("uri cannot be null.");
            return false;
        }
        return true;
    }

    /**
     * Possible RDBMS.
     *
     * @author Van den Borre Grégory
     */
    public enum DBSystem implements DatabaseSystem {

        /**
         * MySQL system.
         */
        MYSQL(
                SQLDialect.MYSQL,
                "com.mysql.cj.jdbc.Driver",
                Driver::new,
                "jdbc:mysql://${1}:${2}/${0}?zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone="+Calendar.getInstance().getTimeZone().getID()),

        /**
         * Derby 10 system.
         */
        DERBY(
                SQLDialect.DERBY,
                "org.apache.derby.jdbc.EmbeddedDriver",
                EmbeddedDriver::new,
                "jdbc:derby:target/database/${0};"),

        /**
         * Derby 10 system, only in memory.
         */
        DERBY_IN_MEMORY(
                SQLDialect.DERBY,
                "org.apache.derby.jdbc.EmbeddedDriver",
                EmbeddedDriver::new,
                "jdbc:derby:memory:${0};user=${3};");

        /**
         * Associated dialect.
         */
        @Getter
        private final SQLDialect dialect;

        /**
         * Associated driver.
         */
        @Getter
        private final String driver;

        @Getter
        private final DriverProvider driverProvider;

        private final String url;

        /**
         * Build a new DBSystem.
         *
         * @param dialect JOOQ dialect to use.
         * @param driver  Driver to load.
         */
        DBSystem(final SQLDialect dialect, final String driver, final DriverProvider driverProvider, String url) {
            this.dialect = dialect;
            this.driver = driver;
            this.driverProvider = driverProvider;
            this.url = url;
        }

        @Override
        public String getUrl(DbProperties p) {
            String[] params = {p.getDbName(), p.getDbHost(), String.valueOf(p.getDbPort()), p.getDbUser()};
            return StringUtil.fillVariable(this.url, params);
        }
    }
}
