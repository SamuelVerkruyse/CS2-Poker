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
        return "Royal Flush";
     if(straightFlush(happyending))
        return "Straight Flush";
     if(foak(happyending))
        return "Four of a Kind";
     if(thoak(happyending) && twoak(happyending))
        return "Full House";
     if(flush(happyending))
        return "Flush";
     if(straight(happyending))
        return "Straight";
     if(thoak(happyending))
        return "Three of a Kind";
     if(twoakonce(happyending))
        return "Two Pair";
     if(twoak(happyending))
        return "One Pair";
     else
        return "High Card";

     //return "TODO: String of Best Hand; may need helper methods";
   }
   public int handValueInt()
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
        return 10;
     if(straightFlush(happyending))
        return 9;
     if(foak(happyending))
        return 8;
     if(thoak(happyending) && twoak(happyending))
        return 7;
     if(flush(happyending))
        return 6;
     if(straight(happyending))
        return 5;
     if(thoak(happyending))
        return 4;
     if(twoakonce(happyending))
        return 3;
     if(twoak(happyending))
        return 2;
     else
        return 1;

     //return "TODO: String of Best Hand; may need helper methods";
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
   public boolean foak(Map<String, ArrayList<Integer>> happyending)
   {
     Collection<ArrayList<Integer>> allofdeez = happyending.values();
     if(allofdeez.size() != 4)
      return false;
    int minsize = 0;
    ArrayList<Integer> comparer = new ArrayList<Integer>();
     for(ArrayList<Integer> findsize: allofdeez)
     {
       if(findsize.size() > minsize)
       {
         minsize = findsize.size();
         comparer = findsize;
       }
     }
     int occurences = 0;
     int maxoccurences = 0;
     for(Integer whatever: comparer)
     {
       for(ArrayList<Integer> findsize: allofdeez)
       {
         for(Integer whateverdos: findsize)
         {
           if(whateverdos == whatever)
            occurences++;
         }
       }
       if(occurences > maxoccurences)
        maxoccurences = occurences;
      occurences = 0;
     }
     if(maxoccurences == 4)
      return true;
     return false;
   }
   public boolean flush(Map<String, ArrayList<Integer>> happyending)
   {
     if(happyending.size() == 1)
            return true;
     return false;
   }
   public boolean straight(Map<String, ArrayList<Integer>> happyending)
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
   public boolean thoak(Map<String, ArrayList<Integer>> happyending)
   {
     Collection<ArrayList<Integer>> allofdeez = happyending.values();
     if(allofdeez.size() < 3)
      return false;
    int minsize = 0;
    ArrayList<Integer> comparer = new ArrayList<Integer>();
     for(ArrayList<Integer> findsize: allofdeez)
     {
       if(findsize.size() > minsize)
       {
         minsize = findsize.size();
         comparer = findsize;
       }
     }
     int occurences = 0;
     int maxoccurences = 0;
     for(Integer whatever: comparer)
     {
       for(ArrayList<Integer> findsize: allofdeez)
       {
         for(Integer whateverdos: findsize)
         {
           if(whateverdos == whatever)
            occurences++;
         }
       }
       if(occurences > maxoccurences)
        maxoccurences = occurences;
      occurences = 0;
     }
     if(maxoccurences == 3)
      return true;
     return false;
   }
   public boolean twoakonce(Map<String, ArrayList<Integer>> happyending)
   {
     Collection<ArrayList<Integer>> allofdeez = happyending.values();
    int minsize = 0;
    ArrayList<Integer> comparer = new ArrayList<Integer>();
     for(ArrayList<Integer> findsize: allofdeez)
     {
       if(findsize.size() > minsize)
       {
         minsize = findsize.size();
         comparer = findsize;
       }
     }
     int occurences = 0;
     int maxoccurences = 0;
     int maxoccurencesdos = 0;
     for(Integer whatever: comparer)
     {
       for(ArrayList<Integer> findsize: allofdeez)
       {
         for(Integer whateverdos: findsize)
         {
           if(whateverdos == whatever)
            occurences++;
         }
       }
       if(occurences == maxoccurences)
        maxoccurencesdos = occurences;
       if(occurences > maxoccurences)
        maxoccurences = occurences;

      occurences = 0;
     }
     if(maxoccurencesdos == 2)
      return true;
     return false;
   }
   public boolean twoak(Map<String, ArrayList<Integer>> happyending)
   {
     Collection<ArrayList<Integer>> allofdeez = happyending.values();
    int minsize = 0;
    ArrayList<Integer> comparer = new ArrayList<Integer>();
     for(ArrayList<Integer> findsize: allofdeez)
     {
       if(findsize.size() > minsize)
       {
         minsize = findsize.size();
         comparer = findsize;
       }
     }
     int occurences = 0;
     int maxoccurences = 0;
     for(Integer whatever: comparer)
     {
       for(ArrayList<Integer> findsize: allofdeez)
       {
         for(Integer whateverdos: findsize)
         {
           if(whateverdos == whatever)
            occurences++;
         }
       }
       if(occurences > maxoccurences)
        maxoccurences = occurences;
      occurences = 0;
     }
     if(maxoccurences == 2)
      return true;
     return false;
   }

   public int compareTo(Object x){
      Hand other = (Hand)x;
      //TODO: Compare hands by ordering above; return -1, 1, or 0
      return this.handValueInt() - other.handValueInt();
   }
}
