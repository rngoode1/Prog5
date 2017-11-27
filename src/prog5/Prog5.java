/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog5;
import lab7.BinarySearchTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author other
 */
public class Prog5 {
    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        for (int i = 0; i < dictionary.length; i++){
            dictionary[i] = new BinarySearchTree<String>();
        }
        Prog5 rd = new Prog5("random_dictionary.txt");
        Prog5 o = new Prog5("oliver.txt");
        rd.dictionary(dictionary);
        o.oliver(dictionary);
        System.out.println("Total Words Found: " + o.getWordsFound());
        System.out.println("Total Words Not Found: " + o.getWordsNotFound());
        System.out.println("Total Comparisons Found: " + o.getCompsFound());
        System.out.println("Total Comparisons Not Found: " + o.getCompsNotFound());
        long avgCompsFound = o.getCompsFound()/o.getWordsFound();
        long avgCompsNotFound = o.getCompsNotFound()/o.getWordsNotFound();
        System.out.println("Average Comparisons Per Word: " + avgCompsFound);
        System.out.println("Average Comparisons Per Word Not Found: " + avgCompsNotFound);
        
    }

    public long getWordsFound() {
        return wordsFound;
    }

    public long getWordsNotFound() {
        return wordsNotFound;
    }

    public long getCompsFound() {
        return compsFound;
    }

    public long getCompsNotFound() {
        return compsNotFound;
    }

    private String filename;
    long wordsFound = 0;
    long wordsNotFound = 0;
    long compsFound = 0;
    long compsNotFound = 0;

    public Prog5(String f) {
        this.filename = f;
    }

    public void dictionary(BinarySearchTree[] i) throws FileNotFoundException {
        File f1 = new File(filename);
        Scanner input = new Scanner(f1);
        while (input.hasNext()) {
            String line = input.next();
            String newline = line.toLowerCase();
            String line2 = newline.replaceAll("[^a-z ]", "");
            int a = line2.charAt(0);
            i[a-97].insert(line2);
        }
    }

    public void oliver(BinarySearchTree[] l) throws FileNotFoundException {
        File f1 = new File(filename);
        Scanner input = new Scanner(f1);
        int[] count = new int[1];
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String line1 = line.toLowerCase();
            String line2 = line1.replaceAll("[^a-z ]", "");
            String line3 = line2.trim();
            String[] words = line3.split(" ");
            for (int i = 0; i < words.length; i++) {
                String word = words[i].trim();
                if (word.length()!=0){
                    int a = words[i].charAt(0);
                    int b = a - 97;
                    if (l[b].search(words[i], count)) {
                        wordsFound++;
                        compsFound += count[0];
                    } 
                    else {
                        wordsNotFound++;
                        compsNotFound += count[0];
                    }
                }
            }
        }
    }
    
    
}
    
    

