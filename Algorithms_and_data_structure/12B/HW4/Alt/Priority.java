// Filename: Priority.java
//
// This program takes in input and insert nodes into a linkedlist.
//
// Output: It prints out the deleted node with the highest priority 
//
// after delete operation 
//
// HW4 by rtao6 Mar 2017

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class Priority {
	static final String SENTINEL = "d"; //set d as a mark 

	public static void main(String[] args) {
		//obtain input
		File file = new File(System.getProperty("user.dir") + "/input.txt");
		BufferedReader reader = null;
		PrintWriter out = null;
		//set output file
		try {
			out = new PrintWriter(new FileWriter(System.getProperty("user.dir")
					+ "/output.txt"));
		} catch (IOException e2) {
		}
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String outStr = doLine(line);
				out.println(outStr);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
			out.close();
		}
	}

	//doLine method creates arraylist of temp array
	//implement nodes as string into a linkedlist
	//sotre operated string into an int linkedlist
	//output: result after operations
	static String doLine(String line) {
		String result = "";
		String[] tempArray = line.split(SENTINEL);
		List<String> tempSplit = new ArrayList<String>();
		Collections.addAll(tempSplit, tempArray);
		if (!line.endsWith(SENTINEL)) {
			tempSplit.remove(tempSplit.size() - 1);
		}
		List<int[]> sortList = new ArrayList<int[]>();
		for (String item : tempSplit) {
			//clean up input
			String replaceItem = item.replaceAll("\\s+", "").replace(")(", "_")
					.replace("(", "").replace(")", "");
			String[] spilt = replaceItem.split("_");
			if (replaceItem.trim().length() > 0) {
				for (String newStr : spilt) {
					String[] temp = newStr.split(",");
					int value = 0;
					try {
						value = Integer.parseInt(temp[0].trim());
					} catch (Exception ex) {
					}
					int prioity = 0;
					try {
						prioity = Integer.parseInt(temp[1].trim());
					} catch (Exception ex) {
					}
					sortList.add(new int[] { value, prioity });
				}
				if (sortList.size() == 0)
					continue;
				int[] maxRecall = getMaxPrioity(sortList);
				if (maxRecall.length > 0) {
					result += maxRecall[0] + " ";
					removeInteger(sortList, maxRecall);
				}
			} else {
				if (sortList.size() > 0) {
					int[] maxRecall = getMaxPrioity(sortList);
					if (maxRecall.length > 0) {
						result += maxRecall[0] + " ";
						removeInteger(sortList, maxRecall);
					}
				} else {
					// print null there is nothing to delete
					result += "null ";
				}
			}
		}
		return result.trim().length() > 0 ? result.substring(0,
				result.length() - 1) : "";
	}

	//method takes in linked list cand return the max proority int
	static int[] getMaxPrioity(List<int[]> list) {
		int[] max = new int[2];
		for (Iterator<int[]> iter = list.iterator(); iter.hasNext();) {
			int[] item = iter.next();
			int value = item[0];
			int prioity = item[1];
			max = prioity > max[1] ? new int[] { value, prioity } : max;
		}
		return max;
	}

	//method removes the int from the list.
	//output: the list after operation
	static List<int[]> removeInteger(List<int[]> list, int[] i) {
		for (Iterator<int[]> it = list.iterator(); it.hasNext();) {
			int[] value = it.next();
			if (value[0] == i[0] && value[1] == i[1]) {
				it.remove();
			}
		}
		return list;
	}
}
