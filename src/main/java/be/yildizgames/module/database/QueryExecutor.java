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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grégory Van den Borre
 */
public class QueryExecutor {

    private final DataBaseConnectionProvider provider;

    public QueryExecutor(DataBaseConnectionProvider provider) {
        super();
        this.provider = provider;
    }

    public final void createTableIfNotExists(TableSchema...schemas) {
        for(TableSchema  schema : schemas) {
            try (var c = this.provider.getConnection(); var pstmt = c.prepareStatement(createTableQuery(schema))) {
                pstmt.execute();
                c.commit();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    static String createTableQuery(TableSchema schema) {
        String query = "CREATE CACHED TABLE IF NOT EXISTS " + schema.getTableName() + " (";
        TableSchemaColumn id = schema.getId();
        if(id != null) {
            query = query + id.getTitle() + " " + id.getType()  + (id.getSize() == -1 ? "" : "(" + id.getSize() + ")") + (schema.isGeneratedId()? " IDENTITY" : "") + " PRIMARY KEY" + (schema.getColumns().length == 0 ? "" : ",");
        }
        query = query + Arrays
                .stream(schema.getColumns())
                .map(c -> c.getTitle() + " " + c.getType() + (c.getSize() == -1 ? "" : "(" + c.getSize() + ")") + (c.isNullable() ? "" : " NOT NULL"))
                .collect(Collectors.joining(","));
        query = query + ");";
        return query;
    }

    public final void dropTables(String... tables) {
        try (var c = this.provider.getConnection(); var dropStmt = c.createStatement()) {
            for(String table : tables) {
                dropStmt.execute("DROP TABLE " + table);
            }
            c.commit();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> List<T> select(String query, RowMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        try (var c = this.provider.getConnection(); var pstmt = WrappedPreparedStatement.create(c.prepareStatement(query)); var resultSet = WrappedResultSet.wrap(pstmt.executeQuery())) {
            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
