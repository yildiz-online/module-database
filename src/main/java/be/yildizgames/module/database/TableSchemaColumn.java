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
public class TableSchemaColumn {

    private final ColumnType type;

    private final String title;

    private final boolean nullable;

    private final int size;

    private TableSchemaColumn(String title, ColumnType type, int size, boolean nullable) {
        super();
        this.title = title;
        this.type = type;
        this.nullable = nullable;
        this.size = size;
    }

    public static TableSchemaColumn intNotNull(String title) {
        return new TableSchemaColumn(title, ColumnType.INT, -1, false);
    }

    public static TableSchemaColumn varcharNotNull(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.VARCHAR, size, false);
    }

    public static TableSchemaColumn charNotNull(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.CHAR, size, false);
    }

    public static TableSchemaColumn tinyIntNotNull(String title) {
        return new TableSchemaColumn(title, ColumnType.TINYINT, -1, false);
    }

    String getTitle() {
        return title;
    }

    String getType() {
        return type.name().toLowerCase();
    }

    int getSize() {
        return size;
    }

    public boolean isNullable() {
        return this.nullable;
    }

    private enum ColumnType {

        INT, TINYINT, VARCHAR, CHAR;

    }
}
