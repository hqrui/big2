package big2;

import java.util.Arrays;
import java.util.Comparator;

public class FiveCardPlay implements Comparable<FiveCardPlay>{
    private final Card[] cards;
    private final Card topCard;
    private final int rank;

    static boolean isStraight(Card[] cards){
        Arrays.sort(cards, Comparator.comparingInt(Card::getRawValue));
        // A2345, 23456, and TQJKA are allowed. QJKA2 is not allowed.
        int val = cards[0].getRawValue(); // raw number (A |-> 1, 2 |-> 2, 3 |-> 3 ... J |-> 11, Q |-> 12, K |-> 13)
        for(int i = 1; i < 5; i++){
            if(val + 1 == cards[i].getRawValue()){
                val++;
            }
            else{
                if(i == 1 && val == 1 && cards[i].getRawValue() == 10) val = 10; // Possible TJQKA
                else return false;
            }
        }
        return true;
    }

    static boolean isFlush(Card[] cards){
        char suit = cards[0].getSuit();
        for(int i = 1; i < 5; i++){
            if(cards[i].getSuit() != suit) return false;
        }
        return true;
    }

    static boolean isFullHouse(Card[] cards){
        Arrays.sort(cards);
        int smallVal = cards[0].getBig2Value();
        int bigVal = cards[4].getBig2Value();
        return (cards[1].getBig2Value() == smallVal &&
                (cards[2].getBig2Value() == smallVal || cards[2].getBig2Value() == bigVal) &&
                cards[3].getBig2Value() == bigVal);
    }

    static boolean isQuad(Card[] cards){
        Arrays.sort(cards);
        int smallVal = cards[0].getBig2Value();
        int bigVal = cards[4].getBig2Value();
        return (cards[1].getBig2Value() == cards[2].getBig2Value() &&
                cards[2].getBig2Value() == cards[3].getBig2Value() &&
                (cards[1].getBig2Value() == smallVal || cards[1].getBig2Value() == bigVal));
    }

    static boolean isStraightFlush(Card[] cards){
        return isFlush(cards) && isStraight(cards);
    }

    FiveCardPlay(Card[] cards) throws InvalidPlayException {
        if(cards.length != 5){
            throw new InvalidPlayException("FiveCardPlay must have exactly five cards");
        }
        if(isStraight(cards) || isFlush(cards) || isFullHouse(cards) || isQuad(cards)){
            this.cards = cards;
            if(isStraight(cards) && isFlush(cards)){
                this.rank = 100;
                // isStraight has caused the cards to be sorted in raw order which is correct for comparing straights
                this.topCard = cards[4];
            }
            else if(isStraight(cards)){
                this.rank = 1;
                this.topCard = cards[4];
            }
            else if(isFlush(cards)){
                Arrays.sort(cards);
                this.rank = 10;
                this.topCard = cards[4];
            }
            else if(isFullHouse(cards)){
                this.rank = 20;
                this.topCard = cards[2];
            }
            else if(isQuad(cards)){
                this.rank = 30;
                this.topCard = cards[2];
            }
            else throw new RuntimeException("Error creating FiveCardPlay");
        }
        else throw new InvalidPlayException("Not a valid set of 5 cards");
    }

    @Override
    public int compareTo(FiveCardPlay o) {
        if(this.rank == o.rank) return this.topCard.compareTo(o.topCard);
        else return this.rank - o.rank;
    }
}
