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


import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

/**
 * This class wrap a ResultSet to transform every call throwing a checked SqlException into a call throwing an unchecked IllegalStateException
 * @author Grégory Van den Borre
 */
public class WrappedResultSet implements ResultSet {

    private final ResultSet resultSet;

    private WrappedResultSet(ResultSet resultSet) {
        super();
        this.resultSet = Objects.requireNonNull(resultSet);
    }

    public static WrappedResultSet wrap(ResultSet resultSet) {
        return new WrappedResultSet(resultSet);
    }

    @Override
    public final boolean next() {
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void close() {
        try {
            this.resultSet.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean wasNull()  {
        try {
            return this.resultSet.wasNull();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final String getString(int columnIndex) {
        try {
            return this.resultSet.getString(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean getBoolean(int columnIndex) {
        try {
            return this.resultSet.getBoolean(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final byte getByte(int columnIndex) {
        try {
            return this.resultSet.getByte(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final short getShort(int columnIndex) {
        try {
            return this.resultSet.getShort(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getInt(int columnIndex) {
        try {
            return this.resultSet.getInt(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final long getLong(int columnIndex) {
        try {
            return this.resultSet.getLong(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final float getFloat(int columnIndex) {
        try {
            return this.resultSet.getFloat(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final double getDouble(int columnIndex) {
        try {
            return this.resultSet.getDouble(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    @Override
    public final BigDecimal getBigDecimal(int columnIndex, int scale) {
        try {
            return this.resultSet.getBigDecimal(columnIndex, scale);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final byte[] getBytes(int columnIndex) {
        try {
            return this.resultSet.getBytes(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Date getDate(int columnIndex) {
        try {
            return this.resultSet.getDate(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Time getTime(int columnIndex) {
        try {
            return this.resultSet.getTime(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Timestamp getTimestamp(int columnIndex) {
        try {
            return this.resultSet.getTimestamp(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final InputStream getAsciiStream(int columnIndex) {
        try {
            return this.resultSet.getAsciiStream(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    @Override
    public final InputStream getUnicodeStream(int columnIndex) {
        try {
            return this.resultSet.getUnicodeStream(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final InputStream getBinaryStream(int columnIndex) {
        try {
            return this.resultSet.getBinaryStream(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final String getString(String columnLabel) {
        try {
            return this.resultSet.getString(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean getBoolean(String columnLabel) {
        try {
            return this.resultSet.getBoolean(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final byte getByte(String columnLabel) {
        try {
            return this.resultSet.getByte(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final short getShort(String columnLabel) {
        try {
            return this.resultSet.getShort(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getInt(String columnLabel) {
        try {
            return this.resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final long getLong(String columnLabel) {
        try {
            return this.resultSet.getLong(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final float getFloat(String columnLabel) {
        try {
            return this.resultSet.getFloat(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final double getDouble(String columnLabel) {
        try {
            return this.resultSet.getDouble(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    @Override
    public final BigDecimal getBigDecimal(String columnLabel, int scale) {
        try {
            return this.resultSet.getBigDecimal(columnLabel, scale);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final byte[] getBytes(String columnLabel) {
        try {
            return this.resultSet.getBytes(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Date getDate(String columnLabel) {
        try {
            return this.resultSet.getDate(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Time getTime(String columnLabel) {
        try {
            return this.resultSet.getTime(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Timestamp getTimestamp(String columnLabel) {
        try {
            return this.resultSet.getTimestamp(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final InputStream getAsciiStream(String columnLabel) {
        try {
            return this.resultSet.getAsciiStream(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    @Override
    public final InputStream getUnicodeStream(String columnLabel) {
        try {
            return this.resultSet.getUnicodeStream(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final InputStream getBinaryStream(String columnLabel) {
        try {
            return this.resultSet.getBinaryStream(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final SQLWarning getWarnings() {
        try {
            return this.resultSet.getWarnings();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void clearWarnings() {
        try {
            this.resultSet.clearWarnings();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final String getCursorName() {
        try {
            return this.resultSet.getCursorName();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final ResultSetMetaData getMetaData() {
        try {
            return this.resultSet.getMetaData();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Object getObject(int columnIndex) {
        try {
            return this.resultSet.getObject(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Object getObject(String columnLabel) {
        try {
            return this.resultSet.getObject(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int findColumn(String columnLabel) {
        try {
            return this.resultSet.findColumn(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Reader getCharacterStream(int columnIndex) {
        try {
            return this.resultSet.getCharacterStream(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Reader getCharacterStream(String columnLabel) {
        try {
            return this.resultSet.getCharacterStream(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final BigDecimal getBigDecimal(int columnIndex) {
        try {
            return this.resultSet.getBigDecimal(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final BigDecimal getBigDecimal(String columnLabel) {
        try {
            return this.resultSet.getBigDecimal(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean isBeforeFirst() {
        try {
            return this.resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean isAfterLast() {
        try {
            return this.resultSet.isAfterLast();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean isFirst() {
        try {
            return this.resultSet.isFirst();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean isLast() {
        try {
            return this.resultSet.isLast();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void beforeFirst() {
        try {
            this.resultSet.beforeFirst();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void afterLast() {
        try {
            this.resultSet.afterLast();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean first() {
        try {
            return this.resultSet.first();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean last() {
        try {
            return this.resultSet.last();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getRow() {
        try {
            return this.resultSet.getRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean absolute(int row) {
        try {
            return this.resultSet.absolute(row);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean relative(int rows) {
        try {
            return this.resultSet.relative(rows);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean previous() {
        try {
            return this.resultSet.previous();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void setFetchDirection(int direction) {
        try {
            this.resultSet.setFetchDirection(direction);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getFetchDirection() {
        try {
            return this.resultSet.getFetchDirection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void setFetchSize(int rows) {
        try {
            this.resultSet.setFetchSize(rows);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getFetchSize() {
        try {
            return this.resultSet.getFetchSize();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getType() {
        try {
            return this.resultSet.getType();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getConcurrency() {
        try {
            return this.resultSet.getConcurrency();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean rowUpdated() {
        try {
            return this.resultSet.rowUpdated();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean rowInserted() {
        try {
            return this.resultSet.rowInserted();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean rowDeleted() {
        try {
            return this.resultSet.rowDeleted();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNull(int columnIndex) {
        try {
            this.resultSet.updateNull(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBoolean(int columnIndex, boolean x) {
        try {
            this.resultSet.updateBoolean(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateByte(int columnIndex, byte x) {
        try {
            this.resultSet.updateByte(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateShort(int columnIndex, short x) {
        try {
            this.resultSet.updateShort(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateInt(int columnIndex, int x) {
        try {
            this.resultSet.updateInt(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateLong(int columnIndex, long x) {
        try {
            this.resultSet.updateLong(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateFloat(int columnIndex, float x) {
        try {
            this.resultSet.updateFloat(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateDouble(int columnIndex, double x) {
        try {
            this.resultSet.updateDouble(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBigDecimal(int columnIndex, BigDecimal x) {
        try {
            this.resultSet.updateBigDecimal(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateString(int columnIndex, String x) {
        try {
            this.resultSet.updateString(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBytes(int columnIndex, byte[] x) {
        try {
            this.resultSet.updateBytes(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateDate(int columnIndex, Date x) {
        try {
            this.resultSet.updateDate(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateTime(int columnIndex, Time x) {
        try {
            this.resultSet.updateTime(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateTimestamp(int columnIndex, Timestamp x) {
        try {
            this.resultSet.updateTimestamp(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateAsciiStream(int columnIndex, InputStream x, int length) {
        try {
            this.resultSet.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBinaryStream(int columnIndex, InputStream x, int length) {
        try {
            this.resultSet.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateCharacterStream(int columnIndex, Reader x, int length) {
        try {
            this.resultSet.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(int columnIndex, Object x, int scaleOrLength) {
        try {
            this.resultSet.updateObject(columnIndex, x, scaleOrLength);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(int columnIndex, Object x) {
        try {
            this.resultSet.updateObject(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNull(String columnLabel) {
        try {
            this.resultSet.updateNull(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBoolean(String columnLabel, boolean x) {
        try {
            this.resultSet.updateBoolean(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateByte(String columnLabel, byte x) {
        try {
            this.resultSet.updateByte(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateShort(String columnLabel, short x) {
        try {
            this.resultSet.updateShort(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateInt(String columnLabel, int x) {
        try {
            this.resultSet.updateInt(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateLong(String columnLabel, long x) {
        try {
            this.resultSet.updateLong(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateFloat(String columnLabel, float x) {
        try {
            this.resultSet.updateFloat(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateDouble(String columnLabel, double x) {
        try {
            this.resultSet.updateDouble(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBigDecimal(String columnLabel, BigDecimal x) {
        try {
            this.resultSet.updateBigDecimal(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateString(String columnLabel, String x) {
        try {
            this.resultSet.updateString(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBytes(String columnLabel, byte[] x) {
        try {
            this.resultSet.updateBytes(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateDate(String columnLabel, Date x) {
        try {
            this.resultSet.updateDate(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateTime(String columnLabel, Time x) {
        try {
            this.resultSet.updateTime(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateTimestamp(String columnLabel, Timestamp x) {
        try {
            this.resultSet.updateTimestamp(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateAsciiStream(String columnLabel, InputStream x, int length) {
        try {
            this.resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBinaryStream(String columnLabel, InputStream x, int length) {
        try {
            this.resultSet.updateBinaryStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateCharacterStream(String columnLabel, Reader reader, int length) {
        try {
            this.resultSet.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(String columnLabel, Object x, int scaleOrLength) {
        try {
            this.resultSet.updateObject(columnLabel, x, scaleOrLength);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(String columnLabel, Object x) {
        try {
            this.resultSet.updateObject(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void insertRow() {
        try {
            this.resultSet.insertRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateRow() {
        try {
            this.resultSet.updateRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void deleteRow() {
        try {
            this.resultSet.deleteRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void refreshRow() {
        try {
            this.resultSet.refreshRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void cancelRowUpdates() {
        try {
            this.resultSet.cancelRowUpdates();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void moveToInsertRow() {
        try {
            this.resultSet.moveToInsertRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void moveToCurrentRow() {
        try {
            this.resultSet.moveToCurrentRow();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Statement getStatement() {
        try {
            return this.resultSet.getStatement();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Object getObject(int columnIndex, Map<String, Class<?>> map) {
        try {
            return this.resultSet.getObject(columnIndex, map);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Ref getRef(int columnIndex) {
        try {
            return this.resultSet.getRef(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Blob getBlob(int columnIndex) {
        try {
            return this.resultSet.getBlob(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Clob getClob(int columnIndex) {
        try {
            return this.resultSet.getClob(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Array getArray(int columnIndex) {
        try {
            return this.resultSet.getArray(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Object getObject(String columnLabel, Map<String, Class<?>> map) {
        try {
            return this.resultSet.getObject(columnLabel, map);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Ref getRef(String columnLabel) {
        try {
            return this.resultSet.getRef(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Blob getBlob(String columnLabel) {
        try {
            return this.resultSet.getBlob(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Clob getClob(String columnLabel) {
        try {
            return this.resultSet.getClob(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Array getArray(String columnLabel) {
        try {
            return this.resultSet.getArray(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Date getDate(int columnIndex, Calendar cal) {
        try {
            return this.resultSet.getDate(columnIndex, cal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Date getDate(String columnLabel, Calendar cal) {
        try {
            return this.resultSet.getDate(columnLabel, cal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Time getTime(int columnIndex, Calendar cal) {
        try {
            return this.resultSet.getTime(columnIndex, cal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Time getTime(String columnLabel, Calendar cal) {
        try {
            return this.resultSet.getTime(columnLabel, cal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Timestamp getTimestamp(int columnIndex, Calendar cal) {
        try {
            return this.resultSet.getTimestamp(columnIndex, cal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Timestamp getTimestamp(String columnLabel, Calendar cal) {
        try {
            return this.resultSet.getTimestamp(columnLabel, cal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final URL getURL(int columnIndex) {
        try {
            return this.resultSet.getURL(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final URL getURL(String columnLabel) {
        try {
            return this.resultSet.getURL(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateRef(int columnIndex, Ref x) {
        try {
            this.resultSet.updateRef(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateRef(String columnLabel, Ref x) {
        try {
            this.resultSet.updateRef(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBlob(int columnIndex, Blob x) {
        try {
            this.resultSet.updateBlob(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBlob(String columnLabel, Blob x) {
        try {
            this.resultSet.updateBlob(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateClob(int columnIndex, Clob x) {
        try {
            this.resultSet.updateClob(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateClob(String columnLabel, Clob x) {
        try {
            this.resultSet.updateClob(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateArray(int columnIndex, Array x) {
        try {
            this.resultSet.updateArray(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateArray(String columnLabel, Array x) {
        try {
            this.resultSet.updateArray(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final RowId getRowId(int columnIndex) {
        try {
            return this.resultSet.getRowId(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final RowId getRowId(String columnLabel) {
        try {
            return this.resultSet.getRowId(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateRowId(int columnIndex, RowId x) {
        try {
            this.resultSet.updateRowId(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateRowId(String columnLabel, RowId x) {
        try {
            this.resultSet.updateRowId(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final int getHoldability() {
        try {
            return this.resultSet.getHoldability();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean isClosed() {
        try {
            return this.resultSet.isClosed();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNString(int columnIndex, String nString) {
        try {
            this.resultSet.updateNString(columnIndex, nString);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNString(String columnLabel, String nString) {
        try {
            this.resultSet.updateNString(columnLabel, nString);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNClob(int columnIndex, NClob nClob) {
        try {
            this.resultSet.updateNClob(columnIndex, nClob);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNClob(String columnLabel, NClob nClob) {
        try {
            this.resultSet.updateNClob(columnLabel, nClob);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final NClob getNClob(int columnIndex) {
        try {
            return this.resultSet.getNClob(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final NClob getNClob(String columnLabel) {
        try {
            return this.resultSet.getNClob(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final SQLXML getSQLXML(int columnIndex) {
        try {
            return this.resultSet.getSQLXML(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final SQLXML getSQLXML(String columnLabel) {
        try {
            return this.resultSet.getSQLXML(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateSQLXML(int columnIndex, SQLXML xmlObject) {
        try {
            this.resultSet.updateSQLXML(columnIndex, xmlObject);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateSQLXML(String columnLabel, SQLXML xmlObject) {
        try {
            this.resultSet.updateSQLXML(columnLabel, xmlObject);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final String getNString(int columnIndex) {
        try {
            return this.resultSet.getNString(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final String getNString(String columnLabel) {
        try {
            return this.resultSet.getNString(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Reader getNCharacterStream(int columnIndex) {
        try {
            return this.resultSet.getNCharacterStream(columnIndex);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final Reader getNCharacterStream(String columnLabel) {
        try {
            return this.resultSet.getNCharacterStream(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNCharacterStream(int columnIndex, Reader x, long length) {
        try {
            this.resultSet.updateNCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNCharacterStream(String columnLabel, Reader reader, long length) {
        try {
            this.resultSet.updateNCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateAsciiStream(int columnIndex, InputStream x, long length) {
        try {
            this.resultSet.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBinaryStream(int columnIndex, InputStream x, long length) {
        try {
            this.resultSet.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateCharacterStream(int columnIndex, Reader x, long length) {
        try {
            this.resultSet.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateAsciiStream(String columnLabel, InputStream x, long length) {
        try {
            this.resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBinaryStream(String columnLabel, InputStream x, long length) {
        try {
            this.resultSet.updateBinaryStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateCharacterStream(String columnLabel, Reader reader, long length) {
        try {
            this.resultSet.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBlob(int columnIndex, InputStream inputStream, long length) {
        try {
            this.resultSet.updateBlob(columnIndex, inputStream, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBlob(String columnLabel, InputStream inputStream, long length) {
        try {
            this.resultSet.updateBlob(columnLabel, inputStream, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateClob(int columnIndex, Reader reader, long length) {
        try {
            this.resultSet.updateClob(columnIndex, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateClob(String columnLabel, Reader reader, long length) {
        try {
            this.resultSet.updateClob(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNClob(int columnIndex, Reader reader, long length) {
        try {
            this.resultSet.updateNClob(columnIndex, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNClob(String columnLabel, Reader reader, long length) {
        try {
            this.resultSet.updateNClob(columnLabel, reader, length);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNCharacterStream(int columnIndex, Reader x) {
        try {
            this.resultSet.updateNCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNCharacterStream(String columnLabel, Reader reader) {
        try {
            this.resultSet.updateNCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateAsciiStream(int columnIndex, InputStream x) {
        try {
            this.resultSet.updateAsciiStream(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBinaryStream(int columnIndex, InputStream x) {
        try {
            this.resultSet.updateBinaryStream(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateCharacterStream(int columnIndex, Reader x) {
        try {
            this.resultSet.updateCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateAsciiStream(String columnLabel, InputStream x) {
        try {
            this.resultSet.updateAsciiStream(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBinaryStream(String columnLabel, InputStream x) {
        try {
            this.resultSet.updateBinaryStream(columnLabel, x);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateCharacterStream(String columnLabel, Reader reader) {
        try {
            this.resultSet.updateCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBlob(int columnIndex, InputStream inputStream) {
        try {
            this.resultSet.updateBlob(columnIndex, inputStream);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateBlob(String columnLabel, InputStream inputStream) {
        try {
            this.resultSet.updateBlob(columnLabel, inputStream);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateClob(int columnIndex, Reader reader) {
        try {
            this.resultSet.updateClob(columnIndex, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateClob(String columnLabel, Reader reader) {
        try {
            this.resultSet.updateClob(columnLabel, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNClob(int columnIndex, Reader reader) {
        try {
            this.resultSet.updateNClob(columnIndex, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateNClob(String columnLabel, Reader reader) {
        try {
            this.resultSet.updateNClob(columnLabel, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final <T> T getObject(int columnIndex, Class<T> type) {
        try {
            return this.resultSet.getObject(columnIndex, type);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final <T> T getObject(String columnLabel, Class<T> type) {
        try {
            return this.resultSet.getObject(columnLabel, type);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(int columnIndex, Object x, SQLType targetSqlType, int scaleOrLength) {
        try {
            this.resultSet.updateObject(columnIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(String columnLabel, Object x, SQLType targetSqlType, int scaleOrLength) {
        try {
            this.resultSet.updateObject(columnLabel, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(int columnIndex, Object x, SQLType targetSqlType) {
        try {
            this.resultSet.updateObject(columnIndex, x, targetSqlType);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final void updateObject(String columnLabel, Object x, SQLType targetSqlType) {
        try {
            this.resultSet.updateObject(columnLabel, x, targetSqlType);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final <T> T unwrap(Class<T> iface) {
        try {
            return this.resultSet.unwrap(iface);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public final boolean isWrapperFor(Class<?> iface) {
        try {
            return this.resultSet.isWrapperFor(iface);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
