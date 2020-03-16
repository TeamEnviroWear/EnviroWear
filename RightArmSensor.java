import java.util.Scanner;


/**
 * Right Arm Sensor hardware component
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

public class RightArmSensor {

	static double RATemp = 0.0; // used as variable for current temperature of users right arm
	static double minTempSet = 0.0; // used for setting the minimum temperature setting that user enters
	static double maxTempSet = 0.0; // used or setting the maximum temperature setting that use enters
	static double avgTemp = 0.0; // used for keeping temp as average of min and max set
	private static Scanner input1; // for inputting simulated min temp set by user
	public static String warning = "Right arm Temperature Unsafe! Increase or deacrease temperature now!"; // unsafe warning sent to user in GUI
	private static Scanner input2; // for inputting simulated max temp set by user
	private static Scanner input3; // for inputting simulated current right arm sensor/controller temp
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

	// This method asks for user input for the current temperature of the users right arm to simulate a current right arm temperature
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

		System.out.println('\n' + "Enter a simulated current right arm temperature in fahrenheit"); // asks user to enter a simulated right arm temperature in fahrenheit
		RATemp = input3.nextDouble();
		System.out.println('\n' + "Your simulated right arm temperature to test the program is " + RATemp); // shows output in console of current simulated right arm temp for testing purposes

	}
	//These setters and getters will be used when the sensor classes are ready to be implemented with the corresponding GUI classes

	public void setRATemp(double RATemp) { // set right arm temp that is simulated
		RightArmSensor.RATemp = RATemp;
	}

	public double getRATemp() { // get right arm temp that is simulated
		return RightArmSensor.RATemp;
	}

	public void setminTempSet(double minTempSet) { // set min temp entered by user in RightArmSensorGUI class
		RightArmSensor.minTempSet = minTempSet;
	}

	public double getminTempSet() { // get min temp entered by user in RightArmSensorGUI class
		return RightArmSensor.minTempSet;
	}

	public void setmaxTempSet(double maxTempSet) { // set max temp entered by user in RightArmSensorGUI class
		RightArmSensor.maxTempSet = maxTempSet;
	}

	public double getmaxTempSet() { // get max temp entered by user in RightArmSensorGUI class
		return RightArmSensor.maxTempSet;
	}

	// method to monitor the users right arm temperature and adjust temp to avg if needed

	public static void monitorUnstableTemp(double minTempSet, double maxTempSet, double avgTemp, double RATemp) {

		//send warning to user if above or below safe thresholds

		if (RATemp < 32 || RATemp > 98.6) { 

			System.out.println('\n' + "WARNING!: " + warning);
		}

		//comparing and adjusting right arm temperature sensor

		else if (RATemp > minTempSet) { 

			RATemp++;
			avgTemp = (minTempSet + maxTempSet) / 2;
			RATemp = avgTemp;

			System.out.println('\n' + "Currently adjusting right arm sensor temp to  :  " + avgTemp + " " + "degrees fahrenheit");

		}

		//comparing and adjusting right arm temperature sensor

		else if (RATemp < maxTempSet) { 

			RATemp--;
			avgTemp = (minTempSet + maxTempSet) / 2;
			RATemp = avgTemp;

			System.out.println('\n' + "Currently adjusting right arm sensor temp to  :  " + avgTemp + " " + "degrees fahrenheit");

		}


	}

	//Simulates sensor/controller monitoring for safe temperature

	public static void monitorSafeTemp(double minTempSet, double maxTempSet, double avgTemp, double RATemp) {

		if (RATemp != minTempSet && RATemp != maxTempSet && RATemp != avgTemp && !(RATemp < minTempSet) && !(RATemp > maxTempSet)) {

			System.out.println('\n' + "Monitoring safe operating temperature :  " + RATemp + " " + "degrees fahrenheit");

		}


	}

	//The main is only here for the purpose of testing the sensor/controller logic

	public static void main(String args[]) {

		simulatedTemp();

		monitorUnstableTemp(maxTempSet, minTempSet, avgTemp, RATemp);

		monitorSafeTemp(minTempSet, maxTempSet, avgTemp, RATemp);
	}
}
