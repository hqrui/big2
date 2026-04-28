package big2;

import java.util.Collections;
import java.util.List;

class PassPlay extends Play {
  public PassPlay(Player player){
    super(player);
  }

  @Override
  boolean canPlayOver(Play prev) {
    return true;
  }

  @Override
  List<Card> getCardList() {
    return Collections.emptyList();
  }
}
