/*
 * MIT License
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.yildizgames.module.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Grégory Van den Borre
 */
class QueryExecutorTest {

    @Nested
    class CreateTableQuery {

        @Test
        void happyFlow() {
            TableSchema schema = TableSchema.createWithId("test",
                    TableSchemaColumn.character("idcol", 32).notNull(),
                    TableSchemaColumn.integer("cola").notNull(),
                    TableSchemaColumn.varchar("colb", 10).notNull().unique(),
                    TableSchemaColumn.tinyInt("colc").notNull().unique());

            String expected = "CREATE CACHED TABLE IF NOT EXISTS test (idcol char(32) PRIMARY KEY," +
                    "cola int NOT NULL," +
                    "colb varchar(10) NOT NULL UNIQUE," +
                    "colc tinyint NOT NULL UNIQUE);";
            Assertions.assertEquals(expected, QueryExecutor.createTableQuery(schema));
        }

        @Test
        void onlyId() {
            TableSchema schema = TableSchema.createWithId("test",
                    TableSchemaColumn.character("idcol", 32).notNull());

            String expected = "CREATE CACHED TABLE IF NOT EXISTS test (idcol char(32) PRIMARY KEY);";
            Assertions.assertEquals(expected, QueryExecutor.createTableQuery(schema));
        }

    }
}
