
package model;

import java.sql.Timestamp;



/**
 *
 * @author Pete
 */
public class Move {
    
    private int canvasId;
    private int xPosition;
    private int yPosition;
    private String HexValue;
    private Timestamp timestamp;

    public int getCanvasId() {
        return canvasId;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public String getHexValue() {
        return HexValue;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Move(int canvasId, int xPosition, int yPosition, String HexValue, Timestamp timestamp) {
        this.canvasId = canvasId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.HexValue = HexValue;
        this.timestamp = timestamp;
    }
    
    
    
}
