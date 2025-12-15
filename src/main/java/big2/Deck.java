package big2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final Card[] cards = new Card[52];

    public Deck() {
        final var suitOrder = Card.getSuitOrder();
        final var suits = Card.getSuitOrder().keySet();
        for(int i = 1; i <= 13; i++){
            for(char suit : suits){
                cards[(i-1) * 4 + suitOrder.get(suit)] = new Card(i, suit);
            }
        }
    }

    public static Hand[] deal() {
        final Deck deck = new Deck();
        List<Card> cardList = Arrays.asList(deck.cards);
        Collections.shuffle(cardList);
        Hand[] hands = new Hand[]{
            new Hand(cardList.subList(0,13).toArray(new Card[0])),
            new Hand(cardList.subList(13,26).toArray(new Card[0])),
            new Hand(cardList.subList(26,39).toArray(new Card[0])),
            new Hand(cardList.subList(39,52).toArray(new Card[0])),
        };
        for(int i = 0; i < 4; i++) hands[i].sort();
        return hands;
    }
}
