/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chaudhari
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get session
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("logout")) {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        }
        if (session.getAttribute("username") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get session
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String item = request.getParameter("item");
        
        ArrayList<String> list = (ArrayList<String>) session.getAttribute("list");
        
        if (action != null) {
            if (action.equals("register")) {
                if (username != null && !username.equals("")) {
                    session.setAttribute("username", username);
                    response.sendRedirect("ShoppingList");
                    return;
                } else {
                     request.setAttribute("message", "Please enter a valid username.");
                     getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                     return;
                }
            }
            else if (action.equals("add") && !item.equals("")) {
                if (list != null) {
                    list.add(item);
                    session.setAttribute("list", list);
                    response.sendRedirect("ShoppingList");
                } else {
                    list = new ArrayList<>();
                    list.add(item);
                    session.setAttribute("list", list);
                    response.sendRedirect("ShoppingList");
                    return;
                }
            }
        }
        
//        if (username != null && !username.equals("")) {
//            session.setAttribute("username", username);
//            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
//        } else {
//            request.setAttribute("message", "Please enter a valid username.");
//            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
//        }
        
        
    }

}
