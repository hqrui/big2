package big2;

class SingleCardPlay extends Play implements Comparable<SingleCardPlay> {
  private final Card card;

  SingleCardPlay(Card card) {
    if (card == null) throw new IllegalArgumentException("Card must not be null");
    this.card = card;
  }

  @Override
  public int compareTo(SingleCardPlay o) {
    return this.card.compareTo(o.card);
  }

  public boolean canPlayOver(Play prev){
    if(prev instanceof SingleCardPlay) return (this.compareTo((SingleCardPlay) prev) > 0);
    else return false;
  }
}
