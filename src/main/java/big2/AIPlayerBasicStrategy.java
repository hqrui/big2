package big2;

class AIPlayerBasicStrategy implements AIPlayerStrategy{
    public Play getPlay(Player player, Hand hand, DiscardPile d){
        Play lastNonPassPlay = d.getLastNonPassPlay();
        hand.sort();
        if (lastNonPassPlay == null || lastNonPassPlay instanceof PassPlay
        || lastNonPassPlay.player == player) {
            // Play the lowest card
            return new OneCardPlay(player, hand.getCards().getFirst());
        } else {
            for (Card c : hand.getCards()) {
                OneCardPlay s = new OneCardPlay(player, c);
                if (s.canPlayOver(lastNonPassPlay)) return s;
            }
            return new PassPlay(player);
        }
    }
}
