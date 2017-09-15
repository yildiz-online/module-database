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

import be.yildiz.common.exeption.ResourceMissingException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grégory Van den Borre
 */
class DbFilePropertiesTest {

    @Test
    void testInvariantConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<DbProperties.DbPropertiesInvariant> constructor = DbProperties.DbPropertiesInvariant.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void happyFlow() {
        File f = getFile("dbfile-happyflow.properties");
        DbProperties p = new DbFileProperties(f.getAbsolutePath());
        assertEquals("user-ok", p.getDbUser());
        assertEquals("password-ok", p.getDbPassword());
        assertEquals("name-ok", p.getDbName());
        assertEquals("host-ok", p.getDbHost());
        assertEquals("system-ok", p.getSystem());
        assertEquals(10, p.getDbPort());
    }

    @Test
    void noFile() {
        assertThrows(ResourceMissingException.class, () -> new DbFileProperties("nowhere"));
    }

    @Test
    void userNull() {
        File f = getFile("dbfile-usernull.properties");
        assertThrows(NullPointerException.class, () -> new DbFileProperties(f.getAbsolutePath()));
    }

    @Test
    void passwordNull() {
        File f = getFile("dbfile-passwordnull.properties");
        assertThrows(NullPointerException.class, () -> new DbFileProperties(f.getAbsolutePath()));
    }

    @Test
    void databaseNull() {
        File f = getFile("dbfile-namenull.properties");
        assertThrows(NullPointerException.class, () -> new DbFileProperties(f.getAbsolutePath()));
    }

    @Test
    void hostNull() {
        File f = getFile("dbfile-hostnull.properties");
        assertThrows(NullPointerException.class, () -> new DbFileProperties(f.getAbsolutePath()));
    }

    @Test
    void portTooLow() {
        File f = getFile("dbfile-portnegative.properties");
        assertThrows(IllegalArgumentException.class, () -> new DbFileProperties(f.getAbsolutePath()));
    }

    @Test
    void portTooHigh() {
        File f = getFile("dbfile-porttoohigh.properties");
        assertThrows(IllegalArgumentException.class, () -> new DbFileProperties(f.getAbsolutePath()));
    }

    private static File getFile(String name) {
        URL url = DbProperties.class.getClassLoader().getResource(name);
        if(url == null) {
            throw new ResourceMissingException(name);
        }
        return new File(url.getFile()).getAbsoluteFile();
    }

}
