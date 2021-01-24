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

    private final boolean indexed;

    private TableSchemaColumn(String title, ColumnType type, int size, boolean nullable, boolean indexed) {
        super();
        this.title = title;
        this.type = type;
        this.nullable = nullable;
        this.size = size;
        this.indexed = indexed;
    }

    public static TableSchemaColumn intNotNull(String title) {
        return new TableSchemaColumn(title, ColumnType.INT, -1, false, false);
    }

    public static TableSchemaColumn varcharNotNull(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.VARCHAR, size, false, false);
    }

    public static TableSchemaColumn charNotNull(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.CHAR, size, false, false);
    }

    public static TableSchemaColumn tinyIntNotNull(String title) {
        return new TableSchemaColumn(title, ColumnType.TINYINT, -1, false, false);
    }

    public static TableSchemaColumn booleanNotNull(String title) {
        return new TableSchemaColumn(title, ColumnType.BOOLEAN, -1, false, false);
    }

    public static TableSchemaColumn bigintNotNull(String title) {
        return new TableSchemaColumn(title, ColumnType.BIGINT, -1, false, false);
    }

    public static TableSchemaColumn intNotNullIndexed(String title) {
        return new TableSchemaColumn(title, ColumnType.INT, -1, false, true);
    }

    public static TableSchemaColumn varcharNotNullIndexed(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.VARCHAR, size, false, true);
    }

    public static TableSchemaColumn charNotNullIndexed(String title, int size) {
        return new TableSchemaColumn(title, ColumnType.CHAR, size, false, true);
    }

    public static TableSchemaColumn tinyIntNotNullIndexed(String title) {
        return new TableSchemaColumn(title, ColumnType.TINYINT, -1, false, true);
    }

    public static TableSchemaColumn booleanNotNullIndexed(String title) {
        return new TableSchemaColumn(title, ColumnType.BOOLEAN, -1, false, true);
    }

    public static TableSchemaColumn bigintNotNullIndexed(String title) {
        return new TableSchemaColumn(title, ColumnType.BIGINT, -1, false, true);
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


    private enum ColumnType {

        INT, TINYINT, BIGINT, VARCHAR, CHAR, BOOLEAN;

    }
}
