import java.util.Scanner;

/**
 * Left Leg Sensor hardware component
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

public class LeftLegSensor {

	static double LLTemp = 0.0; // used as variable for current temperature of users left leg
	static double minTempSet = 0.0; // used for setting the minimum temperature setting that user enters
	static double maxTempSet = 0.0; // used or setting the maximum temperature setting that use enters
	static double avgTemp = 0.0; // used for keeping temp as average of min and max set
	private static Scanner input1; // for inputting simulated min temp set by user
	public static String warning = "Left leg Temperature Unsafe! Increase or deacrease temperature now!"; // unsafe warning sent to user in GUI
	private static Scanner input2; // for inputting simulated max temp set by user
	private static Scanner input3; // for inputting simulated current chest sensor/controller temp
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

	// This method asks for user input for the current temperature of the users left leg to simulate a current left leg temperature
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

		System.out.println('\n' + "Enter a simulated current left leg temperature in fahrenheit"); // asks user to enter a simulated chest temperature in fahrenheit
		LLTemp = input3.nextDouble();
		System.out.println('\n' + "Your simulated chest temperature to test the program is " + LLTemp); // shows output in console of current simulated chest temp for testing purposes

	}
	//These setters and getters will be used when the sensor classes are ready to be implemented with the corresponding GUI classes

	public void setLLTemp(double LLTemp) { // set left leg temp that is simulated
		LeftLegSensor.LLTemp = LLTemp;
	}

	public double getLLTemp() { // get left leg temp that is simulated
		return LeftLegSensor.LLTemp;
	}

	public void setminTempSet(double minTempSet) { // set min temp entered by user in LeftLegSensorGUI class
		LeftLegSensor.minTempSet = minTempSet;
	}

	public double getminTempSet() { // get min temp entered by user in LeftLegSensorGUI class
		return LeftLegSensor.minTempSet;
	}

	public void setmaxTempSet(double maxTempSet) { // set max temp entered by user in LeftLegSensorGUI class
		LeftLegSensor.maxTempSet = maxTempSet;
	}

	public double getmaxTempSet() { // get max temp entered by user in LeftLegSensorGUI class
		return LeftLegSensor.maxTempSet;
	}

	// method to monitor the users chest temperature and adjust temp to avg if needed

	public static void monitorUnstableTemp(double minTempSet, double maxTempSet, double avgTemp, double LLTemp) {

		//send warning to user if above or below safe thresholds

		if (LLTemp < 32 || LLTemp > 98.6) { 

			System.out.println('\n' + "WARNING!: " + warning);
		}

		//comparing and adjusting left leg temperature sensor

		else if (LLTemp > minTempSet) { 

			LLTemp++;
			avgTemp = (minTempSet + maxTempSet) / 2;
			LLTemp = avgTemp;

			System.out.println('\n' + "Currently adjusting left leg sensor temp to  :  " + avgTemp + " " + "degrees fahrenheit");

		}

		//comparing and adjusting left leg temperature sensor

		else if (LLTemp < maxTempSet) { 

			LLTemp--;
			avgTemp = (minTempSet + maxTempSet) / 2;
			LLTemp = avgTemp;

			System.out.println('\n' + "Currently adjusting left leg sensor temp to  :  " + avgTemp + " " + "degrees fahrenheit");

		}


	}

	//Simulates sensor/controller monitoring for safe temperature

	public static void monitorSafeTemp(double minTempSet, double maxTempSet, double avgTemp, double LLTemp) {

		if (LLTemp != minTempSet && LLTemp != maxTempSet && LLTemp != avgTemp && !(LLTemp < minTempSet) && !(LLTemp > maxTempSet)) {

			System.out.println('\n' + "Monitoring safe operating temperature :  " + LLTemp + " " + "degrees fahrenheit");

		}


	}

	//The main is only here for the purpose of testing the sensor/controller logic

	public static void main(String args[]) {

		simulatedTemp();

		monitorUnstableTemp(maxTempSet, minTempSet, avgTemp, LLTemp);

		monitorSafeTemp(minTempSet, maxTempSet, avgTemp, LLTemp);
	}
}

