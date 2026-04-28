package big2;
import java.util.List;

class Game {
  public void run() {
    Hand[] hands = Deck.deal();
    Player[] players = new Player[4];
    for (int i = 0; i < 4; i++) {
      List<Card> cards = hands[i].getCards();
      players[i] = new AIPlayer(hands[i], new AIPlayerBasicStrategy());
      for (Card c : cards) System.out.print(c.toAbbrev() + " ");
      System.out.println();
    }

    DiscardPile discardPile = new DiscardPile();
    int curPlayer = 0;
    for (int turn = 1; turn <= 1000; turn++) {
      Play p = players[curPlayer].getNextPlay(discardPile);
      if (discardPile.checkValid(p)) {
        players[curPlayer].acceptPlay(p);
        discardPile.addPlay(p);
        if (p instanceof PassPlay) System.out.println("Player " + curPlayer + " passed");
        else System.out.println("Player " + curPlayer + " played " + p.toString());
      } else throw new InvalidDiscardException(p.toString());

      if (players[curPlayer].hasWon()) {
        System.out.println("Player " + curPlayer + " has won on turn " + turn);
        return;
      }
      curPlayer = (curPlayer + 1) % 4;
    }

    System.out.println("Game did not finish after 1000 turns");
  }
}
