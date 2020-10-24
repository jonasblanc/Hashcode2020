
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		List<Library> librariesToSU = new ArrayList<>();
		List<Set<Integer>> booksToSub = new ArrayList<>();
		;

		// String filename = "a_example.txt";
		// String filename = "b_read_on.txt";
		// String filename = "c_incunabula.txt";
		// String filename = "d_tough_choices.txt";
		// String filename = "e_so_many_books.txt";
		String filename = "f_libraries_of_the_world.txt";

		Problem p = IO.instanciateAProblem("input/" + filename);
		System.out.println(p);

		boolean enoughTime = true;
		boolean isLibrariesEmpty = false;
		Set<Integer> books = new HashSet<>();
		Library l;
		while (enoughTime && !isLibrariesEmpty) {

			l = p.bestLibrary();
			books = p.getLastBooksSub();
			enoughTime = p.updateDays(l);
			if (books.size() > 0 && enoughTime) {
				librariesToSU.add(l);
				booksToSub.add(books);
			} else {
				break;
			}
			isLibrariesEmpty = p.getRemainingLibraries().isEmpty();
		}

		String s = translator(librariesToSU, booksToSub);
		IO.writer("output/toSub_" + filename, s);

	}

	/**
	 * Creates an output string
	 * 
	 * @param librariesToSignUp
	 *            (List<Library>) - libraries in order
	 * @param booksToSub
	 *            ( List<Set<Integer>>) - corresponding books for every library
	 * @return a foramted string
	 */
	private static String translator(List<Library> librariesToSignUp, List<Set<Integer>> booksToSub) {
		StringBuilder sb = new StringBuilder();
		sb.append(librariesToSignUp.size());

		int count = 0;
		for (Library lib : librariesToSignUp) {
			sb.append("\n");
			sb.append(lib.getId()).append(" ").append(booksToSub.get(count).size()).append("\n");
			for (Integer i : booksToSub.get(count)) {
				sb.append(i).append(" ");
			}
			count++;
		}

		return sb.toString();
	}

}
