package code;

import java.util.Scanner;

public class StringUtilities {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int command = -1;
        final int CONCAT = 1, FUSION = 2, REVERSE = 3, END = 4;
        String result = "", a = "", b = "";

        System.out.println("Welcome to String Utility Application");
        System.out.println("Command List:");
        System.out.println("1: Concat");
        System.out.println("2: Fusion");
        System.out.println("3: Reverse");
        System.out.println("4: End of program");
        System.out.println("====================");
        while(command != END) {
            System.out.print("Input your command: ");
            command = Integer.parseInt(scanner.nextLine());
            
            
            if (command == CONCAT) {
            	System.out.print("Input first string: ");
        		a = scanner.nextLine();
        		System.out.print("Input second string: ");
        		b = scanner.nextLine();
                result =  concatString(a,b);
                System.out.println("Result: " + result);
            } 
            else if (command == FUSION) {
            	System.out.print("Input first string: ");
        		a = scanner.nextLine();
        		System.out.print("Input second string: ");
        		b = scanner.nextLine();
        		result = fusionString(a,b);
                System.out.println("Result: " + result);
            }
            else if (command == REVERSE) {
            	System.out.print("Input string to reverse: ");
            	a = scanner.nextLine();
            	result = reverseString(a);
            	System.out.println("Result: " + result);
            } 
            else if (command == END) {
            System.out.print("Thank you for using my application");
            } 
            else {
            	System.out.println("Invalid command");
            }
    }
}
    public static String concatString(String first,String second){
    	String newWord="";
    	newWord = first.concat(second);
    	return newWord;
    }
    public static String fusionString(String word1,String word2){
				String re="";
				int length1 = word1.length();
        		int length2 = word2.length();
        		if(length1==length2){
        			for(int x = 0;x<=length1-1;x++){
        				char cha1 = word1.charAt(x);
        				char cha2 = word2.charAt(x);
        				char chars[]={cha1,cha2};
        				String mix = new String(chars);
        				re = re+mix;
        			}
        		}
        		return re;
    }
    public static String reverseString(String word) {
    	String reV="";
        int length = word.length();
        for(int x=length-1;x>=0;x--){
        	char cha = word.charAt(x);
        	char chars[]={cha};
        	String mix = new String(chars);
        	reV = reV+mix;
        }
        return reV;
    }
}
