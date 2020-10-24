import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Problem {

	private int days;
	private List<Integer> booksScore = new ArrayList<>();
	private Set<Integer> scannedBooks = new HashSet<>();
	private Set<Library> remainingLibraries;
	private Set<Integer> lastBooksSub;

	/**
	 * Constructor of the problem
	 * 
	 * @param days
	 *            - days remaining
	 * @param booksScore
	 *            - score of the books used to sort libraries and book
	 * @param libraries
	 *            - remaining libraries
	 * @param scannedBooks
	 *            - books already scanned
	 */
	public Problem(int days, List<Integer> booksScore, Set<Library> libraries, Set<Integer> scannedBooks) {
		this.days = days;
		this.booksScore = booksScore;
		remainingLibraries = libraries;
		this.scannedBooks = scannedBooks;
	}

	/**
	 * Selects the best library based on the score of the books avaible in the library not yet
	 * scanned given the rate and days left Update : remainingLibraries (by removing the best
	 * Library) scannedBooks (by adding the books of the best Library that willbe scanned)
	 * lastBooksSub (by adding the books of the best Library that willbe scanned)
	 * 
	 * @return the best library
	 */
	public Library bestLibrary() {
		Library bestLib = null;
		int bestScore = -1;
		Set<Integer> currBooks;
		Set<Integer> bestBooks = null;
		for (Library l : remainingLibraries) {

			currBooks = l.booksToScan(scannedBooks, days, booksScore);
			int score = 0;
			for (int b : currBooks) {
				score += booksScore.get(b);
			}
			if (score > bestScore) {
				bestLib = l;
				bestScore = score;
				bestBooks = currBooks;
			}
		}
		remainingLibraries.remove(bestLib);
		scannedBooks.addAll(bestBooks);
		lastBooksSub = bestBooks;
		return bestLib;
	}

	/**
	 * Update the remaining days after the given library has signed up
	 * 
	 * @param l
	 *            - Library
	 * @return true if the library has the time to sign up
	 */
	public boolean updateDays(Library l) {
		int remainingDays = days - l.getSignUpTime();
		if (remainingDays >= 0) {
			days = remainingDays;
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		int stop = 0;
		for (Library l : remainingLibraries) {
			sb.append("\n").append(l.toString());
			if (stop > 50)
				break;
			stop++;
		}

		int count = 0;
		sb.append("\n").append("Books score : ");
		for (int score : booksScore) {
			sb.append(count + ":" + score).append(" ");
			if (count > 60)
				break;
			count++;
		}
		return "In " + days + " days, and " + remainingLibraries.size() + " libraries" + sb.toString();
	}

	public Set<Library> getRemainingLibraries() {
		return new HashSet<>(remainingLibraries);
	}

	public Set<Integer> getLastBooksSub() {
		return new HashSet<>(lastBooksSub);
	}
}
