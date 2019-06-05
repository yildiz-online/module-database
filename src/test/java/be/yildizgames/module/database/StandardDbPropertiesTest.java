/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE  SOFTWARE.
 */

package be.yildizgames.module.database;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Grégory Van den Borre
 */
public class StandardDbPropertiesTest {


    @Test
    public void testInvariantConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<DbProperties.DbPropertiesInvariant> constructor = DbProperties.DbPropertiesInvariant.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void happyFlow() {
        Properties base = new Properties();
        base.setProperty("database.user", "user-ok");
        base.setProperty("database.password","password-ok");
        base.setProperty("database.root.user","user-ok");
        base.setProperty("database.root.password","password-ok");
        base.setProperty("database.name","name-ok");
        base.setProperty("database.host","host-ok");
        base.setProperty("database.port","10");
        base.setProperty("database.system","system-ok");
        DbProperties p = new StandardDbProperties(base);
        assertEquals("user-ok", p.getDbUser());
        assertEquals("password-ok", p.getDbPassword());
        assertEquals("user-ok", p.getDbRootUser());
        assertEquals("password-ok", p.getDbRootPassword());
        assertEquals("name-ok", p.getDbName());
        assertEquals("host-ok", p.getDbHost());
        assertEquals("system-ok", p.getSystem());
        assertEquals(10, p.getDbPort());
    }

    @Test
    public void userNull() {
        assertThrows(NullPointerException.class, () -> new StandardDbProperties(null));
    }

    @Test
    public void passwordNull() {
        Properties base = new Properties();
        base.setProperty("database.user", "user-ok");
        base.setProperty("database.root.user","user-ok");
        base.setProperty("database.root.password","password-ok");
        base.setProperty("database.name","name-ok");
        base.setProperty("database.host","host-ok");
        base.setProperty("database.port","10");
        base.setProperty("database.system","system-ok");
        assertThrows(NullPointerException.class, () -> new StandardDbProperties(base));
    }

    @Test
    public void databaseNull() {
        Properties base = new Properties();
        base.setProperty("database.user", "user-ok");
        base.setProperty("database.password","password-ok");
        base.setProperty("database.root.user","user-ok");
        base.setProperty("database.root.password","password-ok");
        base.setProperty("database.host","host-ok");
        base.setProperty("database.port","10");
        base.setProperty("database.system","system-ok");
        assertThrows(NullPointerException.class, () -> new StandardDbProperties(base));
    }

    @Test
    public void hostNull() {
        Properties base = new Properties();
        base.setProperty("database.user", "user-ok");
        base.setProperty("database.password","password-ok");
        base.setProperty("database.root.user","user-ok");
        base.setProperty("database.root.password","password-ok");
        base.setProperty("database.name","name-ok");
        base.setProperty("database.port","10");
        base.setProperty("database.system","system-ok");
        assertThrows(NullPointerException.class, () -> new StandardDbProperties(base));
    }

    @Test
    public void portTooLow() {
        Properties base = new Properties();
        base.setProperty("database.user", "user-ok");
        base.setProperty("database.password","password-ok");
        base.setProperty("database.root.user","user-ok");
        base.setProperty("database.root.password","password-ok");
        base.setProperty("database.name","name-ok");
        base.setProperty("database.host","host-ok");
        base.setProperty("database.port","-1");
        base.setProperty("database.system","system-ok");
        assertThrows(IllegalArgumentException.class, () -> new StandardDbProperties(base));
    }

    @Test
    public void portTooHigh() {
        Properties base = new Properties();
        base.setProperty("database.user", "user-ok");
        base.setProperty("database.password","password-ok");
        base.setProperty("database.root.user","user-ok");
        base.setProperty("database.root.password","password-ok");
        base.setProperty("database.name","name-ok");
        base.setProperty("database.host","host-ok");
        base.setProperty("database.port","100000000000000");
        base.setProperty("database.system","system-ok");
        assertThrows(IllegalArgumentException.class, () -> new StandardDbProperties(base));
    }

}
