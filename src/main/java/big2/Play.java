package big2;

abstract class Play {
  Player player;

  public abstract boolean canPlayOver(Play prev);
}
