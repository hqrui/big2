package big2;

interface AIPlayerStrategy {
    Play getPlay(Player player, Hand hand, DiscardPile d);
}
