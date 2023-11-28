public class Round {
	private String unoType = ""; 
	private String dosType = "";

	private int fullLose = -1;
	private int fullWin = 3;
	private int halfLose = 0;
	private int halfWin = 2;

	private int totalRounds = 10;

	private int failChance = 5;

	private int roundAmount = 0;

	private String winningList = "";

	/* Types are as follows:
		Copycat				- Cooperates until defected against once, then defects until the opponent cooperates (same thing opponent did last round)
		Always defect		- Always defects
		Always cooperate	- Always cooperates
		Random				- Completely random
		Grudger				- Always Cooperates until defected, then will always defect
	*/

	public Round(String unoType, String dosType, int totalRounds, int failChance) {
		this.unoType = unoType;
		this.dosType = dosType;
		this.failChance = failChance;
		this.totalRounds = totalRounds;
	}

	public String match() {
		Player player1 = new Player(unoType, failChance);
		Player player2 = new Player(dosType, failChance);
		player1.setEverDefect(false);
		player2.setEverDefect(false);
		for (var i = 0; i < totalRounds; i++) {
			

			String player1choice = player1.move();
			String player2choice = player2.move();

			player1.setThird(player1.getSecond());
			player2.setThird(player2.getSecond());
			player1.setSecond(player1.getLast());
			player2.setSecond(player2.getLast());


			if (player1choice.equals("cooperate") && player2choice.equals("cooperate")) {
				player1.setLast("cooperate");
				player2.setLast("cooperate");
				player1.setScore(halfWin);
				player2.setScore(halfWin);
			} else if (player1choice.equals("cooperate") && player2choice.equals("defect")) {
				player1.setLast("cooperate");
				player2.setLast("defect");
				player1.setScore(fullLose);
				player2.setScore(fullWin);
			} else if (player1choice.equals("defect") && player2choice.equals("cooperate")) {
				player1.setLast("defect");
				player2.setLast("cooperate");
				player1.setScore(fullWin);
				player2.setScore(fullLose);
			} else {
				player1.setLast("defect");
				player2.setLast("defect");
				player1.setScore(halfLose);
				player2.setScore(halfLose);
			}
			
			String everChecker = player1.getLast();

			if (everChecker.equals("defect")) {
				player1.setEverDefect(true);
			} 
			everChecker = player2.getLast();
			if (everChecker.equals("defect")) {
				player2.setEverDefect(true);
			}
		}

		if (player1.getScore() > player2.getScore()) {
			winningList = winningList + "\n1 -" + unoType;
		} else if (player1.getScore() < player2.getScore()) {
			winningList = winningList + "\n2 -" + dosType;
		} else {
			winningList = winningList + "\nTie";
		}
		roundAmount++;
		return "Round " + roundAmount + ": \nPlayer 1 scored a " + player1.getScore() + "\nPlayer 2 scored a " + player2.getScore() + "\nRound " + roundAmount + " settings: " + unoType + ", " + dosType + " ~~--~~ Total matches: " + totalRounds + " ~~--~~ Fail chance: " + failChance;
	}

	public String getWinning() {
		return winningList;
	}
}