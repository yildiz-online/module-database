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

import be.yildiz.common.exeption.UnhandledSwitchCaseException;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class DatabaseConnectionProviderFactory {

    public static DataBaseConnectionProvider create(DbProperties properties) throws SQLException {
        DatabaseSystem system;
        switch (properties.getSystem()) {
            case "derby-memory" :
                system = DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY;
                break;
            case "derby-file" :
                system = DataBaseConnectionProvider.DBSystem.DERBY;
                break;
            case "derby-create":
                system = DataBaseConnectionProvider.DBSystem.DERBY_CREATE;
                break;
            case "mysql":
                system = DataBaseConnectionProvider.DBSystem.MYSQL;
                break;
            default: throw new UnhandledSwitchCaseException(properties.getSystem());
        }
        if("no-pool".equals(properties.getPool())) {
            return new NoPoolConnectionProvider(system, properties);
        } else if("c3p0".equals(properties.getPool())) {
            return new C3P0ConnectionProvider(system, properties);
        } else throw new UnhandledSwitchCaseException(properties.getPool());

    }
}
