package be.yildiz.module.database;

import java.sql.Connection;

/**
 * @author Grégory Van den Borre
 */
@FunctionalInterface
public interface TransactionBehavior {

    void execute(Connection c) throws Exception;
}
