import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	

	public static boolean is_similar(String a, String b) {
		int difference = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				difference += 1;
			}
		}
		if (difference == 1) return true;
		else return false;
	}

	public static int string_distance(String a, String b) {
		int distance = 0;
		int str_len = a.length();
		for (int i = 0; i < str_len; i++) {
			int char_diff = a.charAt(i) - b.charAt(i);
			if (char_diff < 0) {
				char_diff = char_diff * -1;
			}
			distance += char_diff;
		}
		return distance;
	}

	

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0];
		String startWord = args[1];
		String endWord = args[2];
		FileReader reader = new FileReader(inputFileName);
		Scanner scanner = new Scanner(reader);

		//add all words to list
		List<String> wordList = new ArrayList<>();
		while(scanner.hasNext()){
			wordList.add(scanner.next());
		}
		reader.close();
		scanner.close();
		int no_of_vertices = wordList.size();

		Graph wordsGraph = new Graph(no_of_vertices);
		for (int i = 0; i < no_of_vertices; i++) {
			for (int j = 0; j < no_of_vertices; j++){
				if (is_similar(wordList.get(i), wordList.get(j)) && i != j){
					wordsGraph.getVertex(i).addToAdjList(j,(string_distance(wordList.get(i),wordList.get(j))));
				}
			}
		}
		
		wordsGraph.dijkstras(wordList.indexOf(startWord), wordList.indexOf(endWord), wordList);

		long end = System.currentTimeMillis();
		System.out.println("elapsed time: " + (end - start) + " milliseconds");
	}
}
