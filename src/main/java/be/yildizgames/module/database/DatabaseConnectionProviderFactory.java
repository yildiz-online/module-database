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

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.exception.technical.InitializationException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Parse properties to extract the required connection provider.
 * @author Grégory Van den Borre
 */
public class DatabaseConnectionProviderFactory {

    private static final DatabaseConnectionProviderFactory INSTANCE = new DatabaseConnectionProviderFactory();

    private final Map<String, DatabaseSystem> systems = new HashMap<>();

    private DatabaseConnectionProviderFactory() {
        super();
    }

    public static DatabaseConnectionProviderFactory getInstance() {
        return INSTANCE;
    }

    public final void addSystem(String key, DatabaseSystem system) {
        this.systems.put(key, system);
    }

    public final DataBaseConnectionProvider create(DbProperties properties) throws SQLException {
        ImplementationException.throwForNull(properties);
        DatabaseSystem system = Optional
                .ofNullable(this.systems.get(properties.getSystem()))
                .orElseThrow(() -> new InitializationException(properties.getSystem()));
        if(system.requirePool()) {
            return new C3P0ConnectionProvider(system, properties);
        } else {
            return new SimpleConnectionProvider(system, properties);
        }
    }

    public final DataBaseConnectionProvider createWithHighPrivilege(DbProperties properties) throws SQLException {
        ImplementationException.throwForNull(properties);
        DatabaseSystem system = Optional
                .ofNullable(this.systems.get(properties.getSystem()))
                .orElseThrow(() -> new InitializationException(properties.getSystem()));

        if(system.requirePool()) {
            return new C3P0ConnectionProvider(system, properties, true);
        } else {
            return new SimpleConnectionProvider(system, properties, true);
        }
    }
}
