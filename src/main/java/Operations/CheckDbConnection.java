/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Operations;

/**
 *
 * @author Minaz
 */
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

