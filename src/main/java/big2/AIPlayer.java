package big2;

class AIPlayer extends Player {
  public AIPlayer(Hand hand, AIPlayerStrategy strategy) {
    super(hand);
    this.strategy = strategy;
  }

  AIPlayerStrategy strategy;

  @Override
  Play getNextPlay(DiscardPile d) {
    return strategy.getPlay(this, hand, d);
  }
}
