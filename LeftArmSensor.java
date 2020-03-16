import java.util.Scanner;


/**
 * Left Arm Sensor hardware component
 * 
 * converted to software component
 * 
 * This class mimics how the EnviroWear
 * 
 * sensor/temp controller reacts to changes
 * 
 * in body temperature
 * 
 * @author Stephen Hamilton
 *
 */



//The temperature sensor picks up on safe / unsafe temperatures, sends warnings, and adjusts automatically to keep the user within safe temperature conditions

public class LeftArmSensor {

	static double LATemp = 0.0; // used as variable for current temperature of users left arm
	static double minTempSet = 0.0; // used for setting the minimum temperature setting that user enters
	static double maxTempSet = 0.0; // used or setting the maximum temperature setting that use enters
	static double avgTemp = 0.0; // used for keeping temp as average of min and max set
	private static Scanner input1; // for inputting simulated min temp set by user
	public static String warning = "Left arm Temperature Unsafe! Increase or deacrease temperature now!"; // unsafe warning sent to user in GUI
	private static Scanner input2; // for inputting simulated max temp set by user
	private static Scanner input3; // for inputting simulated current left arm sensor/controller temp
	static boolean isOn; //turn sensor on or off


	//method to call in sensor GUI class / turns sensor on or off.
public boolean SensorOn(boolean isOn) {
		
		if (isOn == true) {
			
			System.out.println("Chest sensor is on");
			
		}
		else if (isOn == false) {
			
			System.out.println("Chest sensor is off");
		}
		return isOn;
		
	}

	// This method asks for user input for the current temperature of the users left arm to simulate a current left arm temperature
	public static void simulatedTemp() {

		input1 = new Scanner(System.in);

		System.out.println("Enter a simulated minimum temperature in fahrenheit"); // for testing idea of user enter min temp
		minTempSet = input1.nextDouble();
		System.out.println("Your simulated minimum temperature to test the program is " + minTempSet); // shows output in console of current simulated min temp for testing purposes

		input2 = new Scanner(System.in);

		System.out.println('\n' + "Enter a simulated maximum temperature in fahrenheit"); // for testing idea of user enter min temp
		maxTempSet = input2.nextDouble();
		System.out.println('\n' + "Your simulated maximum temperature to test the program is " + maxTempSet); // shows output in console of current simulated max temp for testing purposes

		input3 = new Scanner(System.in);

		System.out.println('\n' + "Enter a simulated current left arm temperature in fahrenheit"); // asks user to enter a simulated left arm temperature in fahrenheit
		LATemp = input3.nextDouble();
		System.out.println('\n' + "Your simulated left arm temperature to test the program is " + LATemp); // shows output in console of current simulated left arm temp for testing purposes

	}
	//These setters and getters will be used when the sensor classes are ready to be implemented with the corresponding GUI classes

	public void setLATemp(double LATemp) { // set left arm temp that is simulated
		LeftArmSensor.LATemp = LATemp;
	}

	public double getLATemp() { // get left arm temp that is simulated
		return LeftArmSensor.LATemp;
	}

	public void setminTempSet(double minTempSet) { // set min temp entered by user in LeftArmSensorGUI class
		LeftArmSensor.minTempSet = minTempSet;
	}

	public double getminTempSet() { // get min temp entered by user in LeftArmSensorGUI class
		return LeftArmSensor.minTempSet;
	}

	public void setmaxTempSet(double maxTempSet) { // set max temp entered by user in LeftArmSensorGUI class
		LeftArmSensor.maxTempSet = maxTempSet;
	}

	public double getmaxTempSet() { // get max temp entered by user in LeftArmSensorGUI class
		return LeftArmSensor.maxTempSet;
	}

	// method to monitor the users left arm temperature and adjust temp to avg if needed

	public static void monitorUnstableTemp(double minTempSet, double maxTempSet, double avgTemp, double LATemp) {

		//send warning to user if above or below safe thresholds

		if (LATemp < 32 || LATemp > 98.6) { 

			System.out.println('\n' + "WARNING!: " + warning);
		}

		//comparing and adjusting left arm temperature sensor

		else if (LATemp > minTempSet) { 

			LATemp++;
			avgTemp = (minTempSet + maxTempSet) / 2;
			LATemp = avgTemp;

			System.out.println('\n' + "Currently adjusting left arm sensor temp to  :  " + avgTemp + " " + "degrees fahrenheit");

		}

		//comparing and adjusting left arm temperature sensor

		else if (LATemp < maxTempSet) { 

			LATemp--;
			avgTemp = (minTempSet + maxTempSet) / 2;
			LATemp = avgTemp;

			System.out.println('\n' + "Currently adjusting left arm sensor temp to  :  " + avgTemp + " " + "degrees fahrenheit");

		}


	}

	//Simulates sensor/controller monitoring for safe temperature

	public static void monitorSafeTemp(double minTempSet, double maxTempSet, double avgTemp, double LATemp) {

		if (LATemp != minTempSet && LATemp != maxTempSet && LATemp != avgTemp && !(LATemp < minTempSet) && !(LATemp > maxTempSet)) {

			System.out.println('\n' + "Monitoring safe operating temperature :  " + LATemp + " " + "degrees fahrenheit");

		}


	}

	//The main is only here for the purpose of testing the sensor/controller logic

	public static void main(String args[]) {

		simulatedTemp();

		monitorUnstableTemp(maxTempSet, minTempSet, avgTemp, LATemp);

		monitorSafeTemp(minTempSet, maxTempSet, avgTemp, LATemp);
	}
}
