package autoInsurance;

import java.util.TreeSet;

import javax.swing.JOptionPane;

public class AutoInsurance {

	private TreeSet<Car> cars;

	public AutoInsurance(){
		cars = new TreeSet<Car>();
	}

	/**
	 * Adds car to list of cars, returning true if car
	 * successfully added.
	 * @param car
	 * @return boolean
	 */
	public boolean addCar(Car car){
		return cars.add(car);
	}

	/**
	 * Ask for input for new car information, returning true if
	 * car successfully added.
	 * @return boolean
	 */
	public boolean addCar(){
		int year = 0;
		while (true){
			String yearString = JOptionPane.showInputDialog("Year:");
			if (yearString == null)
				return false;
			try {
				year = Integer.parseInt(yearString);
				break;
			}
			catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "A non-number was encountered.\nPlease use only numbers.");
			}
		}

		String model = JOptionPane.showInputDialog("Model:");
		if (model == null)
			return false;

		String make = JOptionPane.showInputDialog("Make:");
		if (make == null)
			return false;

		String vin = JOptionPane.showInputDialog("VIN:");
		if (vin == null)
			return false;


		Car car = new Car(year, model, make, vin);
		return cars.add(car);
	}

	/**
	 * Pulls up a list of cars to choose from.
	 * @return Car
	 */
	public Car getCar(){
		if (getNumberOfCars() == 0){
			JOptionPane.showMessageDialog(null, Control.NO_CARS);
			return null;
		}

		Object[] options = toObjectArray();
		int i = JOptionPane.showOptionDialog(null, "Please choose a car:", 
				"Cars on Insurance", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, null);
		if (i == JOptionPane.CLOSED_OPTION)
			return null;

		// Go through list of cars until the right one is found
		// TODO: This method isn't very good.  Easily broken if you
		//		have two '07 Honda Civics, which might happen.
		for (Car c: cars)
			if (c.toString().equals(options[i]))
				return c;
		return null;
	}

	/**
	 * Removes car c from the list of cars.
	 * @param c car to remove
	 * @return boolean if successfully removed
	 */
	public boolean removeCar(Car c){
		return cars.remove(c);
	}

	/**
	 * Returns the number of cars on the insurance.
	 * @return int
	 */
	public int getNumberOfCars(){
		return cars.size();
	}

	/**
	 * Returns the cars as Object[].
	 * @return Object[]
	 */
	public Object[] toObjectArray(){
		Object[] o = new Object[cars.size()];
		TreeSet<Car> temp = new TreeSet<Car>();
		temp.addAll(cars);
		for (int i = 0; i < cars.size(); i++){
			o[i] = temp.pollFirst().toString();
		}
		return o;
	}

	/**
	 * Returns a String representation of the list of cars.
	 */
	public String toString(){
		String s = "";
		TreeSet<Car> temp = new TreeSet<Car>();
		temp.addAll(cars);
		for (int i = 0; i < cars.size(); i++)
			s += temp.pollLast().toString() + "\n\n";
		return s;
	}
	
	public String toDetailedString(){
		String s = "";
		TreeSet<Car> temp = new TreeSet<Car>();
		temp.addAll(cars);
		for (int i = 0; i < cars.size(); i++)
			s += temp.pollLast().getDetailedInfo() + "\n\n";
		s = s.substring(0, s.length()-2);
		return s;
	}

}
