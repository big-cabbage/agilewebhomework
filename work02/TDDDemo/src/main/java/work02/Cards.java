package work02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cards implements Comparable<Cards>{

	private List<Card> cardList;
	private Rank rank;
	private Map<Integer, Integer> cardNumMap;
	private int highKindCard;
	private List<Integer> pariCardList;
	
	
	public int getHighKindCard() {
		return highKindCard;
	}
	
	public List<Integer> getPairCardList() {
		return pariCardList;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public Rank getRank() {
		return rank;
	}

	public Cards(String cardStr)
	{
		highKindCard = -1;
		pariCardList = new ArrayList<>();
		prepareCardArr(cardStr);
		prepareRank(cardStr);
	}
	
	private void prepareRank(String cardStr) {
		cardNumMap = new HashMap<>();
		boolean isStraight = true;
		boolean isFlush = true;
		int maxSameCardNum = 0;
		cardNumMap.put(cardList.get(0).getCardNum(), 1);
		for(int i=1; i<cardList.size(); i++)
		{
			if(cardNumMap.containsKey(cardList.get(i).getCardNum()))
			{
				cardNumMap.put(cardList.get(i).getCardNum(), cardNumMap.get(cardList.get(i).getCardNum()) +1);
				if(cardNumMap.get(cardList.get(i).getCardNum()) == 2)
				{
					pariCardList.add(cardList.get(i).getCardNum());
				}
				if(cardNumMap.get(cardList.get(i).getCardNum()) > maxSameCardNum)
				{
					maxSameCardNum = cardNumMap.get(cardList.get(i).getCardNum());
					if(maxSameCardNum == 3)
					{
						highKindCard = cardList.get(i).getCardNum();
						pariCardList.remove(pariCardList.size()-1);
					}
				}
			}
			else
			{
				cardNumMap.put(cardList.get(i).getCardNum(), 1);
			}
			if(cardList.get(i-1).getCardNum() != cardList.get(i).getCardNum()+1)
			{
				isStraight = false;
			}
			if(!cardList.get(i-1).getCardSuit().equals(cardList.get(i).getCardSuit()))
			{
				isFlush = false;
			}
		}
		if(isFlush)
		{
			if(isStraight)
			{
				this.rank = Rank.STRAIGHT_FLUSH; 
			}
			else
			{
				this.rank = Rank.FLUSH;
			}
			return;
		}
		if(maxSameCardNum == 4)
		{
			this.rank = Rank.FOUR_OF_A_KIND;
			return;
		}
		if(maxSameCardNum == 3)
		{
			if(cardNumMap.size() == 2)
			{
				this.rank = Rank.FULL_HOUSE;
			}
			else
			{
				this.rank = Rank.THREE_OF_A_KIND;
			}
			return;
		}
		if(isStraight)
		{
			this.rank = Rank.STRAIGHT;
			return;
		}
		if(maxSameCardNum == 2)
		{
			if(cardNumMap.size() == 3)
			{
				this.rank = Rank.TWO_PAIRS;
			}
			else
			{
				this.rank = Rank.PAIR;
			}
			return;
		}
		this.rank = Rank.HIGH_CARD;
	}

	private void prepareCardArr(String cardStr)
	{
		String[] tmpCards = cardStr.split(" ");
		this.cardList = new ArrayList<>();
		for(int i=0; i<tmpCards.length; i++)
		{
			this.cardList.add(new Card(tmpCards[i]));
		}
		cardList.sort(new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o1.getCardNum()>o2.getCardNum() ? -1: 1; 
			}
		});
	}
	
	private int compareHighCard(Cards o, int start, int end)
	{
		for(int i=start; i<end; i++)
		{
			if(cardList.get(i).getCardNum() > o.getCardList().get(i).getCardNum())
			{
				return cardList.get(i).getCardNum();
			}
			else if(cardList.get(i).getCardNum() < o.getCardList().get(i).getCardNum())
			{
				return -o.getCardList().get(i).getCardNum();
			}
		}
		return 0;
	}
	
	private int comparePairCard(Cards o)
	{
		for(int i=0; i<pariCardList.size(); i++)
		{
			if(pariCardList.get(i) != o.getPairCardList().get(i))
			{
				return pariCardList.get(i) > o.getPairCardList().get(i) ? 1:-1;
			}
		}
		return compareHighCard(o, 0, cardList.size());
	}
	
	@Override
	public int compareTo(Cards o){
		if(rank.getRankNum() > o.getRank().getRankNum())
		{
			return 100;
		}
		else if(rank.getRankNum() < o.getRank().getRankNum())
		{
			return -100;
		}
		switch(rank)
		{
			case HIGH_CARD:
			case FLUSH:
				return compareHighCard(o, 0, cardList.size());
			case STRAIGHT_FLUSH:
			case STRAIGHT:
				return compareHighCard(o, 0, 1);
			case THREE_OF_A_KIND:
			case FULL_HOUSE:
			case FOUR_OF_A_KIND:
				return highKindCard > o.getHighKindCard() ? 1 : -1;
			case PAIR:
			case TWO_PAIRS:
				comparePairCard(o);
		}
		throw new AssertionError("Cards inner error!");
	}
}
