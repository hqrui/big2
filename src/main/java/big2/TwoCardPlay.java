package big2;

class TwoCardPlay implements Comparable<TwoCardPlay> {
  // store in ascending suit order
  private final Card[] cards;

  TwoCardPlay(Card[] cards) throws InvalidPlayException {
    if (cards == null || cards.length != 2) {
      throw new RuntimeException("TwoCardPlay must have exactly two cards");
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
}
