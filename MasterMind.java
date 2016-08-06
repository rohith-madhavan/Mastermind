import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MasterMind {
	
	static ArrayList<String> Dict = new ArrayList<String>();
	int EASY_LENGTH = 4;
	int MED_LENGTH = 5;
	int HARD_LENGTH = 6;
	int maxLen;
	
	static String secretWord;
	private static Scanner s;
	
	MasterMind(String sw, int len) {
		Dict = new ArrayList<String>();
		maxLen = len;
		secretWord = sw;
	}
	
	public static void filterFile2List(String dir, int len) {
		
		try{
			s = new Scanner(new File(dir));
			String line ="";
			while(s.hasNextLine()){
				line = s.nextLine();
				if(line.length() == len){
					Dict.add(line);
				}
			}
			
		}
		catch(Exception E){
			System.out.println("Exception"+E);
		}
		
		
	}
	
	public static int countMatch(String s1, String s2) {
		HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
		 for(int i = 0; i < s1.length(); i++)                                            
		 {
		   h1.add(s1.charAt(i));
		 }
		 for(int i = 0; i < s2.length(); i++)
		 {
		   h2.add(s2.charAt(i));
		 }
		 h1.retainAll(h2);
		 Character[] res = h1.toArray(new Character[0]);
		 return res.length;
	}
	
	public static ArrayList<String> pruneDictionary(String word, int commonChars) {
		
		ArrayList<String> prunedDict = new ArrayList<String>();
		
		for(int i = 0; i < Dict.size(); ++i) {
			
			if(countMatch(Dict.get(i), word) >= commonChars) {
				prunedDict.add(Dict.get(i));
			}
			
		}
		
		return prunedDict;
		
	}
	
	public static void elimWords(String s){
		

	    for(int j = 0; j < Dict.size(); j++){
	    	
	       for(int i = 0;i < s.length();i++) {
	    	   
	    	   String dictWord = Dict.get(j);
	        
	    	   if(dictWord.indexOf(s.charAt(i)) > 0) {
	    		   Dict.remove(Dict.get(j));
	    	   }
	    }
	}
	}
	
	public static boolean hasWon() {
	
		return Dict.size() == 1;

		
	}
	
	

	
		
	public static void main(String[] args){
		
		String dir = "C:/Users/sganeriwal/Desktop/PYPLtraining/Anagrams/sowpods.txt";
		
		int lengthX = 5;
		
		filterFile2List(dir, lengthX);
		
		String x = "";
		
		secretWord = "drape";
		
		for(int i = 0; i < 50; ++i) {
			System.out.println(Dict.size());
			Random rand = new Random();
			String computerWord = Dict.get(rand.nextInt(Dict.size()));
			System.out.println(computerWord);
			int commonChars = countMatch(computerWord, secretWord);
			System.out.println(commonChars);
			if(commonChars!=0)
				Dict = pruneDictionary(computerWord, commonChars);
			else 
				elimWords(computerWord);
			
			if(hasWon())
				x = "Win";
			
		}
		
	}

}
