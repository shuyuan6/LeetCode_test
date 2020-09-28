import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {

    class Node {
        public boolean isDir;
        public String content;
        public Map<String, Node> subNodes;
        public String name;
    }

    Node root;

    public FileSystem() {
        root = new Node();
        root.isDir = true;
        root.content = null;
        root.name = "/";
        root.subNodes = new HashMap<>();
    }

    public List<String> ls(String path) {
        String[] paths = path.split("/");
        Node curr = root;
        for (int i = 1; i < paths.length; i++) {
            curr = curr.subNodes.get(paths[i]);
        }
        return new ArrayList<>(curr.subNodes.keySet());
    }

    public void mkdir(String path) {
        String[] elements = path.split("/");
        Node curr = root;
        for (int i = 1; i < elements.length; i++) {
            if (!curr.subNodes.containsKey(elements[i])) {
                Node next = new Node();
                next.isDir = true;
                next.content = null;
                next.subNodes = new HashMap<>();
                next.name = elements[i];
                curr.subNodes.put(next.name, next);
                curr = next;
            } else {
                curr = curr.subNodes.get(elements[i]);
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] filePaths = filePath.split("/");
        Node curr = root;
        for (int i = 1; i < filePaths.length - 1; i++) {
            if (!curr.subNodes.containsKey(filePaths[i])) {
                Node next = new Node();
                next.isDir = true;
                next.content = null;
                next.subNodes = new HashMap<>();
                next.name = filePaths[i];
                curr.subNodes.put(next.name, next);
                curr = next;
            } else {
                curr = curr.subNodes.get(filePaths[i]);
            }
        }
        if (curr.subNodes.get(filePaths[filePaths.length - 1]) == null) {
            Node target = new Node();
            target.isDir = false;
            target.content = content;
            target.name = filePaths[filePaths.length - 1];
            curr.subNodes.put(target.name, target);
        } else {
            Node target = curr.subNodes.get(filePaths[filePaths.length - 1]);
            target.content += content;
        }
    }

    public String readContentFromFile(String filePath) {
        String[] filePaths = filePath.split("/");
        Node curr = root;
        for (int i = 1; i < filePaths.length; i++) {
            curr = curr.subNodes.get(filePaths[i]);
        }
        return curr.content;
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.mkdir("/a/b/d");
        fs.mkdir("/a/b/3");
        fs.mkdir("/a/b/f");
        fs.mkdir("/a/b/g");
        fs.mkdir("/a/b/c");
        fs.mkdir("/a/d/c");
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a"));
        System.out.println(fs.ls("/a/b/c"));


        fs.addContentToFile("/a/b/c/d/e", "hello");
        System.out.println(fs.readContentFromFile("/a/b/c/d/e"));
        fs.addContentToFile("/a/b/c/d/e", "hahaha");
        System.out.println(fs.readContentFromFile("/a/b/c/d/e"));
    }
}
