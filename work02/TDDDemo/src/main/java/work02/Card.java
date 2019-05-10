package work02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card{
	private String cardStr;
	private String cardSuit;
	private int cardNum;
	private static List<String> cardSuitOutList;
	private static List<String> cardSuitInList;
	
	static
	{
		cardSuitInList = Arrays.asList(new String[]{"error!","error!","2","3","4","5","6","7","8","9","T","J","Q","K","A"});
		cardSuitOutList = Arrays.asList(new String[]{"error!","error!","2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"});
	}
	
	public int getCardNum() {
		return cardNum;
	}

	public String getCardStr() {
		return cardStr;
	}

	public String getCardSuit() {
		return cardSuit;
	}
	
	public static String cardNumtoString(int num)
	{
		return cardSuitOutList.get(num);
	}

	public Card(String card) {
		this.cardStr = card;
		this.cardSuit = card.substring(1);
		this.cardNum = cardSuitInList.indexOf(card.substring(0,1));
	}
	
}
