public class TrustRunner {
	public static void main(String[] args) {

	Round round1 = new Round("simpleton", "simpleton", 10, 5);
	for (var i = 0; i < 10; i++) {
		// System.out.println(round1.match());
		round1.match();
	}
	
	System.out.println(round1.getWinning());
	}
}