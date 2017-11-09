// Filename: Bard.java
//
// This program takes in input and reads from shakespeare.txt to run analysis
//
// Output: It outputs the frequency of certains words or the most frequent words
//
// HW5 by rtao6 Mar 2017


/**
 * Created by ruikangtao on 3/14/17.
 */

import java.io.*;
import java.util.*;

public class Bard {
	public static void main(String[] args) throws IOException {
		//declare hashmap, files, scanner for source txt and writer
		HashMap<String, Integer> text = new HashMap<>();
		File Shakes = new File(System.getProperty("user.dir") + "/shakespeare.txt");
		Scanner ins = new Scanner(Shakes);
		PrintWriter out = new PrintWriter(new FileWriter(
				System.getProperty("user.dir") + "/analysis.txt"));
		String word;
		int count = 1;
		//while loop hashes words into hashmap
		while (ins.hasNext()) {
			String value = ins.next();
			if (value.trim().length() == 0)
				continue;
			word = value.replaceAll("[^a-zA-Z'\\-]", "");
			if (text.containsKey(word.toLowerCase())
					&& !word.equals(word.toUpperCase())) {
				text.put(word.toLowerCase(), text.get(word.toLowerCase()) + 1);
			} else if (!word.equals(word.toUpperCase())) {
				text.put(word.toLowerCase(), count);
			}
		}

		//declare arraylist to store words according to frequency
		List<Map.Entry<String, Integer>> list_Data = new ArrayList<Map.Entry<String, Integer>>(
				text.entrySet());
		//additional file and scanner to read input
		File file = new File(System.getProperty("user.dir") + "/input.txt");
		Scanner in = new Scanner(file);
		//while loop reads input file
		while (in.hasNext()) {
			String[] query = in.nextLine().split("\\s+");
			if (query.length == 1) {
				if (text.containsKey(query[0].toLowerCase())) {
					out.println(text.get(query[0].toLowerCase()));
				} else
					out.println("0");
			}

			else if (query.length == 2) {
				// use frequency to find words
				List<Map.Entry<String, Integer>> list = sort(
						getKeysFromValue(text, Integer.parseInt(query[0]))); 
				if (list.size() > 1) {
					int temp = Integer.parseInt(query[1]);
					int length = list.size() > temp ? temp : list.size();
					for (int m = 0; m < length; m++)
						// output specific numbers of words
						out.print(list.get(m).getKey() + " ");
				}
				out.println("");
			}
		}
		in.close();
		out.close();
		ins.close();
	}


	//sort function sort words in list
	static List<Map.Entry<String, Integer>> sort(
			List<Map.Entry<String, Integer>> list) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		// sort
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				if (o2.getValue() != null && o1.getValue() != null
						&& o2.getValue().compareTo(o1.getValue()) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return list;
	}

	//getKeysFromValue function find words with given frequency
	//return list
	public static List<Map.Entry<String, Integer>> getKeysFromValue(
			HashMap<String, Integer> hm, int len) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
		Iterator<Map.Entry<String, Integer>> iterator = hm.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> pair = iterator.next();
			String key = String.valueOf(pair.getKey());
			if (key.length() == len) {
				list.add(pair);
			}
		}
		return list;
	}
}