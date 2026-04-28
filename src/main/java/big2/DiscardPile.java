package big2;

import java.util.ArrayList;

public class DiscardPile {
  private final ArrayList<Play> plays = new ArrayList<>();
  private final int turn = 0;

  private boolean checkValid(Play p) {
    if (turn == 0) return true;
    else if (p.player != null && p.player == plays.getLast().player) return true;
    else return p.canPlayOver(plays.getLast());
  }
}
