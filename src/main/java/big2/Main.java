package big2;

public class Main {
  public static void main(String[] args) {
    Hand[] hands = Deck.deal();
    for (int i = 0; i < 4; i++) {
      Card[] cards = hands[i].getCards();
      for (Card c : cards) System.out.print(c.toAbbrev() + " ");
      System.out.println();
    }
  }
}
