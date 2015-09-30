package session;

import entities.Fruits;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;

/**
 *
 * @author Lorenzo Addazi
 */
@Stateful
public class ShoppingCartBean implements ShoppingCartBeanLocal {

    /* Shopping cart */
    private final Map<String, Fruits> shoppingCart; 
    
    /* Class Contructor */
    public ShoppingCartBean(){
        this.shoppingCart = new HashMap<>();
    }
    
    @Override
    public Fruits findItemById(String itemId){
        /* Return the corresponding item if present, null otherwise */
        return this.shoppingCart.get(itemId);
    }
    
    @Override
    public boolean addItem(Fruits fruitsItem) {
        /* Replace the element if already existing, otherwise add it to the cart */
        Fruits shoppingCartItem = this.findItemById(fruitsItem.getId());
        /* This item has already been inserted into the shopping list */
        if(shoppingCartItem != null){
            /* Increase its quantity */
            fruitsItem.setQuantity(fruitsItem.getQuantity() + shoppingCartItem.getQuantity());
        }
        /* Update the Shopping Cart */
        this.shoppingCart.put(fruitsItem.getId(), fruitsItem); 
        /* Operation Completed */
        return true; 
    }

    @Override
    public boolean deleteItem(Fruits fruitsItem) {
        /* Decrease the item quantity if greater than the given quantity, delete it otherwise */
        Fruits shoppingCartItem = this.findItemById(fruitsItem.getId());
        /* This item has more than the given unity on the shopping cart */
        if(shoppingCartItem.getQuantity() > fruitsItem.getQuantity()){
            fruitsItem.setQuantity(shoppingCartItem.getQuantity() - fruitsItem.getQuantity());
            this.shoppingCart.put(fruitsItem.getId(), fruitsItem);
        } else {
            /* There are equal instances, the item can be removed */
            this.shoppingCart.remove(fruitsItem.getId());
        }
        /* Operation Completed */
        return true; 
    }

    @Override
    public double getTotalPrice() {
        /* Shopping cart total price */
        double totalPrice = 0.0; 
        /* Iterate over each item, evaluate its total price, and add it to the total amount */
        totalPrice = this.shoppingCart.values().stream()
                .map((fruit) -> fruit.getPrice() * fruit.getQuantity())
                .reduce(totalPrice, (accumulator, _item) -> accumulator + _item); 
        /* Return back the total amount */
        return totalPrice; 
    }

    @Override
    public List<Fruits> getCartContent() {
        /* Return the cart content */
        return new ArrayList<>(this.shoppingCart.values()); 
    }

    @Override
    public boolean emptyCartContent() {
        /* Clear the shopping card content */
        this.shoppingCart.clear();
        /* Operation Completed */
        return true; 
    }
    
}
