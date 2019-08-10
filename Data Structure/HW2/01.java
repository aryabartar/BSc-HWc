import java.util.Scanner;

class Node {
    long key;
    long leftSubtreeSize;
    long rightSubtreeSize;
    Node parent;
    Node left;
    Node right;


    public Node(long key, Node parent, Node left, Node right) {
        this.key = key;
        this.parent = parent;
        this.left = left;
        this.right = right;
        leftSubtreeSize = 0;
        rightSubtreeSize = 0;
    }
}

class BST {
    Node root;
    long treeSize;

    public BST(Node root) {
        this.root = root;
        treeSize = 1;
    }

    public void insert(long key) {
        Node z = new Node(key, null, null, null);
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x.leftSubtreeSize += 1;
                x = x.left;
            } else {
                x.rightSubtreeSize += 1;
                x = x.right;
            }
        }

        z.parent = y;
        if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }

        treeSize += 1;
    }

    public void print(Node x) {
        if (x != null) {
            print(x.left);
            System.out.println(x.key + " | Left : " + x.leftSubtreeSize + " | Right : " + x.rightSubtreeSize);
            print(x.right);
        }
    }

    public long findIthBigNode(long location) {
        Node x = root;
        while (true) {
            if (x.rightSubtreeSize  + 1 == location) {
                return (x.key);
            }
            if (location <= x.rightSubtreeSize) {
                x = x.right;
            } else {
                location -= 1 + x.rightSubtreeSize;
                x = x.left;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isFirstInsert = true;
        long size = Long.parseLong(scanner.nextLine());
        BST bst = null;
        for (long i = 0; i < size; i++) {

            if (scanner.nextLong() == 1) {
                if (isFirstInsert) {
                    Node ali = new Node(scanner.nextLong(), null, null, null);
                    bst = new BST(ali);
                    isFirstInsert = false;
                } else {
                    bst.insert(scanner.nextLong());
                }
            } else {
                if (bst == null) {
                    System.out.println("No reviews yet");
                }
                else if (bst.treeSize < 3) {
                    System.out.println("No reviews yet");
                } else {
                    long ith =bst.treeSize / 3 ;
                    System.out.println(bst.findIthBigNode(ith));
                }
            }
        }

    }
}
