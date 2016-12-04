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

import be.yildiz.common.exeption.ResourceMissingException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * @author Grégory Van den Borre
 */
public class DbFilePropertiesTest {

    @Test
    public void happyFlow() {
        File f = getFile("dbfile-happyflow.properties");
        DbProperties p = new DbFileProperties(f.getAbsolutePath());
        Assert.assertEquals("user-ok", p.getDbUser());
        Assert.assertEquals("password-ok", p.getDbPassword());
        Assert.assertEquals("name-ok", p.getDbName());
        Assert.assertEquals("host-ok", p.getDbHost());
        Assert.assertEquals(10, p.getDbPort());
    }

    @Test(expected = ResourceMissingException.class)
    public void noFile() {
        new DbFileProperties("nowhere");
    }

    @Test(expected = NullPointerException.class)
    public void userNull() {
        File f = getFile("dbfile-usernull.properties");
        new DbFileProperties(f.getAbsolutePath());
    }

    @Test(expected = NullPointerException.class)
    public void passwordNull() {
        File f = getFile("dbfile-passwordnull.properties");
        new DbFileProperties(f.getAbsolutePath());
    }

    @Test(expected = NullPointerException.class)
    public void databaseNull() {
        File f = getFile("dbfile-namenull.properties");
        new DbFileProperties(f.getAbsolutePath());
    }

    @Test(expected = NullPointerException.class)
    public void hostNull() {
        File f = getFile("dbfile-hostnull.properties");
        new DbFileProperties(f.getAbsolutePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void portTooLow() {
        File f = getFile("dbfile-portnegative.properties");
        new DbFileProperties(f.getAbsolutePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void portTooHigh() {
        File f = getFile("dbfile-porttoohigh.properties");
        new DbFileProperties(f.getAbsolutePath());
    }

    private static File getFile(String name) {
        URL url = DbProperties.class.getClassLoader().getResource(name);
        if(url == null) {
            throw new ResourceMissingException(name);
        }
        return new File(url.getFile()).getAbsoluteFile();
    }

}
