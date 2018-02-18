package nazaruk;

public class main {

	public static void main(String[] args) {
		
		Kitchen Loft = new Kitchen(); 
		
		Kitchen Hi_Tech = new Kitchen("Hi_Tech", "gray", 4, 50.5); 

		Kitchen Provance = new Kitchen("Provance", "yellow", 1, 15.5,1 ); 
		 
		System.out.println(Loft.toString()); 
		
		System.out.println(Hi_Tech.toString()); 
		
		System.out.println(Provance.toString()); 
		
		Loft.resetValues("Loft", "red", 3, 45,2); 
		
		System.out.println(Loft.toString());

		Loft.printSum(); 
  
		Hi_Tech.printSum(); 
 
		Provance.printSum(); 
 
		Hi_Tech.setNumberOfDrawers(5); 
 
		Kitchen.printStaticSum(); 
		} 
	}