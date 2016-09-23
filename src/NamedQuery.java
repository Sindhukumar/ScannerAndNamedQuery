import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class NamedQuery {
	static File file = new File("/home/oracle/workspace/ScannerAndNamedQuery/NamedQuery.txt");
	public static HashMap<String, String> namedQuery;
	private static PrintWriter writer;

	public static void main(String args[]) {
		while (true) {
			System.out.println("Create name query (1) or Run named query (2). any other key to exit");
			try {
				namedQuery = new HashMap<String, String>();
				switch (getInput().toLowerCase()) {
				case "1":
					do {
						getNamedQuery(namedQuery);
						System.out.println("More named query? (yes/no)");
					} while (getInput().equalsIgnoreCase("yes"));
					storeFile(namedQuery);
					break;
				case "2":
					readFile(namedQuery);
					//System.out.println(namedQuery);
					//System.out.println(namedQuery.isEmpty() +"---contains key  "+namedQuery.containsKey("hello") +"-----"+namedQuery.get("hello"));
					System.out.println("Enter query name");
					Query.runQuery(namedQuery.get(getInput()));
					break;
				default:return;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getNamedQuery(HashMap<String, String> namedQuery) {
		System.out.println("Enter name for query: ");
		String key = getInput();
		System.out.println("Enter the query: ");
		String value = getInput();
		namedQuery.put(key, value);
	}

	public static void storeFile(HashMap<String, String> namedQuery) throws FileNotFoundException {
		writer = new PrintWriter(file);
		for (String name : namedQuery.keySet()) {
			writer.print(name + "|" + namedQuery.get(name));
			writer.println();
			writer.flush();
		}
	}

	public static void readFile(HashMap<String, String> namedQuery) throws FileNotFoundException {
		Scanner fileSc = new Scanner(file).useDelimiter("\\|");
		while (fileSc.hasNext()) {
			namedQuery.put(fileSc.next(), fileSc.next());
		}
		fileSc.close();
	}

	public void close() {
		writer.close();
	}

	public static String getInput() {
		String input = "";
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		// sc.close();
		return input;

	}
}
