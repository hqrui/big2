package big2;

import static org.junit.jupiter.api.Assertions.*;

class SingleCardPlayTest {

    private Card c1, c2, c3;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        c1 = Card.fromAbbrev("3D");
        c2 = Card.fromAbbrev("2H");
        c3 = Card.fromAbbrev("JC");
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        assertEquals(-1, c1.compareTo(c2));
        assertEquals(-1, c1.compareTo(c3));
        assertEquals(1, c2.compareTo(c3));
    }
}