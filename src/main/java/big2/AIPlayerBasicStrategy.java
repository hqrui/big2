package big2;

import java.util.List;

class AIPlayerBasicStrategy implements AIPlayerStrategy{
    public Play getPlay(Player player, Hand hand, DiscardPile d){
        List<Play> prevPlays = d.getPlays();
        hand.sort();
        if (prevPlays.isEmpty() || prevPlays.getLast() instanceof PassPlay) {
            // Play the lowest card
            return new SingleCardPlay(player, hand.getCards().getFirst());
        } else {
            for (Card c : hand.getCards()) {
                SingleCardPlay s = new SingleCardPlay(player, c);
                if (s.canPlayOver(prevPlays.getLast())) return s;
            }
            return new PassPlay(player);
        }
    }
}
