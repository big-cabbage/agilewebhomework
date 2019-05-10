import static org.junit.Assert.assertEquals;

import org.junit.Test;

import work02.Poker;

public class TestPoker {

	@Test
	 public void testGetWinner()
	 {
		 Poker p1 = new Poker("2H 3D 5S 9C KD", "2C 3H 4S 8C AH");
		 assertEquals("White wins - high card: Ace", p1.getWinner());
		 
		 Poker p2 = new Poker("2H 4S 4C 2D 4H", "2S 8S AS QS 3S");
		 assertEquals("Black wins - full house", p2.getWinner());
		 
		 Poker p3 = new Poker("2H 3D 5S 9C KD", "2C 3H 4S 8C KH");
		 assertEquals("Black wins - high card: 9", p3.getWinner());
		 
		 Poker p4 = new Poker("2H 3D 5S 9C KD", "2D 3H 5C 9S KH");
		 assertEquals("Tie", p4.getWinner());
	 }
}
