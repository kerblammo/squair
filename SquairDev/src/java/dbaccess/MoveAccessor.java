package dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Move;

/**
 * Accessor for recent moves from the DrawingHistory table
 *
 * @author Pete
 */
public class MoveAccessor {

    private static Connection conn;
    private static PreparedStatement selectByIdAndTimeStatement;

    //private as there is no need to instantiate
    private MoveAccessor() {
    }

    private static void init() throws SQLException {
        if (conn == null) {
            conn = ConnectionManager.getConnection();
            selectByIdAndTimeStatement = conn.prepareStatement("SELECT * FROM drawinghistory WHERE CanvasId = ? AND timestamp > ?");
        }
    }

    public static List<Move> getMoves(int canvasId, Timestamp time) throws SQLException {
        init();
        ArrayList<Move> moves = new ArrayList<>();
        //bind parameters
        selectByIdAndTimeStatement.setInt(1, canvasId);
        selectByIdAndTimeStatement.setTimestamp(2, time);
        
        //get results
        ResultSet rs = selectByIdAndTimeStatement.executeQuery();
        
        
        while (rs.next()){
            int xPos = rs.getInt("XPOSITION");
            int yPos = rs.getInt("YPOSITION");
            String hex = rs.getString("HEXVALUE");
            Timestamp stamp = rs.getTimestamp("TIMESTAMP");
            
            Move move = new Move(canvasId, xPos, yPos, hex, stamp);
            moves.add(move);
        }
        return moves;
        
    }

}
