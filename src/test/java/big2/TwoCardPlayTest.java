package big2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoCardPlayTest {
    Card c3c = Card.fromAbbrev("3C");
    Card c3s = Card.fromAbbrev("3S");
    Card c3d = Card.fromAbbrev("3D");
    Card c3h = Card.fromAbbrev("3H");
    Card c2d = Card.fromAbbrev("2D");
    Card c2h = Card.fromAbbrev("2H");
    Card cjd = Card.fromAbbrev("JD");
    Card cjh = Card.fromAbbrev("JH");
    TwoCardPlay pair1, pair3cs, pair3sc, pair3dh, pairj, pair2;

    @BeforeEach
    void setUp() {
        try {
            pair3cs = new TwoCardPlay(new Card[]{c3c, c3s});
            pair3sc = new TwoCardPlay(new Card[]{c3s, c3c});
            pair3dh = new TwoCardPlay(new Card[]{c3d, c3h});
            pair2 = new TwoCardPlay(new Card[]{c2d, c2h});
            pairj = new TwoCardPlay(new Card[]{cjd, cjh});
        } catch (InvalidPlayException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void twoCardPlay() {
        assertThrows(Exception.class, () -> {
            pair1 = new TwoCardPlay(new Card[]{c3c, cjh});
        });
        assertThrows(Exception.class, () -> {
            pair1 = new TwoCardPlay(new Card[]{c2h, c3d});
        });
        assertDoesNotThrow(() -> {
            pair1 = new TwoCardPlay(new Card[]{c3c, c3d});
        });
    }

    @Test
    void compareTo() {
        assertEquals(0, pair3cs.compareTo(pair3sc));
        assertEquals(-1, pair3dh.compareTo(pair3cs));
        assertEquals(1, pair3cs.compareTo(pair3dh));
        assertEquals(-1, pair3cs.compareTo(pairj));
        assertEquals(-1, pairj.compareTo(pair2));
        assertEquals(1, pair2.compareTo(pair3cs));

    }
}