/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Fruits;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Lorenzo Addazi
 */
@Local
public interface FruitsFacadeLocal {

    void create(Fruits fruits);

    void edit(Fruits fruits);

    void remove(Fruits fruits);

    Fruits find(Object id);

    List<Fruits> findAll();

    List<Fruits> findRange(int[] range);

    int count();
    
    /** Additional Code
     * @param name
     * @return  **/
    Fruits findByName(String name);  
    Collection getFruitsList(); 
    Fruits findById(String id); 
    
    
}
