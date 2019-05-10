package work02;

public enum Rank implements Comparable<Rank>{
	HIGH_CARD(1, "high card"), PAIR(2, "pair"), TWO_PAIRS(3, "two pairs"), THREE_OF_A_KIND(4, "three of a kind"), 
	STRAIGHT(5, "straight"), FLUSH(6, "flush"), FULL_HOUSE(7, "full house"), FOUR_OF_A_KIND(8, "four of a kind"),STRAIGHT_FLUSH(9, "straight flush");
	private int rankNum;
	private String rankDesc;
	private Rank(int rankNum, String rankDesc)
	{
		this.rankNum = rankNum;
		this.rankDesc = rankDesc;
	}
	
	public int getRankNum() {
		return rankNum;
	}
	
	public String getRankDesc() {
		return rankDesc;
	}
}
