package big2;

import java.util.Map;

public record Card(int value, char suit) implements Comparable<Card> {
    private final static Map<Character, Integer> letterToValue = Map.of(
            'A', 1,
            'J', 11,
            'Q', 12,
            'K', 13
    );
    private final static Map<Integer, Character> valueToLetter = Map.of(
            1, 'A',
            11, 'J',
            12, 'Q',
            13, 'K'
    );
    private final static Map<Character, Integer> suitOrder = Map.of(
            'D', 0,
            'C', 1,
            'H', 2,
            'S', 3
    );
    private static int big2Value(int value) {
        if(value == 1) return 14;
        else if(value == 2) return 15;
        else return value;
    }

    public Card {
        if (value < 1 || value > 13)
            throw new IllegalArgumentException("Card value must be between 1 and 13");
        if (!suitOrder.containsKey(suit))
            throw new IllegalArgumentException("Invalid suit");
    }

    public static Card fromAbbrev(String abbrev) {
        if (abbrev.length() > 3 || abbrev.length() < 2)
            throw new IllegalArgumentException("Card abbreviation must be 2 or 3 characters long");
        final int value;
        if (abbrev.length() == 2 && letterToValue.containsKey(abbrev.charAt(0)))
            value = letterToValue.get(abbrev.charAt(0));
        else try {
            value = Integer.parseInt(abbrev.substring(0, abbrev.length() - 1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Card value could not be parsed");
        }
        final char suit = Character.toUpperCase(abbrev.charAt(abbrev.length() - 1));
        return new Card(value, suit);
    }

    public String toAbbrev() {
        if (valueToLetter.containsKey(value)) {
            return String.valueOf(valueToLetter.get(value)) + suit;
        } else return String.valueOf(value) + suit;
    }

    public static Map<Character, Integer> getSuitOrder() {
        return suitOrder;
    }

    public int compareTo(Card other) {
        if (this.value == other.value) {
            if (this.suit == other.suit) return 0;
            else return (suitOrder.get(this.suit) < suitOrder.get(other.suit)) ? -1 : 1;
        } else return (big2Value(this.value) < big2Value(other.value)) ? -1 : 1;
    }
}