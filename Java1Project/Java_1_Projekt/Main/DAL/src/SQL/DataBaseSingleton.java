package SQL;


import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class DataBaseSingleton {

    private static final String SERVER_NAME = "SQLEXPRESS";
    private static final String DATABASE_NAME = "Java_1_DB";
    private static final String USER = "sa";
    private static final String PASSWORD = "SQL";

    /*private static final String SERVER_NAME = "den1.mssql4.gear.host";
    private static final String DATABASE_NAME = "JAVASTART";
    private static final String USER = "javastart";
    private static final String PASSWORD = "Sc1M4-7WyGw!";*/

    private DataBaseSingleton() {}

    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }
    private static DataSource createInstance() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

}
