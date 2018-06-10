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
public class Kitchen {

  private String name = "no name";
  
  private String color;
    
    private int numberOfWindows;

    private double area;

    private int numberOfDrawers;
    

    public static int totalNumberOfDrawers = 0;

    
    public Kitchen() {
      
    }


    public Kitchen(String name, String color , int numberOfWindows, double area) {

        setName(name);

        setColor(color);
        
        setNumberOfWindows(numberOfWindows);

        setArea(area);
        
    }
    


  public Kitchen(String name, String color , int numberOfWindows, double area, int numberOfDrawers)  {

    setName(name);

        setColor(color);
        
        setNumberOfWindows(numberOfWindows);

        setArea(area);
        
        setNumberOfDrawers(numberOfDrawers);
        
     
  }

  

  public String toString() {

    return " Info about kitchen: \nname: " + getName() 

        + "\ncolor: " + getColor() 

        + "\nnumberOfWindows: " + getNumberOfWindows() 

        + "\narea: " + getArea() 

        + "\nnumberOfDrawers: " + getNumberOfDrawers()

        + "\n";  

  }



  public static void printStaticSum() {

    System.out.print(" Number of drawers from all kitchens is   " + totalNumberOfDrawers );

  }

  

  public void printSum() {

    System.out.println("Number of drawers of  " + getName() + " kitchen  is " + getNumberOfDrawers() );

  }

  

  public void resetValues(String name, String color , int numberOfWindows, double area, int numberOfDrawers)  {

    setName(name);

        setColor(color);
        
        setNumberOfWindows(numberOfWindows);

        setArea(area);

        setNumberOfDrawers(numberOfDrawers);

        
  }

   


   public String getName() {

          return name;

      }



      public void setName(String name) {

          this.name = name;

      }
      
      public double getArea() {
        return area ;
      }
      
      public void setArea(double area) {
        this.area = area;
      }


     public String getColor() {
       return color;
      }
     
     public void setColor(String color) {
       this.color = color ;
     }
      
      public int getNumberOfWindows() {

      return numberOfWindows;

    }



    public void setNumberOfWindows(int numberOfWindows) {

      this.numberOfWindows = numberOfWindows;

    }

    

    public double getNumberOfDrawers() {

      return numberOfDrawers;

    }

    

    public void setNumberOfDrawers(int numberOfDrawers ) {

      totalNumberOfDrawers -= this.numberOfDrawers;

      totalNumberOfDrawers += numberOfDrawers;

      this.numberOfDrawers = numberOfDrawers;

    }

  }
