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

/**
 * Representation of the properties required to connect to a database.
 *
 * @author Grégory Van den Borre
 */
public interface DbProperties {

    /**
     * @return The user login to connect to the database.
     * @Ensures return value != null.
     */
    String getDbUser();

    /**
     * @return The database connection port number.
     * @Ensure 0 >= return value <= 65635
     */
    int getDbPort();

    /**
     * @return The user password to connect to the database.
     * @Ensures return value != null.
     */
    String getDbPassword();

    /**
     * @return The database connection host address.
     * @Ensures return value != null.
     */
    String getDbHost();

    /**
     * @return The database name.
     * @Ensures return value != null.
     */
    String getDbName();
}
