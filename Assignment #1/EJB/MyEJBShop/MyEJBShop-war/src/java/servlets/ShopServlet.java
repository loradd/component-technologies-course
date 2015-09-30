/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import boundary.FruitsFacadeLocal;
import entities.Fruits;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ShoppingCartBean;
import session.ShoppingCartBeanLocal;

/**
 * Checkout : Transaction completed! Thank you!
 * Reset : Reset Shopping Cart 
 * Add : Update Shopping Cart or Message
 * Delete : Update Shopping Cart or Message
 * /

/**
 *
 * @author Lorenzo Addazi
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/ShopServlet"})
public class ShopServlet extends HttpServlet {
    
    private static final String SHOPPING_CART_BEAN_KEY = "SHOPPING_CART_BEAN_KEY"; 
    
    @EJB
    private FruitsFacadeLocal fruitsFacade;
    
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
        
        StringBuffer requestURL = request.getRequestURL();
        if (request.getQueryString() != null) {
            requestURL.append("?").append(request.getQueryString());
        }
        String requestParametersString  = requestURL.toString().substring(requestURL.toString().lastIndexOf("?") + 1);
        if(!requestParametersString.isEmpty()){
            String[] requestParameters = requestParametersString.split("&");
            if(requestParameters.length > 0){
                String[] firstParameterContent = requestParameters[0].split("=");
                if("action".equals(firstParameterContent[0])){
                    /* Switch with respect to the operation type */
                    switch(firstParameterContent[1]){
                        case "confirm" :
                            Iterator shoppingCartIterator = this.getShoppingCart(request).getCartContent().iterator();
                            while(shoppingCartIterator.hasNext()){
                                /* Current Shopping Cart Item */
                                Fruits currentShoppingCartItem = (Fruits) shoppingCartIterator.next();
                                /* Corresponding Stored Item */
                                Fruits persistentShoppingCartItem = this.fruitsFacade.findById(currentShoppingCartItem.getId());
                                /* Update item quantity */
                                persistentShoppingCartItem.setQuantity(persistentShoppingCartItem.getQuantity() - currentShoppingCartItem.getQuantity());
                                /* Save Changes */
                                this.fruitsFacade.edit(persistentShoppingCartItem);
                                /* Reset Shopping Cart Content */
                                this.getShoppingCart(request).emptyCartContent(); 
                            }
                            response.sendRedirect("/MyEJBShop-war/ShopServlet");
                            break;
                        case "reset" :
                            this.getShoppingCart(request).emptyCartContent(); 
                            response.sendRedirect("/MyEJBShop-war/ShopServlet");
                            break;
                        case "add" :
                            if(requestParameters.length == 2){
                                String[] secondParameterContent = requestParameters[1].split("=");
                                if("id".equals(secondParameterContent[0])){
                                    /* Retrieve Item from the database */
                                    Fruits selectedFruitsInstance = this.fruitsFacade.findById(secondParameterContent[1]);
                                    /* Set quantity for an additional instance */
                                    selectedFruitsInstance.setQuantity(1);
                                    /* Add item to the shopping cart */
                                    this.getShoppingCart(request).addItem(selectedFruitsInstance); 
                                }
                            }
                            response.sendRedirect("/MyEJBShop-war/ShopServlet");
                            break;
                        case "remove" : 
                            if(requestParameters.length == 2){
                                String[] secondParameterContent = requestParameters[1].split("=");
                                if("id".equals(secondParameterContent[0])){
                                    /* Retrieve Item from the database */
                                    Fruits selectedFruitsInstance = this.fruitsFacade.findById(secondParameterContent[1]);
                                    /* Set quantity for one less instance */
                                    selectedFruitsInstance.setQuantity(1);
                                    /* Remove Item from the Shopping Cart */
                                    this.getShoppingCart(request).deleteItem(selectedFruitsInstance);
                                }
                            }
                            response.sendRedirect("/MyEJBShop-war/ShopServlet");
                            break;
                        default : /* Do Nothing*/
                    }
                }
            }
        }
        
        /* Shopping Cart */
        //ShoppingCart shoppingCart = thisgetShoppingCart(request); 
        /* Servlet Page */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
                /* Head */
                out.println("<head>");
                    /* Page Title */
                    out.println("<title>The Fruit Shop</title>");
                    /* Cascade Style Sheets Import */
                    out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>"); // Bootstrap CSS
                    out.println("<style type='text/css'>");
                        out.println("li.list-group-item {font-size: 11px; }");
                        out.println("li.list-group-item div a span.glyphicon-plus{ color : green; }");
                        out.println("li.list-group-item div a span.glyphicon-minus{ color : red; }");
                    out.println("</style>");
                out.println("</head>");
                /* Body */
                out.println("<body>");
                    /* Container */
                    out.println("<div class='container'>");
                        /* Shop Banner */
                        out.println("<div class='page-header'>");
                            out.println("<h2>The Fruit Shop</h2>");
                        out.println("</div>");
                        out.println("<div class='row'>");
                            /* Available Items */
                            out.println("<div class='col-xs-12 col-sm-12 col-md-8 col-lg-8'>");
                                out.println("<h4>Available Items</h4>");
                                /* Item List */
                                out.println("<ul class='list-group'>");
                                /* Fuits Instances Iterator */
                                Iterator i = fruitsFacade.getFruitsList().iterator();
                                /* First Element : Check if the list is not empty */
                                if(i.hasNext()){
                                    /* Insert the elements */
                                    while(i.hasNext()){
                                        String currentFruit = (String)i.next();
                                        String[] currentFruitInformation = currentFruit.split(",");
                                        String currentFruitId = currentFruitInformation[0]; 
                                        String currentFruitName = currentFruitInformation[1]; 
                                        Double currentFruitPrice = Double.parseDouble(currentFruitInformation[2]);
                                        Integer currentFruitQuantity = Integer.parseInt(currentFruitInformation[3]);
                                        if(currentFruitQuantity > 0){
                                            out.println("<li class='list-group-item'>");
                                                out.println("<div class='row'>");
                                                    out.println("<div style='float: left; margin-left: 10px;'>");
                                                        out.println("<h5>" + currentFruitName + "<br><small> In Stock : " + currentFruitQuantity + "</small></h5>");
                                                    out.println("</div>"); // <div style='float: left; margin-left: 10px'>
                                                    out.println("<div style='float: right; margin-right: 10px;'>");
                                                        out.println("<div class='btn-group' role='group' aria-label='true'>");
                                                            out.println("<a href='?action=add&id=" + currentFruitId + "'>");
                                                                out.println("<button type='button' class='btn btn-success' style='margin-top: 9px; border: 1px solid black;'>");
                                                                    out.println("<span style='color:black;' class='glyphicon glyphicon-plus' aria-hidden='true'></span>");
                                                                out.println(); // <button type='button' class='btn btn-danger' style='margin-top: 9px; border: 1px solid black;'>
                                                            out.println("</a>"); // <a href='?action=add&id=ID'>
                                                        out.println("</div>"); // <div class='btn-group' role='group' aria-label='true'>
                                                    out.println(); // <div style='float: right; margin-right: 1opx;'>
                                                out.println("</div>"); // <div class='row'>
                                            out.println("</li>"); // <li class='list-group-item'>
                                        }
                                    }
                                } else {
                                    /* The List is Empty */
                                    out.println("<h5>No Available Items.</h5>");
                                }
                                out.println("</ul>"); // <ul class='list-group'>
                            out.println("</div>"); // <div class='col-xs-3 col-sm-3 col-md-3 col-lg-3'>
                            /* Shopping Cart */
                            out.println("<div class='col-xs-12 col-sm-12 col-md-4 col-lg-4'>");
                                out.println("<h4>Shopping Cart</h4>");
                                Iterator shoppingCartFruitsIterator = this.getShoppingCart(request).getCartContent().iterator();
                                /* First Element : Check if the shopping cart is empty */
                                if(shoppingCartFruitsIterator.hasNext()){
                                    /* Insert Shopping Cart Items */
                                    out.println("<ul class='list-group'>");
                                    while(shoppingCartFruitsIterator.hasNext()){
                                        /* Shopping Cart List Item */
                                        Fruits currentShoppingCartFruit = (Fruits)shoppingCartFruitsIterator.next();
                                        String currentShoppingCartFruitId = currentShoppingCartFruit.getId();
                                        String currentShoppingCartFruitName = currentShoppingCartFruit.getName();
                                        Double currentShoppingCartFruitPrice = currentShoppingCartFruit.getPrice(); 
                                        Integer currentShoppingCartFruitQuantity = currentShoppingCartFruit.getQuantity(); 
                                        out.println("<li class='list-group-item'>");
                                            out.println("<div class='row'>");
                                                out.println("<div style='float:left; margin-left: 10px;'>");
                                                    out.println("<h5>" + currentShoppingCartFruitName + " <br><small>Selected: " + currentShoppingCartFruitQuantity + "</small></h5>");
                                                out.println("</div>");
                                                out.println("<div style='float:right; margin-right: 10px;'>");
                                                    out.println("<div class='btn-group' role='group' aria-label='true'>");
                                                        out.println("<a href='?action=remove&id=" + currentShoppingCartFruitId + "'><button type='button' class='btn btn-danger' style='margin-top: 9px; border: 1px solid black;'><span style='color:black;' class='glyphicon glyphicon-minus' aria-hidden='true'></span></button></a>");
                                                    out.println("</div>"); // <div class='btn-group' role='group' aria-label='true'>
                                                out.println("</div>"); // <div style='float: right; margin-right: 10px;'>
                                            out.println("</div>"); // <div class='row'>
                                        out.println("</li>"); // <li class='list-group-item'>
                                    }
                                    out.println("</ul"); // <ul class='list-group'>
                                } else {
                                    out.println("<h5>No Selected Items.</h5>");
                                }
                            out.println("</div>"); // <div class='col-xs-3 col-sm-3 col-md-3 col-lg-3'>
                        out.println("</div>"); // <div class='row'>
                        /* Shopping Cart Checkout/Reset Buttons */
                        out.println("<div class='row'>");
                            out.println("<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>");
                                out.println("<div style='float: right;' class='btn-group' role='group' aria-label='true'>");
                                    out.println("<a href='?action=reset'><button style='border: 1px solid black; color: black;' type='button' class='btn btn-danger'>Reset</button></a>");
                                    out.println("<a href='?action=confirm'><button style='color: black; border: 1px solid black;' type='button' class='btn btn-success'>Checkout</button></a>");
                                out.println("</div>"); // <div class='btn-group' role='group' aria-label='true'>
                            out.println("</div>"); // <div class='col-xs-12 col-sm-12 col-md-4 col-lg-4'>
                        out.println("</div>"); // <div class='row'>
                    out.println("</div>"); // <div class='container'>
                out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    /* Get a Shooping Cart instance from the session, create it if it does not exist */
    private ShoppingCartBeanLocal getShoppingCart(HttpServletRequest request) throws ServletException{
        HttpSession httpSession = request.getSession(); 
        ShoppingCartBeanLocal shoppingCart = (ShoppingCartBeanLocal) httpSession.getAttribute(SHOPPING_CART_BEAN_KEY);
        if(shoppingCart == null){
            try {
                InitialContext initialContext = new InitialContext();
                shoppingCart = (ShoppingCartBeanLocal) initialContext.lookup("java:module/ShoppingCartBean");
                httpSession.setAttribute(SHOPPING_CART_BEAN_KEY, shoppingCart);
            } catch(NamingException e){
                throw new ServletException(e); 
            }
        }
        return shoppingCart; 
    }
    
}
