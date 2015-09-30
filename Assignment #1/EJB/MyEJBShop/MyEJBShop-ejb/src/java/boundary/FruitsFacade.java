/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Fruits;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lorenzo Addazi
 */
@Stateless
public class FruitsFacade extends AbstractFacade<Fruits> implements FruitsFacadeLocal {
    @PersistenceContext(unitName = "MyEJBShop-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FruitsFacade() {
        super(Fruits.class);
    }
    
    /* Retrieve Fruit instance from its name */
    public Fruits findByName(String name){
        /* Build a parametric query */
        Query query = em.createNamedQuery("Fruits.findByNames");
        /* Set "name" parameter */
        query.setParameter("name", name);
        try {
            /* Return the corresponding fruits table row */
            return (Fruits) query.getSingleResult(); 
        } catch (NoResultException e){
            /* Return null if no query result is produced */
            return null; 
        }
    }
    
    /* Retrieve Fruits instance from its ID */
    public Fruits findById(String id){
        /* Build a parametric query */
        Query query = em.createNamedQuery("Fruits.findById"); 
        /* Set "id" parameter */ 
        query.setParameter("id", id);
        /* Return back the correspoonding Fruit instance */
        try {
            /* Return the corresponding fruits table row */
            return (Fruits)query.getSingleResult(); 
        } catch (NoResultException e){
            /* Return null if no query result is produced */
            return null; 
        }
    }
    
    
    /* Retrieve all Fruit instances (Table rows) */
    public Collection getFruitsList(){
        // Final fruit list
        ArrayList fruitsList = new ArrayList();
        try {
            // Fruit table instances iterator 
            Iterator i = findAll().iterator();
            // Iterate over the instances
            while(i.hasNext()){
                // Get the current Fruit instance
                Fruits currentFruit = (Fruits) i.next();
                // Add its description to the list
                fruitsList.add(currentFruit.getId() + "," + 
                               currentFruit.getName() + "," + 
                               currentFruit.getPrice() + "," + 
                               currentFruit.getQuantity());
            }
        } catch (Exception e) {
            // There has been some problem 
            fruitsList.add(e.toString()); 
        }
        // Return back the fruit list
        return fruitsList; 
    }
    
}
