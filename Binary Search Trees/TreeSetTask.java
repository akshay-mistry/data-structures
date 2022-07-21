import java.util.ArrayList;
import java.util.Random;

public class TreeSetTask {

    TreeSet treeSet;

    public TreeSetTask() {

        //1-2
        treeSet = new TreeSet();
        System.out.print("Values: ");
        for (int i = 0; i < 10; i++) {
            int x;
            do {
                x = (int)(Math.random()*100) + 1;
            } while (treeSet.contains(x));
            System.out.print(x + " ");
            treeSet.add(x);
        }

        //3
        System.out.println("\nSize: " + treeSet.size() + "\n");

        //4-5
        System.out.println("PreOrder Traversal:");
        TreeSet preOrderSet = new TreeSet();
        String preOrderString = treeSet.preOrderString().replaceAll("\\[", "").replaceAll("\\]", "");
        String[] preArr = preOrderString.split(", ");
        for (int i = 0; i < preArr.length; i++) {
            int x = Integer.parseInt(preArr[i]);
            preOrderSet.add(x);
        }
        System.out.println("PreOrder: " + preOrderSet.preOrderString());
        System.out.println("InOrder: " + preOrderSet.inOrderString());
        System.out.println("PostOrder: " + preOrderSet.postOrderString());

        //6-7
        System.out.println("\nInOrder Traversal:");
        TreeSet inOrderSet = new TreeSet();
        String inOrderString = treeSet.inOrderString().replaceAll("\\[", "").replaceAll("\\]", "");
        String[] inArr = inOrderString.split(", ");
        for (int i = 0; i < inArr.length; i++) {
            int x = Integer.parseInt(inArr[i]);
            inOrderSet.add(x);
        }
        System.out.println("PreOrder: " + inOrderSet.preOrderString());
        System.out.println("InOrder: " + inOrderSet.inOrderString());
        System.out.println("PostOrder: " + inOrderSet.postOrderString());
        System.out.println("Observation: PreOrder and PostOrder outputs are the same; same InOrder as previous traversal.");

        //8-9
        System.out.println("\nPostOrder Traversal:");
        TreeSet postOrderSet = new TreeSet();
        String postOrderString = treeSet.postOrderString().replaceAll("\\[", "").replaceAll("\\]", "");
        String[] postArr = postOrderString.split(", ");
        for (int i = 0; i < postArr.length; i++) {
            int x = Integer.parseInt(postArr[i]);
            postOrderSet.add(x);
        }
        System.out.println("PreOrder: " + postOrderSet.preOrderString());
        System.out.println("InOrder: " + postOrderSet.inOrderString());
        System.out.println("PostOrder: " + postOrderSet.postOrderString());
        System.out.println("Observation: Same InOrder as previous traversal.");

        //10-11
        System.out.print("\nLetters: ");
        TreeSet letterTree = new TreeSet();
        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            System.out.print(c + " ");
            letterTree.add(c);
        }
        System.out.println("\nPreOrder: " + letterTree.preOrderString());
        System.out.println("InOrder: " + letterTree.inOrderString());
        System.out.println("PostOrder: " + letterTree.postOrderString());


        //12
        for (int i = 1; i < 4; i++) {
            System.out.println("\nRight Rotation " + i + ":");
            letterTree.rotateRight();
            System.out.println("PreOrder: " + letterTree.preOrderString());
            System.out.println("InOrder: " + letterTree.inOrderString());
            System.out.println("PostOrder: " + letterTree.postOrderString());
        }

        //13
        for (int i = 1; i < 4; i++) {
            System.out.println("\nLeft Rotation " + i + ":");
            letterTree.rotateLeft();
            System.out.println("PreOrder: " + letterTree.preOrderString());
            System.out.println("InOrder: " + letterTree.inOrderString());
            System.out.println("PostOrder: " + letterTree.postOrderString());
        }

        //14
        TreeSet intSet = new TreeSet();
        ArrayList<Integer> intList = new ArrayList<>();
        System.out.print("\nIntegers: ");
        for (int i = 0; i < 10; i++) {
            int x;
            do {
                x = (int)(Math.random()*100) + 1;
            } while (intSet.contains(x));
            System.out.print(x + " ");
            intList.add(x);
            intSet.add(x);
        }

        //15-16
        int removeIndex = (int)(Math.random()*intList.size());
        System.out.println("\nRemove Index: " + removeIndex + "; Remove Value: " + intList.get(removeIndex));
        int num = intList.remove(removeIndex);
        intSet.remove(num);
        System.out.println("PreOrder: " + intSet.preOrderString());
        System.out.println("InOrder: " + intSet.inOrderString());
        System.out.println("PostOrder: " + intSet.postOrderString());
    }

    public static void main(String[] args) {
        TreeSetTask treeSetTask = new TreeSetTask();
    }

    public class TreeSet<E extends Comparable<E>> {

        TreeNode<E> root;
        int size;
        String str;

        public TreeSet() {
            root = null;
            size = 0;
            str = "";
        }

        public int size() {
            return size;
        }

        public TreeNode<E> getRoot() {
            return root;
        }

        //ADD FUNCTION
        public void add(E value) {
            TreeNode<E> newNode = new TreeNode<E>(value);
            if (root == null) {
                root = newNode;
                size++;
            }
            else add(root, value);
        }
        public void add(TreeNode<E> root,E value) {
            TreeNode<E> currentNode = root;
            TreeNode<E> newNode = new TreeNode<E>(value);
            if(currentNode.getValue().equals(value))
                return;

            if (value.compareTo(currentNode.getValue()) < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    size++;
                    return;
                }
                else add(currentNode.getLeft(),value);
            }
            else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    size++;
                    return;
                }
                else add(currentNode.getRight(),value);
            }
        }

        //REMOVE FUNCTION
        public void remove(E value) {
            if (root == null) {
                return;
            }
            if (contains(root, value)) {
                if (root.getLeft() == null && root.getRight() == null) {
                    root = null;
                    size = 0;
                    return;
                }
                else {
                    size--;
                    root = remove(root, value);
                }
            }
        }
        public TreeNode<E> remove(TreeNode<E> root, E value) {
            TreeNode<E> currentNode = root;
            if (currentNode.getValue().equals(value)) {
                if (currentNode.getRight() == null && currentNode.getLeft() == null) {
                    currentNode = null;
                }
                else if (currentNode.getRight() == null) {
                    currentNode = currentNode.getLeft();
                }
                else if (currentNode.getLeft() == null) {
                    currentNode = currentNode.getRight();
                }
            }
            else if (value.compareTo(currentNode.getValue()) < 0)
                currentNode.setLeft(remove(currentNode.getLeft(), value));
            else if (value.compareTo(currentNode.getValue()) > 0)
                currentNode.setRight(remove(currentNode.getRight(), value));

            return currentNode;
        }

        //CONTAINS FUNCTION
        public boolean contains(E value) {
            return contains(root, value);
        }
        public boolean contains(TreeNode<E> root, E value) {
            TreeNode<E> currentNode = root;
            if (currentNode == null) {
                return false;
            }
            if (currentNode.getValue().equals(value)) {
                return true;
            }
            if (value.compareTo(currentNode.getValue()) < 0) {
                return contains(currentNode.getLeft(), value);
            }
            return contains(currentNode.getRight(), value);
        }

        //IN ORDER
        public void inOrder(TreeNode<E> node) {
            if (node != null) {
                inOrder(node.getLeft());
                str += node.getValue() + ", ";
                inOrder(node.getRight());
            }
        }
        public String inOrderString() {
            if (size == 0) {
                return "[]";
            }
            str = "[";
            inOrder(root);
            return str.substring(0, str.length()-2)+"]";
        }

        //PRE ORDER
        public void preOrder(TreeNode<E> node) {
            if (node != null) {
                str += node.getValue() + ", ";
                preOrder(node.getLeft());
                preOrder(node.getRight());
            }
        }
        public String preOrderString() {
            if (size == 0) {
                return "[]";
            }
            str = "[";
            preOrder(root);
            return str.substring(0, str.length()-2)+"]";
        }

        //POST ORDER
        public void postOrder(TreeNode<E> node) {
            if (node != null) {
                postOrder(node.getLeft());
                postOrder(node.getRight());
                str += node.getValue() + ", ";
            }
        }
        public String postOrderString() {
            if (size == 0) {
                return "[]";
            }
            str = "[";
            postOrder(root);
            return str.substring(0, str.length()-2)+"]";
        }

        //ROTATE LEFT
        public void rotateLeft() {
            rotateLeft(root);
        }
        public void rotateLeft(TreeNode<E> node) {
            if(node != null && node.getRight() != null) {
                TreeNode<E> tempNode = node.getRight();
                node.setRight(tempNode.getLeft());
                tempNode.setLeft(node);
                root = tempNode;
            }
        }

        //ROTATE RIGHT
        public void rotateRight() {
            rotateRight(root);
        }
        private void rotateRight(TreeNode<E> node) {
            if(node != null && node.getLeft() != null) {
                TreeNode<E> tempNode = node.getLeft();
                node.setLeft(tempNode.getRight());
                tempNode.setRight(node);
                root = tempNode;
            }
        }

        public class TreeNode<E extends Comparable<E>> {

            E value;
            TreeNode<E> leftChild;
            TreeNode<E> rightChild;

            public TreeNode(E value) {
                this.value = value;
                leftChild = null;
                rightChild = null;
            }

            public TreeNode<E> getRight() {
                return rightChild;
            }

            public TreeNode<E> getLeft() {
                return leftChild;
            }

            public void setRight(TreeNode<E> node) {
                rightChild = node;
            }

            public void setLeft(TreeNode<E> node) {
                leftChild = node;
            }

            public E getValue() {
                return value;
            }

            @Override
            public String toString() {
                return (String) value;
            }
        }
    }
}