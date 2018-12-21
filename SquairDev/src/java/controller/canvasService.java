package controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import dbaccess.BudgetAccessor;
import dbaccess.CanvasAccessor;
import dbaccess.MoveAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Canvas;
import model.Move;

/**
 *
 * @author Pete
 */
@WebServlet(name = "canvasService", urlPatterns = {"/canvasservice/load/*", "/canvasservice/refresh/*", "/canvasservice/reset", "/canvasservice"})
public class canvasService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            //build gson object and get path info
            Gson g = new Gson();
            String uri = request.getRequestURI();
            String pi = request.getPathInfo();
            if (uri.contains("load")) {
                //get a single canvas by its id
                if (pi != null) {
                    //pi should match pattern "/####"
                    int id = Integer.parseInt(pi.substring(1));
                    Canvas canvas = CanvasAccessor.getCanvas(id);
                    out.print(g.toJson(canvas));
                }
            } else if (uri.contains("refresh")) {
                //get canvas id and date
                String[] params = pi.split("/");
                int id = Integer.parseInt(params[1]);
                Timestamp time = new Timestamp(Long.parseLong(params[2]));
                List<Move> moves = MoveAccessor.getMoves(id, time);
                out.print(g.toJson(moves));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            //build gson object and get path info
            Gson g = new Gson();
            String uri = request.getRequestURI();
            String pi = request.getPathInfo();
            
            if (uri.contains("reset")){
                resetCanvas(request, g, out);
            } else {
                paintCanvas(request, g, out);
            }
            
            
        }
    }
    
    private void resetCanvas(HttpServletRequest request, Gson g, final PrintWriter out) throws JsonSyntaxException, IOException {
        //build canvas from JSON
        Scanner sc = new Scanner(request.getReader());
        String jsonData = sc.nextLine();
        Canvas canvas = g.fromJson(jsonData, Canvas.class);
        
        
        //reset the canvas
        try {
            boolean success = CanvasAccessor.resetCanvas(canvas);
            out.println(success);
        } catch (SQLException ex){
            System.err.println(ex.getMessage());
            out.println("ERROR: " + ex.getMessage());
        }
        
    }

    private void paintCanvas(HttpServletRequest request, Gson g, final PrintWriter out) throws JsonSyntaxException, IOException {
        //build Move object from JSON
        Scanner sc = new Scanner(request.getReader());
        String jsonData = sc.nextLine();
        Move move = g.fromJson(jsonData, Move.class);
        
        //get userId and permission level
        JsonElement jsonElement = new JsonParser().parse(jsonData);
        JsonObject jobject = jsonElement.getAsJsonObject();
        int userId = jobject.get("userId").getAsInt();
        int permission = jobject.get("permission").getAsInt();
        //out.println("Permission Level: " + permission);
        
        //if user does not have super privileges, they must spend their budget
        boolean budgetConfirmed = true;
        if (permission == 0){
            budgetConfirmed = spendBudget(userId, move.getCanvasId());
        }
        
        //stop here if something failed when spending budget
        if (budgetConfirmed){
            //must update both Drawing table and DrawingHistory table
            try {
                boolean success = CanvasAccessor.paintCanvas(move);
                out.println(success);
            } catch (SQLException ex){
                System.err.println(ex.getMessage());
                out.println("ERROR: " + ex.getMessage());
            }
            
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Alter a user's wallet values 
     * @param userId the user spending budget
     * @param canvasId the canvas being spent on
     */
    private boolean spendBudget(int userId, int canvasId) {
        boolean valid;
        try {
            valid = BudgetAccessor.spendBudget(userId, canvasId);
        } catch (SQLException ex){
            System.err.println(ex.getMessage());
            valid = false;
        }
        return valid;
    }

}
