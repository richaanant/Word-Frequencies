
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import itsc2214.*;

/**
 * Project 2 - text analysis.
 * 
 * @author abhatna6@charlotte.edu
 */

public class TextDocument implements Document {

    private String fileName;
    private HashMap<String, Integer> hashTable;
    private int numUniqueWords;
    private int numTotalWords;

    /**
     * Creates a Hashtable by by having as an empty documment as a string.
     */
    public TextDocument() {
        this.hashTable = new HashMap<>();
        loadFromString("");
    }

    /**
     * @param fileName - the information where it contains the document.
     * Creates a Hashtable by reading the information of a file.
     */
    public TextDocument(String fileName) {
        this.hashTable = new HashMap<>();
        loadFromFile(fileName);
    }

    /**
     * Gets the file.
     * 
     * @return - returns a string of the file name.
     */
    @Override
    public String getFilename() {
        return this.fileName;
    }

    /**
     * @param fileName - the information the where it contains the document.
     * It tests that it the file exists by iterating each line.
     * It tests that the file does not exist by returning an empty
     * string.
     * @return - a boolean value indicating it is true that the file exists.
     * false - if the file does not exists.
     */
    @Override
    public boolean loadFromFile(String fileName) {
        try {
            this.fileName = fileName;
            java.util.List<String> lines = Files.readAllLines(Paths.get(fileName));
            StringBuilder k = new StringBuilder();
            for (String line : lines) {
                k.append(line).append(" ");
            }
            return loadFromString(k.toString().trim());
        } catch (IOException e) {
            return loadFromString("");
        }
    }

    /**
     * Creates a string array to split the data as a string.
     * Splits each word which removes anything that is.
     * Not an alphabetical from A-Z.
     * And Digit from (0 - 9).
     * Iterates each word which will add the word.
     * By removing each leading and trailing whitespaces.
     * 
     * @param data - the string as the the file data.
     * @return a boolean value indicating that is is true.
     *         That the word has been added sucessfully.
     */
    @Override
    public boolean loadFromString(String data) {
        // removes anything that is not an alphabetical from A-Z and Digit from (0 - 9)
        String[] words = data.split("[^a-zA-Z\\d]");
        for (int i = 0; i < words.length; i++) {
            addWord(words[i].trim());
        }
        return true;
    }

    /**
     * If the hashtable contains it key which is the word.
     * Than it returns the value of the key word of the string.
     * 
     * @param w - a given word of a string.
     * @return a integer value of the key word of the string.
     */
    public int frequencyCount(String w) {
        if (hashTable.containsKey(w)) {
            return hashTable.get(w);
        } else {
            return 0;
        }

    }

    /**
     * Adds the word based on the conditions.
     * if the word is null do nothing.
     * If word is empty and less than 3 characters.
     * Than do nothing.
     * Remove any each leading and trailing whitespaces.
     * If the word is in the hashtable.
     * Than store that word in the hashtable by.
     * Incrementing by the vlaue by 1.
     * If not, than stored the word.
     * with the value by 1 if it not seen the first time.
     * 
     * @param w - given word of the string.
     */
    public void addWord(String w) {
        if (w == null) {
            return;
        }
        if (w.length() == 0 || w.length() < 3) {
            return;
        }
        String newWord = w.trim().toLowerCase();

        if (hashTable.containsKey(newWord)) {
            int value = hashTable.get(newWord);
            hashTable.put(newWord, value + 1);
        } else {
            hashTable.put(newWord, 1);
            numUniqueWords++;

        }
        numTotalWords++;

    }

    /**
     * Contains the word if it exists or not.
     * 
     * @param w - given word of the string.
     * @return a boolean value which returns true if the word.
     *         Contains it in the hashtable.
     *         return false if it is not in the hashtable.
     */
    public boolean contains(String w) {
        return hashTable.containsKey(w);
    }

    /**
     * Gets the number of unique words in the table.
     * 
     * @return - returns an number of unique words in the table.
     */
    public int numUniqueWordsInTable() {
        return numUniqueWords;

    }

    /**
     * Gets the number of total number of words in the table.
     * 
     * @return an number of total nubmer of words in the table.
     */

    public int totalNumOfWords() {
        return numTotalWords;

    }

}
