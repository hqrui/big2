package big2;

import java.util.List;

class TwoCardPlay extends Play implements Comparable<TwoCardPlay> {
  // store in ascending suit order
  private final Card[] cards;

  TwoCardPlay(Card[] cards) throws InvalidPlayException {
    if (cards == null || cards.length != 2) {
      throw new IllegalArgumentException("TwoCardPlay must have exactly two cards");
    }
    if (cards[0].value() != cards[1].value()) {
      throw new InvalidPlayException("The two cards must have the same number");
    }
    if (cards[0].compareTo(cards[1]) > 0) {
      Card temp = cards[0];
      cards[0] = cards[1];
      cards[1] = temp;
    }
    this.cards = cards;
  }

  @Override
  public int compareTo(TwoCardPlay o) {
    return this.cards[1].compareTo(o.cards[1]);
  }

  @Override
  boolean canPlayOver(Play prev) {
    if (prev instanceof PassPlay) return true;
    if (prev instanceof TwoCardPlay) return (this.compareTo((TwoCardPlay) prev) > 0);
    else return false;
  }

  @Override
  List<Card> getCardList() {
    return List.of(cards);
  }
}
