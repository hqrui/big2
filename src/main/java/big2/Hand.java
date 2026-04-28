package big2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand {
  private final ArrayList<Card> cards;

  public Hand(Card[] cards) {
    this.cards = new ArrayList<>(Arrays.asList(cards));
  }

  public List<Card> getCards() {
    return Collections.unmodifiableList(cards);
  }

  public void sort() {
    cards.sort(Card::compareTo);
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  void removeCards(Play p) {
    ArrayList<Card> remainingCards = new ArrayList<>(cards);
    for (Card c : p.getCardList()) {
      if (!remainingCards.remove(c)) {
        throw new IllegalArgumentException("Play contains card not present in hand: " + c);
      }
    }
    cards.clear();
    cards.addAll(remainingCards);
  }
}
