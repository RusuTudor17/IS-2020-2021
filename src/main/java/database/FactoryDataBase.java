package database;

public class FactoryDataBase {
    public ConnectionWrapperJDBC getConnectionWrapper(boolean boolean_test_db) {

        if (boolean_test_db) {
            return new ConnectionWrapperJDBC(Constants.Database.TESTARE);
        }
        else
            return new ConnectionWrapperJDBC(Constants.Database.PRODUCTIE);

    }
}
