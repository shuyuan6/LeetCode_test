import java.util.HashMap;

public class TrieNode {

    public HashMap<Character, TrieNode> children = new HashMap<>();
    public String word = null;

    public TrieNode() {
    }
}
