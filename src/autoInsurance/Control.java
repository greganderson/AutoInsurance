package autoInsurance;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class Control {
	
	/*
	// Options are shown in BACKWARDS order: 2, 1, 0
	private static int NUMBER_OF_OPTIONS = 5;
	private static int ADD_CAR = 4;
	private static int GET_INFO = 3;
	private static int REMOVE_CAR = 2;
	private static int CHANGE_INSURANCE = 1;
	private static int EXIT = 0;
	*/
	
	// Options are shown in NORMAL order: 0, 1, 2
	private static int NUMBER_OF_OPTIONS = 5;
	private static int ADD_CAR = 0;
	private static int GET_INFO = 1;
	private static int REMOVE_CAR = 2;
	private static int CHANGE_INSURANCE = 3;
	private static int EXIT = 4;
	
	public static String NO_CARS = "There are no cars on the insurance.";
	
	private AutoInsurance auto;
	private Object[] options;	// List of options
	private FileReadWrite file;

	public static void main(String[] args) throws FileNotFoundException{
		Control control = new Control();
		
		// Load known cars
		control.file.readFile();
		
		// Run until user clicks exit
		while (control.showList());
	}
	
	public Control(){
		auto = new AutoInsurance();
		options = new Object[NUMBER_OF_OPTIONS];
		file = new FileReadWrite(auto);
		
		// Make sure each option is added (NUMBER_OF_OPTIONS)
		options[ADD_CAR] = "Add car";
		options[GET_INFO] = "Show Information";
		options[REMOVE_CAR] = "Remove car";
		options[CHANGE_INSURANCE] = "Change insurance dates";
		options[EXIT] = "Exit";
	}
	
	public boolean showList(){
		String title = "List of cars on the insurance:\n\n";
		int i = 0;
		if (auto.getNumberOfCars() == 0) {
			i = JOptionPane.showOptionDialog(null, title + NO_CARS, 
					"Car Insurance", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, 
					options, null);
		}
		else {
			i = JOptionPane.showOptionDialog(null, title + auto.toString(), "Car Insurance", 
				JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, null);
		}
		
		if (i == ADD_CAR){
			auto.addCar();
			return true;
		}
		
		else if (i == GET_INFO){
			showInformation();
			return true;
		}
		
		else if (i == REMOVE_CAR){
			Car c = auto.getCar();
			if (c == null)
				return true;
			auto.removeCar(c);
			return true;
		}
		
		else if (i == CHANGE_INSURANCE){
			Car c = auto.getCar();
			if (c == null)
				return true;

			// Run until user either closes or successfully changes the date
			while (!c.changeEffectiveDate());
			
			// Run until user either closes or successfully changes the date
			while (!c.changeExpirationDate());
			
			return true;
		}
		
		else if (i == EXIT){
			file.toTextFile();
			return false;
		}
		
		else if (i == JOptionPane.CLOSED_OPTION) {
			file.toTextFile();
			return false;
		}
		
		// Take care of everything else
		else {
			JOptionPane.showMessageDialog(null, "Encountered different option");
			return true;
		}
	}
	
	public void showInformation(){
		Car c = auto.getCar();
		if (c == null)
			return;
		JOptionPane.showMessageDialog(null, c.getDetailedInfo());
	}
}
