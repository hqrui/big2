package big2;

import java.util.ArrayList;

public class DiscardPile {
  private final ArrayList<Play> plays = new ArrayList<>();
  private final int turn = 0;

  private boolean checkValid(Play p) {
    if (turn == 0) return true;
    if (p.player != null && p.player == plays.getLast().player) return true;
    if (plays.getLast() instanceof SingleCardPlay) {
      if (p instanceof SingleCardPlay) {
        return (((SingleCardPlay) p).compareTo((SingleCardPlay) plays.getLast())) > 0;
      } else return false;
    } else if (plays.getLast() instanceof TwoCardPlay) {
      if (p instanceof TwoCardPlay) {
        return (((TwoCardPlay) p).compareTo((TwoCardPlay) plays.getLast())) > 0;
      } else return false;
    } else if (plays.getLast() instanceof FiveCardPlay) {
      if (p instanceof FiveCardPlay) {
        return (((FiveCardPlay) p).compareTo((FiveCardPlay) plays.getLast())) > 0;
      } else return false;
    } else throw new RuntimeException("Play is not Single, Two, or FiveCardPlay");
  }
}
