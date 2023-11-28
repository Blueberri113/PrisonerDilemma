import java.util.Random;

public class Player {
	private String type = ""; 
	private String playerChoice = "";

	private boolean everDefect = false;
	private String lastMove = "cooperate";
	private String secondLast = "cooperate";
	private String myLast = "cooperate";
	private String thirdLast = "cooperate";

	private int score = 0;
	private boolean ifLower = false;

	private int failChance = 5;

	public Player(String type, int failChance) {
		this.type = type;
		this.failChance = failChance;
	}

	public void setEverDefect(boolean change) {
		everDefect = change;
	}
	public void setLast(String change) {
		lastMove = change;
	}
	public void setSecond(String change) {
		secondLast = change;
	}
	public void setScore(int add) {
		int temp = score;
		score = score + add;
		if (score <= temp) {
			ifLower = true;
		} else {
			ifLower = false;
		}
	}
	public void setThird(String change) {
		thirdLast = change;
	}

	public int getScore() {
		return score;
	}
	public boolean getEver() {
		return everDefect;
	}
	public String getLast() {
		return lastMove;
	}
	public String getSecond() {
		return secondLast;
	}



    public String move() {
        // Start determining if should cooperate or  defect

		// Copycat behavior
		if (type.equals("copycat")) {
			if (lastMove.equals("defect")) {
				playerChoice = "defect";
			} else {
				playerChoice = playerCooperate();
			}

			// Copykitten behavior
		} else if (type.equals("copykitten")){
			if (lastMove.equals("defect") && secondLast.equals("defect")) {
				playerChoice = "defect";
			} else {
				playerChoice = playerCooperate();
			}

			// Grudger behavior
		} else if (type.equals("grudger")) {
			if (everDefect = false) {
				playerChoice = playerCooperate();
			} else {
				playerChoice = "defect";
			}
		
			// Basics behavior
		} else if (type.equals("defect")) {
			playerChoice = "defect";
		} else if (type.equals("cooperate")) {
			playerChoice = playerCooperate();
		
			// Random behavior
		} else if (type.equals("random")) {
			playerChoice = randomChoice();

			// Copydog behavior
		} else if (type.equals("copydog")) {
			if (lastMove.equals("defect") && secondLast.equals("defect") && thirdLast.equals("defect")) {
				playerChoice = "defect";
			} else {
				playerChoice = playerCooperate();
			}
		} else if (type.equals("simpleton")) {
			if (ifLower) {
				if (myLast.equals("cooperate")) {
					playerChoice = "defect";
				} else {
					playerChoice = playerCooperate();
				}
			} else {
				if (myLast.equals("cooperate")) {
					playerChoice = playerCooperate();
				} else {
					playerChoice = "defect";
				}
			}
		}
		myLast = playerChoice;
		return playerChoice;
    }


	public String playerCooperate() {
		Random rand = new Random();
		int n = rand.nextInt(100);
		if (n < failChance) {
			return "defect";
		}
		return "cooperate";
	}

	public String randomChoice() {
		
		Random rand = new Random();
		int n = rand.nextInt(2);

		if (n == 0) {
			return "defect";
		} else {
			return playerCooperate();
		}
	}
}