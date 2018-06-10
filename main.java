package nazaruk;

public class Main {

	public static void Main(String[] args) {
		
		Kitchen loft = new Kitchen(); 
		
		Kitchen hiTech = new Kitchen("Hi_Tech", "gray", 4, 50.5); 

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
