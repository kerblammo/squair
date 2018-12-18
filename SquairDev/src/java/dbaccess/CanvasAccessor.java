
package dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Canvas;

/**
 * Accessor for Canvases in the database
 * @author Pete
 */
public class CanvasAccessor {
    
    private static Connection conn;
    private static PreparedStatement selectByIdStatement;
    private static PreparedStatement selectDrawingByIdStatement;

    
    
    //private means do not instantiate
    private CanvasAccessor(){
    }
    
    
    private static void init() throws SQLException {
        if (conn == null){
            conn = ConnectionManager.getConnection();
            selectByIdStatement = conn.prepareStatement("SELECT * FROM canvas WHERE Id = ?");
            selectDrawingByIdStatement = conn.prepareStatement("SELECT * FROM drawing WHERE CanvasId = ? ORDER BY YPosition, XPosition");
        }
        
        
    }
    
    public static Canvas getCanvas(int id) throws SQLException {
        init();
        
        //perform canvas query
        selectByIdStatement.setInt(1, id);
        ResultSet crs = selectByIdStatement.executeQuery();
        crs.next();
        
        //get parameters of canvas
        String name = crs.getString("NAME");
        int width = crs.getInt("WIDTH");
        int height = crs.getInt("HEIGHT");
        int maxBudget = crs.getInt("MAXBUDGET");
        int budgetRefill = crs.getInt("BUDGETREFILL");
        
        //perform drawing query
        selectDrawingByIdStatement.setInt(1, id);
        ResultSet drs = selectDrawingByIdStatement.executeQuery();
        
        //get the collection of coloured pixels
        String[][] pixels = getStringArrayFromResults(drs, width, height);
        
        //build canvas
        Canvas canvas = new Canvas(id, name, width, height, maxBudget, budgetRefill, pixels);
        
        return canvas;
        
        
        
        
    }
    
    //Turns a resultset from the drawing table into an organized array of coloured pixels 
    private static String[][] getStringArrayFromResults(ResultSet rs, int width, int height) throws SQLException{
        String[][] results = new String[height][width];
        rs.next();
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                results[y][x] = rs.getString("HEXVALUE");
                rs.next();
            }
        }
        return results;
        
    }
    
    
}