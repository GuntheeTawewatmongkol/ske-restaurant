package code;

import java.util.Scanner;

public class Palindrome {
	 public static void main(String[] args) {
		 	Scanner myScan = new Scanner(System.in);
		    System.out.print("Input a number : ");
		    long num = myScan.nextLong();
		    
		    if(isPalindrome(num)){
	            System.out.println(num + " is palindrome number.");
	        }else{
	            System.out.println(num + " is not palindrome number.");
	        }
	 }
	 public static boolean isPalindrome( long number ) {
		 long palindrome = number;
	        long num2 = 0;

	        while (palindrome != 0) {
	            long mod = palindrome % 10;
	            num2 = num2 * 10 + mod;
	            palindrome = palindrome / 10;
	        }
	        if (number == num2) {
	            return true;
	        }
	        return false;
	 }
}
