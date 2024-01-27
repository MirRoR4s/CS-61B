import org.junit.Test;
import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	@Test
	public void testRemoveLast() {
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		lld1.addLast(1);
		int ans = lld1.removeLast();
		assertEquals(1, ans);
		assertEquals(lld1.size(), 0);
		lld1.addLast(1);
		lld1.addLast(2);
		ans = lld1.removeLast();
		assertEquals(2, ans);
		assertEquals(lld1.size(), 1);
	}

	@Test
	public void testGet() {
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		lld1.addLast(1);
		lld1.addLast(2);
		int ans = lld1.get(0);
		assertEquals(1, ans);
		ans = lld1.get(1);
		assertEquals(2, ans);
		lld1.addFirst(3);
		ans = lld1.get(0);
		assertEquals(3, ans);
	}

	@Test
	public void testGetRecursive() {
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		lld1.addLast(1);
		lld1.addLast(2);
		int ans = lld1.getRecursive(0);
		assertEquals(1, ans);
		ans = lld1.getRecursive(1);
		assertEquals(2, ans);
		lld1.addFirst(3);
		ans = lld1.getRecursive(0);
		assertEquals(3, ans);
	}
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
		
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
		
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
	}
} 