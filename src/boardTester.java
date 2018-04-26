//Patrick Dundas cs110

public class boardTester {
    public static void main(String [] args){
        Deck d = new Deck();
        d.shuffle();
        Board b = new Board(d);
        System.out.println(b);
        System.out.println(b.getCard(0,0));
        System.out.println(b);
        if (Card.checkSet(b.getCard(0,0), b.getCard(0,1), b.getCard(0,2)))
            System.out.println("set");
        else
            System.out.println("not a set");

        System.out.println(d);
    }

}
