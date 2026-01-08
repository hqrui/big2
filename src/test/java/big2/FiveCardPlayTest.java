package big2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiveCardPlayTest {
  FiveCardPlay stra5, str26, strta;
  FiveCardPlay flucj, fluca, flus9;
  FiveCardPlay fh32, fh45;
  FiveCardPlay q7;
  FiveCardPlay strfl;

  @BeforeEach
  void setUp() {
    try {
      // Straights
      // A, 2, 3, 4, 5 (Mixed suits) -> Raw sort: A(1), 2(2), 3, 4, 5. Top card: 5.
      stra5 =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("AD"),
                Card.fromAbbrev("2D"),
                Card.fromAbbrev("3H"),
                Card.fromAbbrev("4S"),
                Card.fromAbbrev("5C")
              });
      // 2, 3, 4, 5, 6 (Mixed suits) -> Raw sort: 2, 3, 4, 5, 6. Top card: 6.
      str26 =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("2C"),
                Card.fromAbbrev("3D"),
                Card.fromAbbrev("4H"),
                Card.fromAbbrev("5S"),
                Card.fromAbbrev("6C")
              });
      // T, J, Q, K, A (Mixed suits) -> Raw sort: A(1), T(10), J, Q, K. Top card: K.
      strta =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("TC"),
                Card.fromAbbrev("JD"),
                Card.fromAbbrev("QH"),
                Card.fromAbbrev("KS"),
                Card.fromAbbrev("AC")
              });

      // Flushes
      // J high flush
      flucj =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("3C"),
                Card.fromAbbrev("5C"),
                Card.fromAbbrev("7C"),
                Card.fromAbbrev("9C"),
                Card.fromAbbrev("JC")
              });
      // A high flush
      fluca =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("4C"),
                Card.fromAbbrev("6C"),
                Card.fromAbbrev("8C"),
                Card.fromAbbrev("TC"),
                Card.fromAbbrev("AC")
              });
      flus9 =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("3S"),
                Card.fromAbbrev("5S"),
                Card.fromAbbrev("7S"),
                Card.fromAbbrev("8S"),
                Card.fromAbbrev("9S")
              });

      // Full Houses
      // 33322 (Triplet 3)
      fh32 =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("3D"),
                Card.fromAbbrev("3C"),
                Card.fromAbbrev("3S"),
                Card.fromAbbrev("2D"),
                Card.fromAbbrev("2H")
              });
      // 44455 (Triplet 4)
      fh45 =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("4D"),
                Card.fromAbbrev("4C"),
                Card.fromAbbrev("4S"),
                Card.fromAbbrev("5D"),
                Card.fromAbbrev("5H")
              });

      // Quads
      // 77773 (Quad 7)
      q7 =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("7D"),
                Card.fromAbbrev("7C"),
                Card.fromAbbrev("7H"),
                Card.fromAbbrev("7S"),
                Card.fromAbbrev("3D")
              });

      // Straight Flush
      // 3, 4, 5, 6, 7 (All Diamonds)
      strfl =
          new FiveCardPlay(
              new Card[] {
                Card.fromAbbrev("3D"),
                Card.fromAbbrev("4D"),
                Card.fromAbbrev("5D"),
                Card.fromAbbrev("6D"),
                Card.fromAbbrev("7D")
              });

    } catch (InvalidPlayException e) {
      fail("FiveCardPlayTest failed to set up valid hands");
    }
  }

  @Test
  void testInvalidHand() {
    // Test invalid number of cards
    assertThrows(
        IllegalArgumentException.class,
        () -> new FiveCardPlay(new Card[] {Card.fromAbbrev("3D"), Card.fromAbbrev("4D")}),
        "Should throw on wrong array length");

    // Test garbage hand (no pattern)
    assertThrows(
        InvalidPlayException.class,
        () ->
            new FiveCardPlay(
                new Card[] {
                  Card.fromAbbrev("3D"),
                  Card.fromAbbrev("8C"),
                  Card.fromAbbrev("2H"),
                  Card.fromAbbrev("KH"),
                  Card.fromAbbrev("5S")
                }),
        "Should throw on random cards");

    // Test broken straight (missing middle card)
    assertThrows(
        InvalidPlayException.class,
        () ->
            new FiveCardPlay(
                new Card[] {
                  Card.fromAbbrev("3D"),
                  Card.fromAbbrev("4H"),
                  Card.fromAbbrev("6S"),
                  Card.fromAbbrev("7D"),
                  Card.fromAbbrev("8D")
                }),
        "Should throw on broken straight");
  }

  @Test
  void compareTo() {
    // 1. Compare Same Rank (Straight vs Straight)
    // stra5 (top 5) < str26 (top 6)
    assertTrue(
        stra5.compareTo(str26) < 0, "Straight ending in 5 should lose to Straight ending in 6");
    // str26 (top 6) < strta (top K)
    assertTrue(
        str26.compareTo(strta) < 0, "Straight ending in 6 should lose to Broadway (ending in K)");

    // 2. Compare Same Rank (Flush vs Flush)
    // flucj (high J) < fluca (high A)
    assertTrue(
        flucj.compareTo(fluca) < 0,
        "Jack high flush should lose to Ace high flush (of the same suit)");
    assertTrue(fluca.compareTo(flus9) < 0, "Clubs flush should lose to spades flush");

    // 3. Compare Same Rank (Full House vs Full House)
    // fh32 (triplet 3) < fh45 (triplet 4)
    assertTrue(fh32.compareTo(fh45) < 0, "Full house of 3s should lose to Full house of 4s");

    // 4. Compare Different Ranks (Hierarchy Check)
    // Straight (1) < Flush (10)
    assertTrue(strta.compareTo(flucj) < 0, "Straight should lose to Flush");

    // Flush (10) < Full House (20)
    assertTrue(fluca.compareTo(fh32) < 0, "Flush should lose to Full House");

    // Full House (20) < Quads (30)
    assertTrue(fh45.compareTo(q7) < 0, "Full House should lose to Quads");

    // Quads (30) < Straight Flush (100)
    assertTrue(q7.compareTo(strfl) < 0, "Quads should lose to Straight Flush");
  }
}
