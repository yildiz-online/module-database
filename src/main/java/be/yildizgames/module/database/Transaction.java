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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A transactional database operation.
 *
 * @author Grégory Van den Borre
 */
public final class Transaction {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Connection provider.
     */
    private final DataBaseConnectionProvider connectionProvider;

    public Transaction(DataBaseConnectionProvider provider) {
        this.connectionProvider = provider;
    }

    public void execute(TransactionBehavior behavior) {
        try(Connection c = connectionProvider.getConnection()) {
            logger.debug("Starting transaction");
            c.setAutoCommit(false);
            try {
                behavior.execute(c);
            } catch (Exception e) {
                c.rollback();
                logger.error("Error in transaction", e);
            }
            c.commit();
            c.setAutoCommit(true);
            logger.debug("Complete transaction");
        } catch (SQLException e) {
            logger.error("Error in transaction", e);
        }
    }
}
