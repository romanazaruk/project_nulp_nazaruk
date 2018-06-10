/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Користувач
 */

 

 public class Main {

  public static void Main(String[] args) {
    
    Kitchen loft = new Kitchen(); 
    
    Kitchen hiTech = new Kitchen("Hi_Tech", "gray", 4, 50.5,4); 

    Kitchen provance = new Kitchen("Provance", "yellow", 1, 15.5,1 ); 
     
    System.out.println(loft.toString()); 
    
    System.out.println(hiTech.toString()); 
    
    System.out.println(provance.toString()); 
    
    loft.resetValues("Loft", "red", 3, 45,2); 
    
    System.out.println(loft.toString());

    loft.printSum(); 
  
    hiTech.printSum(); 
 
    provance.printSum(); 
 
    hiTech.setNumberOfDrawers(5); 
 
    Kitchen.printStaticSum(); 
    } 
  }
    

