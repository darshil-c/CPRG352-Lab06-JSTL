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
        // get the action paramater
        String action = request.getParameter("action");

        // check if action is null
        if (action != null) {
            // if it isn't null and equals to logout, invalidate the useers session and redirect them to register again
            if (action.equals("logout")) {
                session.invalidate();
                request.setAttribute("message", "You have been logged out..");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        }
        // if the user doesn't have a username in their session send them to register else send them to the shopping list
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

        // get all the parameters from the shopping list JSP
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String item = request.getParameter("item");
        String listItem = request.getParameter("listitem");

        // ArrayList to store shopping list items
        ArrayList<String> list = (ArrayList<String>) session.getAttribute("list");

        // if action is null redirect user to shoppinglist
        if (action != null) {
            // action to register
            if (action.equals("register")) {
                // if username is not null or empty register the user
                if (username != null && !username.equals("")) {
                    session.setAttribute("username", username);
                    response.sendRedirect("ShoppingList");
                    return;
                    // if the user name is invalid let the user know and send them to register again
                } else {
                    request.setAttribute("message", "Please enter a valid username.");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    return;
                }
                // action to add item to list
            } else if (action.equals("add") && !item.equals("")) {
                // if the list already exists add the item
                if (list != null) {
                    list.add(item);
                    session.setAttribute("list", list);
                    response.sendRedirect("ShoppingList");
                    return;
                    // if the list doesn't exist make a new one and add it to user session
                } else {
                    list = new ArrayList<>();
                    list.add(item);
                    session.setAttribute("list", list);
                    response.sendRedirect("ShoppingList");
                    return;
                }
                // action to delete item from the list
            } else if (action.equals("delete") && !listItem.equals("") && listItem != null) {
                // loop to cycle through ArrayList
                for (int i = 0; i < list.size(); i++) {
                    // check equality then remove item
                    if (listItem.equals(list.get(i))) {
                        list.remove(i);
                    }
                }
                response.sendRedirect("ShoppingList");
                return;
            } else {
                response.sendRedirect("ShoppingList");
                return;
            }
        } else {
            response.sendRedirect("ShoppingList");
            return;
        }

    }

}
