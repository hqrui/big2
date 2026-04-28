package big2;

import java.util.List;

abstract class Play {
  private final Player player;

  public Play(Player player){
    this.player = player;
  }

  abstract boolean canPlayOver(Play prev);

  abstract List<Card> getCardList();

  @Override
  public String toString() {
    return String.join(" ", getCardList().stream().map(Card::toAbbrev).toList());
  }
}
