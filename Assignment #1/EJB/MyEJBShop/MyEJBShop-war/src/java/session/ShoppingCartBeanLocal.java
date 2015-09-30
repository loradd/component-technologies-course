/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Fruits;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lorenzo Addazi
 */
@Local
public interface ShoppingCartBeanLocal {
    
    /* Get Item from its id (primary key) */
    Fruits findItemById(String itemId);
    /* Add item to cart */
    boolean addItem(Fruits fruitsItem);
    /* Delete item from cart */
    boolean deleteItem(Fruits fruitsItem); 
    /* Get total price */
    double getTotalPrice();
    /* Get cart content */
    List<Fruits> getCartContent();
    /* Empty cart content */
    boolean emptyCartContent();
    
}
