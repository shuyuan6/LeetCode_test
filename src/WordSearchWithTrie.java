import java.util.ArrayList;
import java.util.List;

public class WordSearchWithTrie {
    char[][] _board = null;
    ArrayList<String> result = new ArrayList<>();

    public void backTracking (int row, int col, TrieNode parent) {
        Character ch = this._board[row][col];
        TrieNode curr = parent.children.get(ch);

        if (curr.word != null) {
            this.result.add(curr.word);
            System.out.println("curr.word: " + curr.word);
            curr.word = null;
        }

        this._board[row][col] = '#';

        int[] rDirections = {0, -1, 0, +1};
        int[] cDirections = {-1, 0, +1, 0};

        for (int i = 0; i < 4; i++) {
            int nextRow = row + rDirections[i];
            int nextCol = col + cDirections[i];

            if (nextRow < 0 || nextRow >= this._board.length || nextCol < 0 || nextCol >= this._board[row].length || this._board[nextRow][nextCol] == '#') {
                continue;
            }
            if (curr.children.containsKey(this._board[nextRow][nextCol])) {
                backTracking(nextRow, nextCol, curr);
            }
        }
        this._board[row][col] = ch;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (Character ch : word.toCharArray()) {
                if (node.children.containsKey(ch)) {
                    node = node.children.get(ch);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(ch, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }

        this._board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (root.children.containsKey(board[i][j])) {
                    backTracking(i, j, root);
                }
            }
        }
        return this.result;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},
                          {'e','t','a','e'},
                          {'i','h','k','r'},
                          {'i','f','l','v'}};

        String[] words = {"oath","pea","eat","rain"};
        WordSearchWithTrie wswt = new WordSearchWithTrie();
        System.out.println(wswt.findWords(board, words));
    }

}


