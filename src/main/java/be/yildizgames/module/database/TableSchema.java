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

/**
 * @author Grégory Van den Borre
 */
public class TableSchema {

    private final TableSchemaColumn[] columns;

    private final TableSchemaColumn id;

    private final String tableName;

    private final boolean generatedId;

    private TableSchema(String tableName, TableSchemaColumn id, boolean generatedId, TableSchemaColumn[] columns) {
        super();
        this.id = id;
        this.columns = columns;
        this.tableName = tableName;
        this.generatedId = generatedId;
    }

    private TableSchema(String tableName, TableSchemaColumn[] columns) {
        super();
        this.id = null;
        this.columns = columns;
        this.tableName = tableName;
        this.generatedId = false;
    }

    public static TableSchema createWithId(String tableName, TableSchemaColumn id, TableSchemaColumn... columns) {
        return new TableSchema(tableName, id, false, columns);
    }

    public static TableSchema createWithGeneratedId(String tableName, TableSchemaColumn id, TableSchemaColumn... columns) {
        return new TableSchema(tableName, id, true, columns);
    }

    public static TableSchema createWithoutId(String tableName, TableSchemaColumn... columns) {
        return new TableSchema(tableName, columns);
    }

    public final String getTableName() {
        return this.tableName;
    }

    TableSchemaColumn getId() {
        return this.id;
    }

    TableSchemaColumn[] getColumns() {
        return columns;
    }

    boolean isGeneratedId() {
        return this.generatedId;
    }

    @Override
    public final String toString() {
        return this.tableName;
    }
}
