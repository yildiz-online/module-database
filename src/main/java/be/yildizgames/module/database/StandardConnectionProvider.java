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
package be.yildizgames.module.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

class StandardConnectionProvider extends DataBaseConnectionProvider {

    private final Logger logger = LoggerFactory.getLogger(StandardConnectionProvider.class);

    private final Properties properties;

    StandardConnectionProvider(DatabaseSystem system, DbProperties properties) {
        this(system, properties, false);
    }

    StandardConnectionProvider(DatabaseSystem system, DbProperties properties, boolean root) {
        super(system, properties, root);
        this.logger.info("Using no database connection pool.");
        this.properties = new Properties();
        this.properties.put("user", this.getLogin());
        this.properties.put("password", this.getPassword());
    }

    @Override
    protected Connection getConnectionImpl() throws SQLException {
        return this.getSystem().getDriverProvider().getDriver().connect(this.getUri(), this.properties);
    }

    @Override
    public void close() throws Exception {

    }
}
