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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Grégory Van den Borre
 */
class QueryExecutorTest {

    @Test
    void testAddColumnQueryToTable() {
        QueryExecutor executor = new QueryExecutor(null);
        TableSchema table = TableSchema.createWithoutId("testTable");
        TableSchemaColumn column = TableSchemaColumn.varchar("newColumn", 50);
        
        Assertions.assertEquals("ALTER TABLE testTable ADD newColumn varchar(50);", executor.addColumnToTableQuery(table, column));
    }

    @Test
    void testAddColumnToTableQueryWithIntegerColumn() {

        QueryExecutor executor = new QueryExecutor(null);
        TableSchema table = TableSchema.createWithoutId("testTable");
        TableSchemaColumn column = TableSchemaColumn.integer("newColumn");

        Assertions.assertEquals("ALTER TABLE testTable ADD newColumn int NOT NULL DEFAULT 0;", executor.addColumnToTableQuery(table, column));
    }


    @Test
    void testAddColumnToTableQueryWithSizedColumn() {
        QueryExecutor executor = new QueryExecutor(null);
        TableSchema table = TableSchema.createWithoutId("testTable");
        TableSchemaColumn column = TableSchemaColumn.varchar("testColumn", 100);
        
        String result = executor.addColumnToTableQuery(table, column);
        
        assertEquals("ALTER TABLE testTable ADD testColumn varchar(100);", result);
    }

    @Test
    void testAddColumnToTableQueryWithUnsizedColumn() {
        QueryExecutor executor = new QueryExecutor(null);
        TableSchema table = TableSchema.createWithoutId("testTable");
        TableSchemaColumn column = TableSchemaColumn.integer("testColumn");
        
        String result = executor.addColumnToTableQuery(table, column);
        
        assertEquals("ALTER TABLE testTable ADD testColumn int NOT NULL DEFAULT 0;", result);
    }

}