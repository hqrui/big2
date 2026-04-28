package big2;

abstract class Player {
  protected Hand hand;

  public Player(Hand hand) {
    this.hand = hand;
  }

  public boolean hasWon() {
    return hand.isEmpty();
  }

  abstract Play getNextPlay(DiscardPile d);

  void acceptPlay(Play p) {
    hand.removeCards(p);
  }
}
