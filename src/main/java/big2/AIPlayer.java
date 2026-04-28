package big2;

import java.util.List;

class AIPlayer extends Player {
  public AIPlayer(Hand hand) {
    super(hand);
  }

  @Override
  Play getNextPlay(DiscardPile d) {
    // dummy strategy
    List<Play> prevPlays = d.getPlays();
    hand.sort();
    if (prevPlays.isEmpty() || prevPlays.getLast() instanceof PassPlay) {
      // Play the lowest card
      return new SingleCardPlay(this, hand.getCards().getFirst());
    } else {
      for (Card c : hand.getCards()) {
        SingleCardPlay s = new SingleCardPlay(this, c);
        if (s.canPlayOver(prevPlays.getLast())) return s;
      }
      return new PassPlay(this);
    }
  }
}
