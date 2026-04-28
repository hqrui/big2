package big2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DiscardPile {
  private final ArrayList<Play> plays = new ArrayList<>();

  boolean checkValid(Play p) {
    if (plays.isEmpty()) return true;
    else return p.canPlayOver(plays.getLast());
  }

  List<Play> getPlays() {
    return Collections.unmodifiableList(plays);
  }

  void addPlay(Play p) {
    plays.add(p);
  }
}
