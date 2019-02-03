package j4kyu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {
    private PokerHand.Result loss = PokerHand.Result.LOSS;
    private PokerHand.Result win = PokerHand.Result.WIN;
    private PokerHand.Result tie = PokerHand.Result.TIE;

    @Test
    public void PokerHandTest() {
        Test2(PokerHand.Role.StraightFlush, "2H 3H 4H 5H 6H");
        Test2(PokerHand.Role.Flush, "AH 3H 4H 5H 6H");
        Test2(PokerHand.Role.Straight, "2C 3H 4H 5H 6H");
        Test2(PokerHand.Role.Straight, "2C 3H 4H 5H AH");
        Test2(PokerHand.Role.RoyalStraightFlush, "JH TH KH QH AH");
        Test2(PokerHand.Role.Highest, "2S AH 4H 5S 6C");
        Test2(PokerHand.Role.FourOfKind, "AS AD AC AH JD");
        Test2(PokerHand.Role.ThreeOfKind, "AS AD AC TH JD");
        Test2(PokerHand.Role.FullHouse, "2S AH 2H AS AC");
        Test2(PokerHand.Role.TwoPairs, "2S 2H 4H 5S 4C");
    }
    private void Test2(PokerHand.Role exp, String cards) {
        assertEquals(exp, new PokerHand(cards).getRole(), cards);
    }

    @Test
    public void PokerHandRankingTest()
    {
        Test("Highest straight flush wins",        loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
        Test("Straight flush wins of 4 of a kind", win,  "2H 3H 4H 5H 6H", "AS AD AC AH JD");
        Test("Highest 4 of a kind wins",           win,  "AS AH 2H AD AC", "JS JD JC JH 3D");
        Test("4 Of a kind wins of full house",     loss, "2S AH 2H AS AC", "JS JD JC JH AD");
        Test("Full house wins of flush",           win,  "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
        Test("Highest flush wins",                 win,  "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
        Test("Flush wins of straight",             win,  "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
        Test("Equal straight is tie", 	  	      tie,  "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
        Test("Straight wins of three of a kind",   win,  "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
        Test("3 Of a kind wins of two pair",       loss, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
        Test("2 Pair wins of pair",                win,  "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
        Test("Highest pair wins",                  loss, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
        Test("Pair wins of nothing",               loss, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
        Test("Highest card loses",                 loss, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
        Test("Highest card wins",                  win,  "4S 5H 6H TS AC", "3S 5H 6H TS AC");
        Test("Equal cards is tie",		          tie,  "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }

    private void Test(String description, PokerHand.Result expected, String playerHand, String opponentHand)
    {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        assertEquals(expected, player.compareWith(opponent), description);
    }
}