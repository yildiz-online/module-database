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

    private boolean nullable = true;

    private final int size;

    private boolean indexed = false;

    private boolean unique = false;

    private TableSchemaColumn(String title, ColumnType type, int size) {
        super();
        this.title = title;
        this.type = type;
        this.size = size;
    }

    public final TableSchemaColumn notNull() {
        this.nullable = false;
        return this;
    }

    public final TableSchemaColumn indexed() {
        this.indexed = true;
        return this;
    }

    public final TableSchemaColumn unique() {
        this.unique = true;
        return this;
    }

    public static TableSchemaColumn integer(String title) {
        return new TableSchemaColumn(title, ColumnType.INT, -1);
    }

    public static TableSchemaColumn varchar(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.VARCHAR, size);
    }

    public static TableSchemaColumn character(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.CHAR, size);
    }

    public static TableSchemaColumn tinyInt(String title) {
        return new TableSchemaColumn(title, ColumnType.TINYINT, -1);
    }

    public static TableSchemaColumn bool(String title) {
        return new TableSchemaColumn(title, ColumnType.BOOLEAN, -1);
    }

    public static TableSchemaColumn bigint(String title) {
        return new TableSchemaColumn(title, ColumnType.BIGINT, -1);
    }

    final String getTitle() {
        return this.title;
    }

    final String getType() {
        return this.type.name().toLowerCase();
    }

    final int getSize() {
        return this.size;
    }

    final boolean isNullable() {
        return this.nullable;
    }

    final boolean isIndexed() {
        return this.indexed;
    }

    final boolean isUnique() {
        return this.unique;
    }


    private enum ColumnType {

        INT, TINYINT, BIGINT, VARCHAR, CHAR, BOOLEAN;

    }
}
