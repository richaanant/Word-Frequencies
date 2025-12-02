import org.junit.*;
//import org.w3c.dom.Text;
import static org.junit.Assert.*;

/**
 * Unit test for the TextDocument class.
 */
public class TextDocumentTest {

    /**
     * testEmptyDoc() testing adding words by hand.
     */
    @Test
    public void testEmptyDoc() {
        TextDocument runner = new TextDocument();
        runner.addWord("Hello");
        runner.addWord("Hello");
        runner.addWord("World");

        int n = runner.numUniqueWordsInTable();
        assertEquals("Empty: numUniqueWordsInTable() is wrong", 2, n);

        n = runner.totalNumOfWords();
        assertEquals("Empty: totalNumOfWords() is wrong", 3, n);
    }

    /**
     * testAnotherEmptyDoc() testing if the document is empty.
     */
    @Test
    public void testAnotherEmptyDoc() {
        TextDocument runner = new TextDocument();
        runner.loadFromString("");
        int n = runner.numUniqueWordsInTable();
        assertEquals("Empty: numUniqueWordsInTable() is 0", 0, n);

        n = runner.totalNumOfWords();
        assertEquals("Empty: totalNumOfWords() is wrong", 0, n);

    }

    // TODO add your own test cases here
    /**
     * testLoadFromString() testing if the document contains words.
     */
    @Test
    public void testLoadFromString() {
        TextDocument runner = new TextDocument();
        runner.loadFromString(
                "Vice President Johnson, Mr. Speaker, Mr. Chief Justice, " 
                + "President Eisenhower, Vice president Nixon, "
                + "President Truman, Reverend Clergy, fellow citizens:\n"
                        + //
                        "");
        assertTrue(runner.contains("vice"));
        assertTrue(runner.contains("president"));
        assertTrue(runner.contains("johnson"));
        assertFalse(runner.contains("Mr. Speaker"));
        assertFalse(runner.contains("Mr. Chief Justice"));
        assertFalse(runner.contains("president eisenhower"));
        assertFalse(runner.contains("vice president nixon"));
        assertFalse(runner.contains("president truman"));
        assertFalse(runner.contains("reverend clergy"));
        assertFalse(runner.contains("fellow citizens"));
    }

    /**
     * testGetFileName() testing it if the file exists.
     */
    @Test
    public void testGetFileName() {
        TextDocument runner = new TextDocument("kennedy.txt");
        assertEquals("kennedy.txt", runner.getFilename());
    }

    /**
     * testFrequency() testing it the frequency of each word.
     * In the document.
     */
    @Test
    public void testFrequency() {
        TextDocument runner = new TextDocument();
        runner.loadFromString(
                "So let us begin anew--remembering on both sides");

        assertEquals(0, runner.frequencyCount("So"));
        assertEquals(1, runner.frequencyCount("let"));
        assertEquals(0, runner.frequencyCount("us"));
        assertEquals(1, runner.frequencyCount("anew"));
        assertEquals(1, runner.frequencyCount("remembering"));
        assertEquals(0, runner.frequencyCount("on"));
        assertEquals(1, runner.frequencyCount("both"));
        assertEquals(1, runner.frequencyCount("sides"));
    }

    /**
     * testWordNull() testing if the word in the string.
     * Is not Null.
     */
    @Test
    public void testWordNull() {
        TextDocument runner = new TextDocument();
        runner.addWord(null);
        assertEquals(0, runner.totalNumOfWords());
    }

    /**
     * testLoadFromFile() testing if the document contains words.
     * In the file.
     */
    @Test
    public void testLoadFromFile() {
        TextDocument runner = new TextDocument("kennedy.txt");
        runner.loadFromString("Let both sides explore what problems unite us instead");
        assertTrue(runner.contains("let"));
        assertTrue(runner.contains("both"));
        assertTrue(runner.contains("sides"));
        assertTrue(runner.contains("explore"));
        assertTrue(runner.contains("what"));
        assertTrue(runner.contains("problems"));
        assertTrue(runner.contains("unite"));
        assertFalse(runner.contains("us"));
        assertTrue(runner.contains("instead"));


    }

    /**
     * testLoadFromFileEmpty() testing if the doucment is empty.
     * Containing no words.
     */
    @Test
    public void testLoadFromFileEmpty() {
        TextDocument runner = new TextDocument("");
        assertFalse(runner.contains("Let"));
        assertFalse(runner.contains("both"));
        assertFalse(runner.contains("sides"));
        assertFalse(runner.contains("explore"));
        assertFalse(runner.contains("what"));
        assertFalse(runner.contains("problems"));
        assertFalse(runner.contains("unite"));
        assertFalse(runner.contains("us"));
        assertFalse(runner.contains("instead"));

    }

}
