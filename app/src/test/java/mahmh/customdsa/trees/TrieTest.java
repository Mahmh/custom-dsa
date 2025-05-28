package mahmh.customdsa.trees;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
        trie.store(List.of(
            "cat", "cut", "card", "car", 
            "interface", "internet", "crust", "cream"
        ));
    }

    @Test
    void testExactWordCompletion() {
        List<String> results = trie.complete("car");
        assertTrue(results.contains("car"));
        assertTrue(results.contains("card"));
        assertEquals(2, results.size());
    }

    @Test
    void testMultipleMatches() {
        List<String> results = trie.complete("c");
        assertTrue(results.containsAll(List.of("cat", "cut", "car", "card", "cream", "crust")));
        assertEquals(6, results.size());
    }

    @Test
    void testPartialPrefix() {
        List<String> results = trie.complete("cr");
        assertTrue(results.contains("cream"));
        assertTrue(results.contains("crust"));
        assertEquals(2, results.size());
    }

    @Test
    void testSingleMatch() {
        List<String> results = trie.complete("interf");
        assertEquals(List.of("interface"), results);
    }

    @Test
    void testFullWordPrefix() {
        List<String> results = trie.complete("internet");
        assertEquals(List.of("internet"), results);
    }

    @Test
    void testNoMatch() {
        List<String> results = trie.complete("xyz");
        assertTrue(results.isEmpty());
    }

    @Test
    void testEmptyPrefixReturnsAllWords() {
        List<String> results = trie.complete("");
        assertEquals(8, results.size());
        assertTrue(results.containsAll(List.of("cat", "cut", "car", "card", "cream", "crust", "interface", "internet")));
    }

    @Test
    void testCaseSensitiveBehavior() {
        trie.store("Cat");
        List<String> results = trie.complete("C");
        assertTrue(results.contains("Cat"));
        assertFalse(results.contains("cat")); // since "cat" is lowercase
    }
}