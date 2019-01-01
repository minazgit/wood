



package Operations;

import java.sql.*;

public class CheckDbConnection {
    public static boolean isDbConnected(Connection con) {
    final String CHECK_SQL_QUERY = "SELECT 1";
    boolean isConnected = false;
    try {
        final PreparedStatement statement = con.prepareStatement(CHECK_SQL_QUERY);
        isConnected = true;
    } catch (Exception  e) {
        // handle SQL error here!
    }
    return isConnected;
    }
}

