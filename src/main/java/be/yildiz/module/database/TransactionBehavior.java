package be.yildiz.module.database;

/**
 * @author Grégory Van den Borre
 */
@FunctionalInterface
public interface TransactionBehavior {

    void execute() throws Exception;
}
