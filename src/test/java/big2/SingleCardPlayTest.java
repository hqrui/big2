package big2;

import static org.junit.jupiter.api.Assertions.*;

class SingleCardPlayTest {

  private Card c1, c2, c3, c4, c5, c6;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    c1 = Card.fromAbbrev("3D");
    c2 = Card.fromAbbrev("2H");
    c3 = Card.fromAbbrev("JD");
    c4 = Card.fromAbbrev("JC");
    c5 = Card.fromAbbrev("JH");
    c6 = Card.fromAbbrev("JS");
  }

  @org.junit.jupiter.api.Test
  void compareTo() {
    assertEquals(-1, c1.compareTo(c2));
    assertEquals(-1, c1.compareTo(c3));
    assertEquals(1, c2.compareTo(c3));

    // compare suits
    assertEquals(-1, c3.compareTo(c4));
    assertEquals(1, c4.compareTo(c3));
    assertEquals(-1, c3.compareTo(c5));
    assertEquals(1, c6.compareTo(c5));
  }
}
