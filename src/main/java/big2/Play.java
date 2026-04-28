package big2;

import java.util.List;

abstract class Play {
  Player player;

  public abstract boolean canPlayOver(Play prev);

  public abstract List<Card> getCardList();

  @Override
  public String toString(){
    return String.join(" ", getCardList().stream().map(Card::toAbbrev).toList());
  }
}
