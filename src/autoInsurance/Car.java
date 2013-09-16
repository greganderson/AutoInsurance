package autoInsurance;

import javax.swing.JOptionPane;

public class Car implements Comparable{

	/* Things needed */
	// Drivers license, estimated mileage, driving record

	private int year;
	private String model;
	private String make;
	private String vin;

	private Date effectiveDate;	// Date insurance starts
	private Date expirationDate;

	/**
	 * Makes a new car object.
	 * @param year year of car
	 * @param model model of car
	 * @param make make of car
	 * @param vin VIN number of car
	 */
	public Car(int year, String model, String make, String vin){
		this.year = year;
		this.model = model;
		this.make = make;
		this.vin = vin;

		// Set initial date
		Date date = new Date(1, 1, 2000);
		effectiveDate = date;
		expirationDate = date;

	}

	/**
	 * Changes the date the insurance is effective to
	 * the given date.
	 * @param date
	 */
	public void changeEffectiveDate(Date date){
		effectiveDate = date;
	}

	public boolean changeEffectiveDate(){
		String s = JOptionPane.showInputDialog("Enter new effective date (mm-dd-yyyy):");
		
		// Check if cancel was clicked or message box was closed
		if (s == null)
			return true;
		
		String[] a = s.split("[-/]");

		int month;
		int day;
		int year;

		// Month
		try {
			if (a[0].charAt(0) == '0') {
				month = a[0].charAt(1) - '0';
				if (month < 1 && month > 9)
					throw new NumberFormatException();
			}
			else
				month = Integer.parseInt(a[0]);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Encountered a non-number.\nPlease use only numbers.");
			return false;
		}

		// Day
		try {
			if (a[0].charAt(0) == '0') {
				day = a[0].charAt(1) - '0';
				if (day < 1 && day > 9)
					throw new NumberFormatException();
			}
			else
				day = Integer.parseInt(a[1]);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Encountered a non-number.\nPlease use only numbers.");
			return false;
		}

		// year
		try {
			year = Integer.parseInt(a[2]);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Encountered a non-number.\nPlease use only numbers.");
			return false;
		}


		changeEffectiveDate(new Date(month, day, year));
		
		return true;
	}

	/**
	 * Returns the date the insurance is effective.
	 * @return String month-day-year
	 */
	public String getEffectiveDate(){
		return effectiveDate.toString();
	}

	/**
	 * Changes the expiration date to the given date.
	 * @param date
	 */
	public void changeExpirationDate(Date date){
		expirationDate = date;
	}

	/**
	 * Get user input for new expiration date.  If anything but numbers are used, then an exception
	 * is thrown, and they are brought back to the main screen.  Returns true if everything worked
	 * successfully.  Returns false if there were any problems, causing the user to re-input the 
	 * information.
	 * 
	 * Does not take care of incorrect dates.
	 */
	// TODO: Make it so user can only input correct dates.
	public boolean changeExpirationDate(){
		String s = JOptionPane.showInputDialog("Enter new expiration date (mm-dd-yyyy):");
		
		// Check if cancel was clicked or message box was closed
		if (s == null)
			return true;
		
		String[] a = s.split("[-/]");

		int month;
		int day;
		int year;

		// Month
		try {
			if (a[0].charAt(0) == '0') {
				month = a[0].charAt(1) - '0';
				if (month < 1 && month > 9)
					throw new NumberFormatException();
			}
			else
				month = Integer.parseInt(a[0]);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Encountered a non-number.\nPlease use only numbers.");
			return false;
		}

		// Day
		try {
			if (a[0].charAt(0) == '0') {
				day = a[0].charAt(1) - '0';
				if (day < 1 && day > 9)
					throw new NumberFormatException();
			}
			else
				day = Integer.parseInt(a[1]);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Encountered a non-number.\nPlease use only numbers.");
			return false;
		}

		// year
		try {
			year = Integer.parseInt(a[2]);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Encountered a non-number.\nPlease use only numbers.");
			return false;
		}


		changeExpirationDate(new Date(month, day, year));
		
		return true;
	}

	/**
	 * Returns the expiration date of the insurance.
	 * @return String month-day-year
	 */
	public String getExpirationDate(){
		return expirationDate.toString();
	}

	/**
	 * Returns the year of the car.
	 * @return int
	 */
	public int getYear(){
		return year;
	}

	/**
	 * Returns the model.
	 * @return String
	 */
	public String getModel(){
		return model;
	}

	/**
	 * Returns the make.
	 * @return String
	 */
	public String getMake(){
		return make;
	}

	/**
	 * Returns the VIN number.
	 * @return String
	 */
	public String getVin(){
		return vin;
	}

	/**
	 * Returns all of the car information as a String.
	 * @return String
	 * 			year model make:
	 * 			VIN: vin
	 * 			Effective date: effectiveDate
	 * 			Expiration date: expirationDate
	 */
	public String getDetailedInfo(){
		String s = year + " " + model + " " + make + ":";
		s += "\nVIN: " + vin;
		s += "\nEffective date: " + effectiveDate.toString();
		s += "\nExpiration date: " + expirationDate.toString();
		return s;
	}

	/**
	 * Returns the basic information on the car, specifically
	 * the year, model, and make.
	 * @return String year model make
	 */
	public String toString(){
		return year + " " + model + " " + make;
	}

	/**
	 * Uses difference in year, then difference in first two
	 * letters of model, then difference in first two letters
	 * of make if all others are the same.
	 */
	@Override
	public int compareTo(Object arg) {
		Car c = (Car) arg;

		// Check for difference in year
		if (this.year != c.year){
			if (this.year < c.year)
				return 1;
			else if (this.year > c.year)
				return -1;
		}
		// Same year, so check model
		else {
			if (!this.model.equals(c.model)){
				if (this.model.charAt(0) < c.model.charAt(0))
					return 1;
				else if (this.model.charAt(0) > c.model.charAt(0))
					return -1;
				else {
					if (this.model.charAt(1) < c.model.charAt(1))
						return 1;
					else if (this.model.charAt(1) > c.model.charAt(1))
						return -1;
				}
			}
			// Same model, so check make
			else {
				if (!this.make.equals(c.make)){
					if (this.make.charAt(0) < c.make.charAt(0))
						return 1;
					else if (this.make.charAt(0) > c.make.charAt(0))
						return -1;
					else {
						if (this.make.charAt(1) < c.make.charAt(1))
							return 1;
						else if (this.make.charAt(1) > c.make.charAt(1))
							return -1;
					}
				}
			}
		}

		// Must be the same in almost every way
		return 0;
	}

}
