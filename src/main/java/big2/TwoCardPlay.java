package big2;

class TwoCardPlay implements Comparable<TwoCardPlay>{
    private final Card[] cards;

    TwoCardPlay(Card[] cards) throws InvalidPlayException {
        if(cards.length != 2){
            throw new InvalidPlayException("TwoCardPlay must have exactly two cards");
        }
        if(cards[0].value() != cards[1].value()){
            throw new InvalidPlayException("The two cards must have the same number");
        }
        this.cards = cards;
    }

    @Override
    public int compareTo(TwoCardPlay o) {
        return this.cards[0].compareTo(o.cards[0]);
    }
}
