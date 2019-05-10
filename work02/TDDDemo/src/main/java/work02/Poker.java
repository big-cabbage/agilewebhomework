package work02;

import java.util.Arrays;

public class Poker {
	private Cards playerBlack;
	private Cards playerWhite;
	
	public Poker(String playerBlack, String playerWhite) 
	{
		this.playerBlack = new Cards(playerBlack);
		this.playerWhite = new Cards(playerWhite);
	}
	
	public String getWinner()
	{
		int result = playerBlack.compareTo(playerWhite);
		String resultStr = "";
		if(result > 0)
		{
			resultStr += "Black wins - " + playerBlack.getRank().getRankDesc();
			if(playerBlack.getRank() == Rank.HIGH_CARD)
			{
				resultStr += ": "+ Card.cardNumtoString(result);
			}
		}
		else if(result < 0)
		{
			resultStr += "White wins - " + playerWhite.getRank().getRankDesc();
			if(playerBlack.getRank() == Rank.HIGH_CARD)
			{
				resultStr += ": "+ Card.cardNumtoString(-result);
			}
		}
		else
		{
			resultStr += "Tie";
		}
		return resultStr;
	}
}
