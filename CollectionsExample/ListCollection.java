package CollectionsExample;

import java.util.*;

public class ListCollection {
    public static void main(String[] args) {

        List ntBooks = new LinkedList();
        ntBooks.add("Matthew");
        ntBooks.add("Mark");
        ntBooks.add("Luke");
        ntBooks.add("John");
        ntBooks.add("Acts");
        ntBooks.add("Romans");
        ntBooks.add("1 Corinthians");
        ntBooks.add("2 Corinthians");
        ntBooks.add("Galatians");
        ntBooks.add("Ephesians");
        ntBooks.add("Phillipians");
        ntBooks.add("Colossians")    ;
        ntBooks.add("1 Thessalonians");
        ntBooks.add("2 Thessalonians");
        ntBooks.add("1 Timothy");
        ntBooks.add("2 Timothy");
        ntBooks.add("Titus");
        ntBooks.add("Philemon");
        ntBooks.add("Hebrews");
        ntBooks.add("James");
        ntBooks.add("1 Peter");
        ntBooks.add("2 Peter");
        ntBooks.add("1 John");
        ntBooks.add("2 John");
        ntBooks.add("3 John");
        ntBooks.add("Jude");
        ntBooks.add("Revelation");
        System.out.println();
        System.out.println(" LinkedList Elements");
        System.out.println("\t" + ntBooks);

        //Find what book comes after Titus
        //First find the index of Titus
        int bookIndex = ntBooks.indexOf("Titus");
        System.out.println();
        System.out.println("Titus was book number " + ( bookIndex + 1) +" of the list.");


        //Display the next book
        System.out.println();
        System.out.println("The book after Titus is: " + ntBooks.get(bookIndex+1));
    }
}
