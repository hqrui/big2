package big2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DiscardPile {
  private final ArrayList<Play> plays = new ArrayList<>();

  boolean checkValid(Play p) {
    if (plays.isEmpty()) return true;
    Play lastNonPassPlay = getLastNonPassPlay();
    if (lastNonPassPlay == null || lastNonPassPlay.player == p.player) return true;
    else return p.canPlayOver(lastNonPassPlay);
  }

  Play getLastNonPassPlay() {
    for (int i = plays.size() - 1; i >= 0; i--) {
      Play play = plays.get(i);
      if (!(play instanceof PassPlay)) {
        return play;
      }
    }
    return null;
  }

  List<Play> getPlays() {
    return Collections.unmodifiableList(plays);
  }

  void addPlay(Play p) {
    plays.add(p);
  }
}
