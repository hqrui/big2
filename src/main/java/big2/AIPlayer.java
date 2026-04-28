package big2;

import java.util.List;

public class AIPlayer extends Player {
  public AIPlayer(Hand hand) {
    super(hand);
  }

  Play getNextPlay(DiscardPile d) {
    // dummy strategy
    List<Play> prevPlays = d.getPlays();
    hand.sort();
    if (prevPlays.isEmpty() || prevPlays.getLast() instanceof PassPlay) {
      // Play the lowest card
      return new SingleCardPlay(hand.getCards().getFirst());
    } else {
      for (Card c : hand.getCards()) {
        SingleCardPlay s = new SingleCardPlay(c);
        if (s.canPlayOver(prevPlays.getLast())) return s;
      }
      return new PassPlay();
    }
  }
}
