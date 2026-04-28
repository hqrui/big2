package big2;

import java.util.Arrays;
import java.util.Comparator;

public class FiveCardPlay implements Comparable<FiveCardPlay> {
  private final Card[] cards;
  private final Card topCard;
  private final Combination rank;

  enum Combination {
    STRAIGHT,
    FLUSH,
    FULLHOUSE,
    QUAD,
    STRAIGHTFLUSH;
  }

  static boolean isStraight(Card[] cards) {
    if (cards == null || cards.length != 5)
      throw new IllegalArgumentException("Must provide array of 5 cards");
    Arrays.sort(cards, Comparator.comparingInt(Card::getRawValue));
    // A2345, 23456, and TQJKA are allowed. QJKA2 is not allowed.
    int val =
        cards[0]
            .getRawValue(); // raw number (A |-> 1, 2 |-> 2, 3 |-> 3 ... J |-> 11, Q |-> 12, K |->
    // 13)
    for (int i = 1; i < 5; i++) {
      if (val + 1 == cards[i].getRawValue()) {
        val++;
      } else {
        if (i == 1 && val == 1 && cards[i].getRawValue() == 10) val = 10; // Possible TJQKA
        else return false;
      }
    }
    return true;
  }

  static boolean isFlush(Card[] cards) {
    if (cards == null || cards.length != 5)
      throw new IllegalArgumentException("Must provide array of 5 cards");
    Suit suit = cards[0].getSuit();
    for (int i = 1; i < 5; i++) {
      if (cards[i].getSuit() != suit) return false;
    }
    return true;
  }

  static boolean isFullHouse(Card[] cards) {
    if (cards == null || cards.length != 5)
      throw new IllegalArgumentException("Must provide array of 5 cards");
    Arrays.sort(cards);
    int smallVal = cards[0].getBig2Value();
    int bigVal = cards[4].getBig2Value();
    return (cards[1].getBig2Value() == smallVal
        && (cards[2].getBig2Value() == smallVal || cards[2].getBig2Value() == bigVal)
        && cards[3].getBig2Value() == bigVal);
  }

  static boolean isQuad(Card[] cards) {
    if (cards == null || cards.length != 5)
      throw new IllegalArgumentException("Must provide array of 5 cards");
    Arrays.sort(cards);
    int smallVal = cards[0].getBig2Value();
    int bigVal = cards[4].getBig2Value();
    return (cards[1].getBig2Value() == cards[2].getBig2Value()
        && cards[2].getBig2Value() == cards[3].getBig2Value()
        && (cards[1].getBig2Value() == smallVal || cards[1].getBig2Value() == bigVal));
  }

  static boolean isStraightFlush(Card[] cards) {
    return isFlush(cards) && isStraight(cards);
  }

  FiveCardPlay(Card[] cards) throws InvalidPlayException {
    if (cards == null || cards.length != 5) {
      throw new IllegalArgumentException("FiveCardPlay must have exactly five cards");
    }
    this.cards = cards;
    if (isStraight(cards) && isFlush(cards)) {
      this.rank = Combination.STRAIGHTFLUSH;
      // isStraight has caused the cards to be sorted in raw order which is correct for comparing
      // straights
      this.topCard = cards[4];
    } else if (isStraight(cards)) {
      this.rank = Combination.STRAIGHT;
      this.topCard = cards[4];
    } else if (isFlush(cards)) {
      Arrays.sort(cards);
      this.rank = Combination.FLUSH;
      this.topCard = cards[4];
    } else if (isFullHouse(cards)) {
      this.rank = Combination.FULLHOUSE;
      this.topCard = cards[2];
    } else if (isQuad(cards)) {
      this.rank = Combination.QUAD;
      this.topCard = cards[2];
    } else throw new InvalidPlayException("Not a valid set of 5 cards");
  }

  @Override
  public int compareTo(FiveCardPlay o) {
    if (this.rank == o.rank) {
      if (this.rank == Combination.FLUSH && this.topCard.suit() != o.topCard.suit()) {
        return this.topCard.suit().compareTo(o.topCard.suit());
      } else return this.topCard.compareTo(o.topCard);

    } else return this.rank.compareTo(o.rank);
  }
}
