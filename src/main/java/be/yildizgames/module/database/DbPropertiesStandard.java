/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

import java.util.Objects;
import java.util.Properties;

/**
 * Basic implementation for the DbProperties.
 *
 * @author Grégory Van den Borre
 */
public class DbPropertiesStandard implements DbProperties {

    /**
     * Database login.
     */
    private final String user;

    /**
     * Database password.
     */
    private final String password;

    /**
     * Name of the database.
     */
    private final String database;

    /**
     * Database address.
     */
    private final String host;

    /**
     * Database port.
     */
    private final int port;

    private final String system;

    private final String rootUser;

    private final String rootPassword;

    /**
     * Build a DbProperties from a property object.
     * Expected content is:
     * <ul>
     *     <li>database.user</li>
     *     <li>database.password</li>
     *     <li>database.root.user</li>
     *     <li>database.root.password</li>
     *     <li>database.name</li>
     *     <li>database.host</li>
     *     <li>database.port</li>
     *     <li>database.system</li>
     * </ul>
     * @param properties Properties object.
     * @throws NullPointerException If any parameter is null.
     * @throws IllegalArgumentException If the port is not between 0 and 65635
     */
    public DbPropertiesStandard(final Properties properties) {
        super();
        Objects.requireNonNull(properties);
        this.user = properties.getProperty("database.user");
        this.password = properties.getProperty("database.password");
        this.rootUser = properties.getProperty("database.root.user");
        this.rootPassword = properties.getProperty("database.root.password");
        this.database = properties.getProperty("database.name");
        this.host = properties.getProperty("database.host");
        this.port = Integer.parseInt(properties.getProperty("database.port"));
        this.system = properties.getProperty("database.system");
        DbPropertiesInvariant.check(this.user, this.password, this.rootUser, this.rootPassword, this.database, this.host, this.port, this.system);
    }

    public static DbProperties fromProperties(final Properties properties) {
        return new DbPropertiesStandard(properties);
    }

    @Override
    public String getDbUser() {
        return this.user;
    }

    @Override
    public int getDbPort() {
        return this.port;
    }

    @Override
    public String getDbPassword() {
        return this.password;
    }

    @Override
    public String getDbHost() {
        return this.host;
    }

    @Override
    public String getDbName() {
        return this.database;
    }

    @Override
    public String getSystem() {
        return this.system;
    }

    @Override
    public String getDbRootUser() {
        return this.rootUser;
    }

    @Override
    public String getDbRootPassword() {
        return this.rootPassword;
    }
}
