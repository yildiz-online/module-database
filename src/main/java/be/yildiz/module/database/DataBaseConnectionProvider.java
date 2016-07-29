//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.database;

import be.yildiz.common.log.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jdbcdslog.ConnectionLoggingProxy;
import org.jooq.SQLDialect;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provide connections to the database system.
 *
 * @author Grégory Van den Borre
 */
@Getter(value = AccessLevel.PROTECTED)
public abstract class DataBaseConnectionProvider {

    /**
     * Selected Database system.
     */
    private final DBSystem system;
    /**
     * Connection URI.
     */
    private final String uri;
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
     * @throws NullPointerException if a parameter is null.
     * @throws SQLException         If the given DB system is not managed.
     * @Ensures (this.system == system)
     * @Ensures (this.system != null)
     * @Ensures (this.login == properties.dbUser)
     * @Ensures (this.password == properties.dbPassword)
     */
    protected DataBaseConnectionProvider(@NonNull final DBSystem system, @NonNull final DbProperties properties) throws SQLException {
        String host = properties.getDbHost();
        int port = properties.getDbPort();
        String database = properties.getDbName();
        this.system = system;
        this.login = properties.getDbUser();
        this.password = properties.getDbPassword();
        switch (this.system) {
            case MYSQL:
                this.uri = "jdbc:mysql://" + host + ":" + port + "/" + database + "?zeroDateTimeBehavior=convertToNull&useSSL=false";
                break;
            case DERBY:
                this.uri = "jdbc:derby:target/database/" + database + ";";
                break;
            default:
                throw new SQLException("Unknown system: " + system);
        }
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
        try {
            Logger.info("Checking database connection...");
            this.getConnection();
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
        try {
            Connection c = this.getConnectionImpl();
            if (this.debug) {
                c = ConnectionLoggingProxy.wrap(c);
            }
            return c;
        } catch (SQLException e) {
            throw e;
        }
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

    /**
     * Possible RDBMS.
     *
     * @author Van den Borre Grégory
     */
    public enum DBSystem {

        /**
         * MySQL system.
         */
        MYSQL(SQLDialect.MYSQL, "com.mysql.jdbc.Driver"),

        /**
         * Derby 10 system.
         */
        DERBY(SQLDialect.DERBY, "org.apache.derby.jdbc.EmbeddedDriver");

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

        /**
         * Build a new DBSystem.
         * @param dialect JOOQ dialect to use.
         * @param driver Driver to load.
         */
        DBSystem(final SQLDialect dialect, final String driver) {
            this.dialect = dialect;
            this.driver = driver;
        }
    }
}
