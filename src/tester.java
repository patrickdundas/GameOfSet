//Patrick Dundas cs110

public class tester {
    public static void main(String [] args){
        Card c1 = new Card(Card.Color.PURPLE, Card.Fill.SOLID, Card.Shape.OVAL, Card.Number.TWO);
        Card c2 = new Card(Card.Color.RED, Card.Fill.STRIPED, Card.Shape.TRIANGLE, Card.Number.ONE);
        Card c3 = new Card(Card.Color.GREEN, Card.Fill.OUTLINE, Card.Shape.DIAMOND, Card.Number.THREE);

        Card c4 = new Card(Card.Color.PURPLE, Card.Fill.SOLID, Card.Shape.OVAL, Card.Number.TWO);
        Card c5 = new Card(Card.Color.PURPLE, Card.Fill.SOLID, Card.Shape.OVAL, Card.Number.TWO);
        Card c6 = new Card(Card.Color.PURPLE, Card.Fill.SOLID, Card.Shape.OVAL, Card.Number.TWO);

        System.out.println(Card.checkSet(c1,c2,c3));
        System.out.println(Card.checkSet(c4,c5,c6));

        System.out.println(Card.checkSet(c1,c4,c3));

        Deck d = new Deck();

        System.out.println(d);
    }


}
