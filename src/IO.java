
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class IO {

	/**
	 * Creates a problem out of an input file with the correct format
	 * 
	 * @param filename
	 *            (String) - the name of the input file
	 * @return an instance of a problem
	 */
	public static Problem instanciateAProblem(String filename) {
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			int days = 0;
			List<Integer> booksScore = new ArrayList<>();
			Set<Library> libraries = new HashSet<>();

			String line;
			int lineCounter = 0;
			int libCounter = 0;
			while ((line = in.readLine()) != null) {
				if (lineCounter == 0) {
					days = Integer.parseInt(line.split(" ")[2]);
				} else if (lineCounter == 1) {
					for (String s : line.split(" ")) {
						booksScore.add(Integer.parseInt(s));
					}
				} else {
					if (line.split(" ").length > 1) {
						int sut = Integer.parseInt(line.split(" ")[1]);
						int rate = Integer.parseInt(line.split(" ")[2]);
						Set<Integer> books = new HashSet<>();
						line = in.readLine();
						for (String s : line.split(" ")) {
							books.add(Integer.parseInt(s));
						}
						libraries.add(new Library(books, sut, rate, libCounter));
						libCounter++;
					}
				}
				lineCounter += 1;
			}
			return new Problem(days, booksScore, libraries, new HashSet<>());
		} catch (IOException e) {
			System.out.println(e);
			throw new Error("Reading the file went wrong");
		}
	}

	/**
	 * Writes the given data in a file
	 * 
	 * @param file
	 *            (String) - the name of the output file
	 * @param data
	 *            (String) - the data to write in the output file
	 */
	public static void writer(String filename, String data) {
		try (Writer w = new FileWriter(filename)) {
			w.write(data);
		} catch (IOException e) {
			throw new Error("Writing the file went wrong");
		}
		return;
	}
}
