
package model;

/**
 * This class represents a Canvas entity in the database
 * @author Pete
 */
public class Canvas {
    
    private int id;
    private String name;
    private int width;
    private int height;
    private int maxBudget;
    private int budgetRefill;
    private String[][] pixels;

    public String[][] getPixels() {
        return pixels;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxBudget() {
        return maxBudget;
    }

    public int getBudgetRefill() {
        return budgetRefill;
    }

    public Canvas(int id, String name, int width, int height, int maxBudget, int budgetRefill, String[][] pixels) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.maxBudget = maxBudget;
        this.budgetRefill = budgetRefill;
        this.pixels = pixels;
    }

    
    
    
    
    
}
