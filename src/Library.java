
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Library {
	private Set<Integer> books = new HashSet<Integer>();
	private int signUpTime = 0;
	private int rate = 0;
	private int id = 0;

	/**
	 * Constructor for Library
	 * 
	 * @param b
	 *            - books avaible
	 * @param sut
	 *            - sign up time
	 * @param r
	 *            - rate
	 * @param id
	 *            - id of the library
	 */
	public Library(Set<Integer> b, int sut, int r, int id) {
		books = b;
		signUpTime = sut;
		rate = r;
		this.id = id;
	}

	/**
	 * Return the books that will maximize the score of the library given the books already scanned
	 * and the time remaining
	 * 
	 * @param scanBooks
	 * @param time
	 * @param booksScore
	 * @return the books that the library'll scan
	 */
	public Set<Integer> booksToScan(Set<Integer> scanBooks, int time, List<Integer> booksScore) {

		Set<Integer> tempBooks = new HashSet<>(books);
		tempBooks.removeAll(scanBooks);
		List<Integer> sortedBooks = new ArrayList<>(tempBooks);
		sortedBooks.sort(Comparator.comparing(b -> -booksScore.get(b)));

		int numBooks = (time - getSignUpTime()) * rate;
		Set<Integer> booksToScan = new HashSet<>();
		for (int i = 0; i < numBooks && i < (tempBooks.size()); ++i) {
			booksToScan.add(sortedBooks.get(i));
		}

		return booksToScan;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (Integer i : books) {
			sb.append(i).append(" ");
			if (count > 0)
				break;
			count++;
		}
		return "Library " + id + " takes " + getSignUpTime() + " days to sign up, has a rate of " + rate + " books per day and has " + books.size() + " books : " + sb.toString();
	}

	public int getSignUpTime() {
		return signUpTime;
	}

	public int getId() {
		return id;
	}
}