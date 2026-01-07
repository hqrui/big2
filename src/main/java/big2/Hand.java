package big2;

import java.util.Arrays;

public class Hand {
  private Card[] cards;

  public Hand(Card[] cards) {
    this.cards = cards;
  }

  public Card[] getCards() {
    return cards;
  }

  public void sort() {
    Arrays.sort(cards);
  }
}
