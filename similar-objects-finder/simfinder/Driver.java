package simfinder;
import java.util.*;
import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException {
		File f = new File(args[0]);
		Calculator c = new Calculator(Parser.parseFile(f));
		FieldValue[] blankFields = c.getBlankFields();
		System.out.println("Please choose your preference for the following attribute.");
		int i=0;
		while(i<blankFields.length){
			String[] options = c.getOptionsFor(blankFields[i].getHeader());
			System.out.println("Options for "+blankFields[i].getHeader()+": ");
			for(String option: options){
				System.out.println(option);
			}
			String input = getUserInput();
			blankFields[i].setValue(input);
			if(Arrays.asList(options).contains(input))
				i++;

		}
		System.out.println("Number of returned results?");
		int numOfResults = Integer.parseInt(getUserInput());

		ArrayList<String> results = c.calculate(blankFields,numOfResults);
		print(results);
	}

	public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }
    public static void print(ArrayList<String> res){ //Used to print the results from an arrayList
    	for(int i =0;i<res.size();i++){
    		System.out.println((i+1)+". "+res.get(i));
    	}
    }
}
