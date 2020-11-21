import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static boolean isSimilar(String a, String b) {
		int difference = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				difference += 1;
			}
		}
		if (difference == 1) return true;
		else return false;
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

		//build the graph from the input file
		Graph wordsGraph = new Graph(wordList.size());
		//iterate over list updating vertices adjacent lists
		for (int i = 0; i < wordList.size(); i++) {
			for (int j = 0; j < wordList.size(); j++){
				if (isSimilar(wordList.get(i), wordList.get(j)) && i != j){
					// update information for vertex with index
					wordsGraph.getVertex(i).addToAdjList(j);
				}
			}
		}
		
		wordsGraph.bfs(wordList.indexOf(startWord), wordList.indexOf(endWord), wordList);

		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
