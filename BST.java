public class BST {  //Binary Tree Representation in Java
    private class Node {
        private int key;
        private String data;
        private Node left;
        private Node right;

        public Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

    private Node root;

    public void orderPrint() { //Order Traversal
        if (root != null)
            orderPrintTree(root);
    }

    private void orderPrintTree(Node root) {
        if (root.left != null)
            orderPrintTree(root.left);
        System.out.println(root.key + " "); //inorder
        if (root.right != null)
            orderPrintTree(root.right);
    }

    public String searchRecursion(int key) { //Search using Recursion
        Node n = searchTreeRecursion(root, key);
        return (n == null ? null : n.data);
    }

    private Node searchTreeRecursion(Node root, int key) {
        if (root == null)
            return null;
        else if (key == root.key)
            return root;
        else if (key < root.key)
            return searchTreeRecursion(root.left, key);
        else
            return searchTreeRecursion(root.right, key);
    }

    public String searchIteration(int key) { //Search using iteration
        Node n = searchTreeIteration(root, key);
        return (n == null ? null : n.data);
    }

    private Node searchTreeIteration(Node root, int key) {
        Node trav = root;
        while (trav != null) {
            if (key == trav.key)
                return trav;
            else if (key < trav.key)
                trav = trav.left;
            else
                trav = trav.right;
        }
        return null;
    }

    public void insert(int key, String data) { //Insert
        Node newNode = new Node(key, data);
        if (root == null) {
            root = newNode;
            return;
        }
        Node parent = null;
        Node trav = root;

        while (trav != null) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else if (key > trav.key) {
                trav = trav.right;
            } else {
                trav.data = data;
                return;
            }
        }
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public String Delete(int key) {
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        if (trav == null) {
            return null;
        }
        String removedData = trav.data;
        deleteNode(trav, parent);
        return removedData;
    }

    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left == null || toDelete.right == null) {
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;
            }
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        } else {
            Node replacementParent = toDelete;
            Node replacement = toDelete.right;
            while (replacement.left != null) {
                replacementParent = replacement;
                replacement = replacement.left;
            }
            toDelete.key = replacement.key;
            toDelete.data = replacement.data;
            if (replacementParent.left == replacement) {
                replacementParent.left = replacement.right;
            } else {
                replacementParent.right = replacement.right;
            }
        }
    }

    public static void main (String[]args){
        BST tree = new BST();

        tree.insert(50, "Apple");
        tree.insert(30, "Banana");
        tree.insert(70, "Cherry");
        tree.insert(20, "Date");
        tree.insert(40, "Fig");
        tree.insert(60, "Grape");
        tree.insert(80, "Honeydew");

        System.out.println("Ordered Tree:");
        tree.orderPrint();
        System.out.println("--------------------");

        System.out.println("Searching for key 40: " + tree.searchRecursion(40));
        System.out.println("Searching for key 25 (using iteration): " + tree.searchIteration(25));
        System.out.println("--------------------");

        System.out.println("Deleting node with key 30: " + tree.Delete(30));
        System.out.println("Ordered Tree after deletion:");
        tree.orderPrint();
        System.out.println("--------------------");
    }
}




