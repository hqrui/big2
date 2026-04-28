package big2;

enum Suit {
  DIAMONDS,
  CLUBS,
  HEARTS,
  SPADES;

  @Override
  public String toString() {
    return name().substring(0, 1);
  }
}
