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

/**
 * Representation of the properties required to connect to a database.
 *
 * @author Grégory Van den Borre
 */
public interface DbProperties {

    /**
     * @return The user login to connect to the database.
     */
    //@Ensures("return value != null")
    String getDbUser();

    /**
     * @return The database connection port number.
     */
    //@Ensure("0 >= return value", "return value <= 65635")
    int getDbPort();

    /**
     * @return The user password to connect to the database.
     */
    //@Ensures("return value != null")
    String getDbPassword();

    /**
     * @return The database connection host address.
     */
    //@Ensures("return value != null")
    String getDbHost();

    /**
     * @return The database name.
     */
    //@Ensures("return value != null")
    String getDbName();

    /**
     * Invariant to ensure the state is correct.
     */
    class Invariant {

        /**
         * Prevent instantiation.
         */
        private Invariant() {
            super();
        }

        public static void check(String user, String password, String database, String host, int port) {
            checkUser(user);
            checkPassword(password);
            checkDatabase(database);
            checkHost(host);
            checkPort(port);
        }

        private static void checkUser(String user) {
            if(user == null) {
                throw new NullPointerException("User is null.");
            }
        }

        private static void checkPassword(String password) {
            if(password == null) {
                throw new NullPointerException("Password is null.");
            }
        }

        private static void checkDatabase(String database) {
            if(database == null) {
                throw new NullPointerException("database is null.");
            }
        }

        private static void checkHost(String host) {
            if(host == null) {
                throw new NullPointerException("User is null.");
            }
        }

        private static void checkPort(int port) {
            if(port < 0 || port > 65635) {
                throw new IllegalArgumentException("Port must be between 0 and 65635, value is " + port);
            }
        }
    }
}
