package big2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Hand {
  private final ArrayList<Card> cards;

  Hand(Card[] cards) {
    this.cards = new ArrayList<>(Arrays.asList(cards));
  }

  List<Card> getCards() {
    return Collections.unmodifiableList(cards);
  }

  void sort() {
    cards.sort(Card::compareTo);
  }

  boolean isEmpty() {
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
