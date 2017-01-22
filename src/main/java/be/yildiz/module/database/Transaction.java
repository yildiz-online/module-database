package be.yildiz.module.database;

import be.yildiz.common.log.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public final class Transaction {

    private final DataBaseConnectionProvider connectionProvider;

    public Transaction(DataBaseConnectionProvider p) {
        this.connectionProvider = p;
    }

    public void execute(TransactionBehavior b) {
        try(Connection c = connectionProvider.getConnection()) {
            c.setAutoCommit(false);
            try {
                b.execute(c);
            } catch (Exception e) {
                c.rollback();
                Logger.error(e);
            }
            c.commit();
            c.setAutoCommit(true);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
}
