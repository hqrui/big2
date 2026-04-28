package big2;

import java.util.Collections;
import java.util.List;

class PassPlay extends Play {
  @Override
  public boolean canPlayOver(Play prev) {
    return true;
  }

  @Override
  public List<Card> getCardList() {
    return Collections.emptyList();
  }
}
