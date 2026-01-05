package big2;

class SingleCardPlay implements Comparable<SingleCardPlay>{
    private final Card card;

    SingleCardPlay(Card card){
        this.card = card;
    }

    @Override
    public int compareTo(SingleCardPlay o) {
        return this.card.compareTo(o.card);
    }
}
