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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author Grégory Van den Borre
 */
public class WrappedPreparedStatement implements PreparedStatement {

    private final PreparedStatement statement;

    private WrappedPreparedStatement(final PreparedStatement statement) {
        super();
        this.statement = Objects.requireNonNull(statement);
    }

    public static WrappedPreparedStatement create(final PreparedStatement statement) {
        return new WrappedPreparedStatement(statement);
    }

    @Override
    public final ResultSet executeQuery() {
        try {
            return this.statement.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int executeUpdate() {
        try {
            return this.statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNull(int i, int i1) {
        try {
            this.statement.setNull(i, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBoolean(int i, boolean b) {
        try {
            this.statement.setBoolean(i, b);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setByte(int i, byte b) {
        try {
            this.statement.setByte(i, b);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setShort(int i, short i1) {
        try {
            this.statement.setShort(i, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setInt(int i, int i1) {
        try {
            this.statement.setInt(i, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setLong(int i, long l) {
        try {
            this.statement.setLong(i, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setFloat(int i, float v) {
        try {
            this.statement.setFloat(i, v);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setDouble(int i, double v) {
        try {
            this.statement.setDouble(i, v);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBigDecimal(int i, BigDecimal bigDecimal) {
        try {
            this.statement.setBigDecimal(i, bigDecimal);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setString(int i, String s) {
        try {
            this.statement.setString(i, s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBytes(int i, byte[] bytes) {
        try {
            this.statement.setBytes(i, bytes);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setDate(int i, Date date) {
        try {
            this.statement.setDate(i, date);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setTime(int i, Time time) {
        try {
            this.statement.setTime(i, time);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp) {
        try {
            this.statement.setTimestamp(i, timestamp);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, int i1) {
        try {
            this.statement.setAsciiStream(i, inputStream, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public void setUnicodeStream(int i, InputStream inputStream, int i1) {
        try {
            this.statement.setUnicodeStream(i, inputStream, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, int i1) {
        try {
            this.statement.setBinaryStream(i, inputStream, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void clearParameters() {
        try {
            this.statement.clearParameters();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setObject(int i, Object o, int i1) {
        try {
            this.statement.setObject(i, o, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setObject(int i, Object o) {
        try {
            this.statement.setObject(i, o);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean execute() {
        try {
            return this.statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addBatch() {
        try {
            this.statement.addBatch();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setCharacterStream(int i, Reader reader, int i1) {
        try {
            this.statement.setCharacterStream(i, reader, i1);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setRef(int i, Ref ref) {
        try {
            this.statement.setRef(i, ref);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBlob(int i, Blob blob) {
        try {
            this.statement.setBlob(i, blob);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setClob(int i, Clob clob) {
        try {
            this.statement.setClob(i, clob);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setArray(int i, Array array) {
        try {
            this.statement.setArray(i, array);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ResultSetMetaData getMetaData() {
        try {
            return this.statement.getMetaData();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setDate(int i, Date date, Calendar calendar) {
        try {
            this.statement.setDate(i, date, calendar);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setTime(int i, Time time, Calendar calendar) {
        try {
            this.statement.setTime(i, time, calendar);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) {
        try {
            this.statement.setTimestamp(i, timestamp, calendar);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNull(int i, int i1, String s) {
        try {
            this.statement.setNull(i, i1, s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setURL(int i, URL url) {
        try {
            this.statement.setURL(i, url);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ParameterMetaData getParameterMetaData() {
        try {
            return this.statement.getParameterMetaData();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setRowId(int i, RowId rowId) {
        try {
            this.statement.setRowId(i, rowId);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNString(int i, String s) {
        try {
            this.statement.setNString(i, s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNCharacterStream(int i, Reader reader, long l) {
        try {
            this.statement.setNCharacterStream(i, reader, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNClob(int i, NClob nClob) {
        try {
            this.statement.setNClob(i, nClob);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setClob(int i, Reader reader, long l) {
        try {
            this.statement.setClob(i, reader, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBlob(int i, InputStream inputStream, long l) {
        try {
            this.statement.setBlob(i, inputStream, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNClob(int i, Reader reader, long l) {
        try {
            this.statement.setNClob(i, reader, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setSQLXML(int i, SQLXML sqlxml) {
        try {
            this.statement.setSQLXML(i, sqlxml);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setObject(int i, Object o, int i1, int i2) {
        try {
            this.statement.setObject(i, o, i1, i2);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) {
        try {
            this.statement.setAsciiStream(i, inputStream, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) {
        try {
            this.statement.setBinaryStream(i, inputStream, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setCharacterStream(int i, Reader reader, long l) {
        try {
            this.statement.setCharacterStream(i, reader, l);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream) {
        try {
            this.statement.setAsciiStream(i, inputStream);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream) {
        try {
            this.statement.setBinaryStream(i, inputStream);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setCharacterStream(int i, Reader reader) {
        try {
            this.statement.setCharacterStream(i, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNCharacterStream(int i, Reader reader) {
        try {
            this.statement.setNCharacterStream(i, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setClob(int i, Reader reader) {
        try {
            this.statement.setClob(i, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setBlob(int i, InputStream inputStream) {
        try {
            this.statement.setBlob(i, inputStream);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setNClob(int i, Reader reader) {
        try {
            this.statement.setNClob(i, reader);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ResultSet executeQuery(String s) {
        try {
            return this.statement.executeQuery(s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int executeUpdate(String s) {
        try {
            return this.statement.executeUpdate(s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        try {
            this.statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getMaxFieldSize() {
        try {
            return this.statement.getMaxFieldSize();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setMaxFieldSize(int i) {
        try {
            this.statement.setMaxFieldSize(i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getMaxRows() {
        try {
            return this.statement.getMaxRows();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setMaxRows(int i) {
        try {
            this.statement.setMaxRows(i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setEscapeProcessing(boolean b) {
        try {
            this.statement.setEscapeProcessing(b);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getQueryTimeout() {
        try {
            return this.statement.getQueryTimeout();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setQueryTimeout(int i) {
        try {
            this.statement.setQueryTimeout(i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void cancel() {
        try {
            this.statement.cancel();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public SQLWarning getWarnings() {
        try {
            return this.statement.getWarnings();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void clearWarnings() {
        try {
            this.statement.clearWarnings();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setCursorName(String s) {
        try {
            this.statement.setCursorName(s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean execute(String s) {
        try {
            return this.statement.execute(s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ResultSet getResultSet() {
        try {
            return this.statement.getResultSet();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getUpdateCount() {
        try {
            return this.statement.getUpdateCount();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean getMoreResults() {
        try {
            return this.statement.getMoreResults();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setFetchDirection(int i) {
        try {
            this.statement.setFetchDirection(i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getFetchDirection() {
        try {
            return this.statement.getFetchDirection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setFetchSize(int i) {
        try {
            this.statement.setFetchSize(i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getFetchSize() {
        try {
            return this.statement.getFetchSize();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getResultSetConcurrency() {
        try {
            return this.statement.getResultSetConcurrency();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getResultSetType() {
        try {
            return this.statement.getResultSetType();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addBatch(String s) {
        try {
            this.statement.addBatch(s);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void clearBatch() {
        try {
            this.statement.clearBatch();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int[] executeBatch() {
        try {
            return this.statement.executeBatch();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            return this.statement.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean getMoreResults(int i) {
        try {
            return this.statement.getMoreResults(i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ResultSet getGeneratedKeys() {
        try {
            return this.statement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int executeUpdate(String s, int i) {
        try {
            return this.statement.executeUpdate(s, i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int executeUpdate(String s, int[] ints) {
        try {
            return this.statement.executeUpdate(s, ints);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int executeUpdate(String s, String[] strings) {
        try {
            return this.statement.executeUpdate(s, strings);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean execute(String s, int i) {
        try {
            return this.statement.execute(s, i);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean execute(String s, int[] ints) {
        try {
            return this.statement.execute(s, ints);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean execute(String s, String[] strings) {
        try {
            return this.statement.execute(s, strings);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int getResultSetHoldability() {
        try {
            return this.statement.getResultSetHoldability();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isClosed() {
        try {
            return this.statement.isClosed();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setPoolable(boolean b) {
        try {
            this.statement.setPoolable(b);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isPoolable() {
        try {
            return this.statement.isPoolable();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void closeOnCompletion() {
        try {
            this.statement.closeOnCompletion();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isCloseOnCompletion() {
        try {
            return this.statement.isCloseOnCompletion();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        try {
            return this.statement.unwrap(aClass);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) {
        try {
            return this.statement.isWrapperFor(aClass);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
