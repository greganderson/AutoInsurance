package autoInsurance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReadWrite {

	private static String SOURCE = "test.txt";	// Location of the file to read from

	private AutoInsurance auto;

	public static void main(String[] args) throws FileNotFoundException{
		FileReadWrite file = new FileReadWrite(new AutoInsurance());
		file.readFile();
	}

	/**
	 * Takes the auto object created in Control.java to work with.
	 * @param auto
	 */
	public FileReadWrite(AutoInsurance auto){
		this.auto = auto;
	}

	/**
	 * Reads the file and adds the cars to the auto object.
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException{
		Scanner s = new Scanner(new File(SOURCE));

		String list = "";

		String line;
		while (s.hasNextLine()){
			line = s.nextLine();
			if (line.equals("")){
				continue;
			}
			
			// Get the car information

			// First line
			String[] info = line.split(" ");
			int year = Integer.parseInt(info[0]);
			String model = info[1];
			String make = info[2].substring(0, info[2].length()-1);

			// Second line
			line = s.nextLine();
			info = line.split(" ");
			String vin = info[1];

			// Third line
			line = s.nextLine();
			info = line.split(" ");
			String[] effectiveDate = info[2].split("-");
			int effMonth = Integer.parseInt(effectiveDate[0]);
			int effDay = Integer.parseInt(effectiveDate[1]);
			int effYear = Integer.parseInt(effectiveDate[2]);

			// Fourth line
			line = s.nextLine();
			info = line.split(" ");
			String[] expirationDate = info[2].split("-");
			int expMonth = Integer.parseInt(expirationDate[0]);
			int expDay = Integer.parseInt(expirationDate[1]);
			int expYear = Integer.parseInt(expirationDate[2]);

			// Add the car
			Car car = new Car(year, model, make, vin);
			car.changeEffectiveDate(new Date(effMonth, effDay, effYear));
			car.changeExpirationDate(new Date(expMonth, expDay, expYear));
			auto.addCar(car);
		}

		System.out.println(list);
	}

	/**
	 * Takes the car information and writes it to the given file.
	 */
	public void toTextFile(){
		try {
			PrintWriter output = new PrintWriter(new FileWriter(SOURCE));

			output.println(auto.toDetailedString());

			output.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
