import java.util.Scanner;

public class PrimalityMain {

	public static void main(String[] args) {
		// The value of isPrime will get printed in the end
		boolean isPrime = false;
		System.out.println("Welcome, user. Please input a positive integer.");
		System.out.println("'TRUE' will be printed if you input a prime number.");
		System.out.println("Otherwise, 'FALSE' will be printed.");
		System.out
				.println("If invalid characters such as negative signs or letters are entered, they will be ignored.");
		System.out.println("You will be informed if your input does not contain a positive integer.");
		Scanner scan = new Scanner(System.in);
		// Gets user's input as a string
		String str = scan.nextLine();
		// If string does not contain int, tells user that input was not valid
		// and does not run primality test
		int ourInt = makeInt(str);
		if (ourInt == -1)
			System.out.println("ERROR: invalid input");
		// If ourInt is 1 or 0, it will not be prime, so we won't do anything;
		// isPrime can be left false.
		else if (ourInt > 1)
			isPrime = primeTest(ourInt);
		System.out.println("The result is: ");
		if (isPrime)
			System.out.println("TRUE");
		else
			System.out.println("FALSE");
	}

	public static boolean primeTest(int ourInt) {
		int sqrt = (int) Math.floor(Math.sqrt(ourInt));
		// Checks if the number is 2 or 3.
		if(ourInt == 2 || ourInt == 3)
			return true;
		// Checks first if ourInt is even, a multiple of 3, or not in the form
		// of 6k +- 1. If any of these tests come back true, ourInt is not
		// prime. The method will therefore return false.
		int sixMod = ourInt % 6;
		if (ourInt % 2 == 0)
			return false;
		else if (ourInt % 3 == 0)
			return false;
		else if (sixMod != 1 && sixMod != 5)
			return false;
		// If there is a chance the number is prime, check all odd numbers from
		// 5 to the floor of its square root to see if they divide it evenly. If
		// any of them do, it is prime.
		else {
			for (int i = 5; i <= sqrt; i+=2)
				if (ourInt % i == 0)
					return false;
		}
		// If nothing was found to divide it and it is of the form 6k +- 1, the
		// number is prime.
		return true;
	}

	public static int makeInt(String str) {
		// The return value starts out as -1. If it stays that way, that is
		// because there were no digits in the input string to turn into the
		// return value. The user will be informed in the main method that they
		// did not provide valid input if this is the case.
		int retInt = -1;
		// Turns the string into an array of chars, each of which is checked to
		// see if it is a digit. If so, it is appended to the StringBuilder. The
		// StringBuilder is then turned into an integer and returned.
		// Note: used StringBuilder instead of mutating Strings because Strings
		// need to be copied each time they are "changed," which is highly
		// inefficient
		StringBuilder sb = new StringBuilder();
		char[] arr = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			char ch = arr[i];
			if (Character.isDigit(ch))
				sb.append(arr[i]);
		}
		// Note: I wasn't sure whether or not to just use parseInt at the
		// beginning; I thought it would be better to write my own method
		// for getting the integer and making sure that the user entered
		// something reasonable, but something similar could have been
		// accomplished in fewer lines with a try-catch block in the main
		// method.
		String toString = sb.toString();
		if (!toString.equals(""))
			retInt = Integer.parseInt(toString);
		return retInt;
	}
}
