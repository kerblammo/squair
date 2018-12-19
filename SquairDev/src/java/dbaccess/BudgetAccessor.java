/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Pete
 */
public class BudgetAccessor {
    
    private static Connection conn;
    private static PreparedStatement spendBudgetStatement;
    
    
    //no need to instantiate
    private BudgetAccessor(){
    }
    
    private static void init() throws SQLException {
        if (conn == null){
            conn = ConnectionManager.getConnection();
            spendBudgetStatement = conn.prepareStatement("UPDATE wallet SET Value = Value - 1 WHERE UserId = ? AND CanvasId = ? AND Value > 0");
        }
    }
    
    public static boolean spendBudget(int userId, int canvasId) throws SQLException {
        init();
        spendBudgetStatement.setInt(1, userId);
        spendBudgetStatement.setInt(2, canvasId);
        int rowCount = spendBudgetStatement.executeUpdate();
        return rowCount == 1;
        
    }
    
}
