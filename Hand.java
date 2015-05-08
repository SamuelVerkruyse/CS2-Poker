import java.util.*;

public class Hand implements Comparable {
   private ArrayList<Card> hand;

   public Hand(){
      hand = new ArrayList<Card>();
   }

   public void add(Card c){
      hand.add(c);
   }

   public void sortHand(){
      Collections.sort(hand);
   }

   public String toString(){
      return hand.toString();
   }

   /*
   BEST
   Royal Flush
   Straight Flush
   Four of a Kind
   Full House
   Flush
   Straight
   Three of a Kind
   Two Pair
   One Pair
   High Card
   WORST
   */
   public String handValue()
   {
     Collections.sort(hand);
     Map<String, ArrayList<Integer>> happyending = new HashMap<String, ArrayList<Integer>>();
     for(Card ref: hand)
     {
        if(!happyending.containsKey(ref.suit))
        {
          ArrayList<Integer> original = new ArrayList<Integer>();
          original.add(ref.value);
          happyending.put(ref.suit, original);
        }
        else
        {
          ArrayList<Integer> appender = happyending.get(ref.suit);
          appender.add(ref.value);
          happyending.put(ref.suit, appender);
        }
     }
     if(royalFlush(happyending))
        System.out.println(hand);
     if(straightFlush(happyending))
        System.out.println(hand);

     return "TODO: String of Best Hand; may need helper methods";
   }

   public boolean royalFlush(Map<String, ArrayList<Integer>> happyending)
   {
     if(happyending.size() == 1)
     {
       ArrayList<Integer> start = happyending.get(hand.get(0).suit);
       int starter = start.get(0);
       if(starter != 10)
        return false;
       for(int i = 1; i < hand.size(); i++)
       {
         if(start.get(0) != starter + 1)
          return false;
       }
       return true;
     }
     return false;
   }
   public boolean straightFlush(Map<String, ArrayList<Integer>> happyending)
   {
     if(happyending.size() == 1)
     {
       ArrayList<Integer> start = happyending.get(hand.get(0).suit);
       int starter = start.get(0);
       for(int i = 1; i < hand.size(); i++)
       {
         if(start.get(0) != starter + 1)
          return false;
       }
       return true;
     }
     return false;
   }

   public int compareTo(Object x){
      Hand other = (Hand)x;
      //TODO: Compare hands by ordering above; return -1, 1, or 0
      return 0;
   }
}
