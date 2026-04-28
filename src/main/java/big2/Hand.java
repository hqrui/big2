package big2;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
  private final ArrayList<Card> cards;

  public Hand(Card[] cards) {
    this.cards = new ArrayList<>(Arrays.asList(cards));
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public void sort() {
    cards.sort(Card::compareTo);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  void removeCards(Play p) {
    for (Card c : p.getCardList()) {
      cards.remove(c);
    }
  }
}
