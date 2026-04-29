package big2;

import java.util.List;

class OneCardPlay extends Play implements Comparable<OneCardPlay> {
  private final Card card;

  OneCardPlay(Player player, Card card) {
    super(player);
    if (card == null) throw new IllegalArgumentException("Card must not be null");
    this.card = card;
  }

  OneCardPlay(Card card) {
    this(null, card);
  }

  @Override
  public int compareTo(OneCardPlay o) {
    return this.card.compareTo(o.card);
  }

  @Override
  boolean canPlayOver(Play prev) {
    if (prev instanceof PassPlay) return true;
    if (prev instanceof OneCardPlay) return (this.compareTo((OneCardPlay) prev) > 0);
    else return false;
  }

  @Override
  List<Card> getCardList() {
    return List.of(card);
  }
}
