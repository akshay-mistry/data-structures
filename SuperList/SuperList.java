import java.util.EmptyStackException;

public class SuperList<E> {

    private ListNode<E> root;
    private ListNode<E> end;
    int size;

    public SuperList() {
        root = null;
        end = null;
        size = 0;
    }

    public SuperList(E value)
    {
        root = new ListNode<E>(value);
        end = root;
        size = 1;
    }

    public void add (E value) {
        ListNode<E> newNode = new ListNode<>(value);
        if (isEmpty()) {
            root = newNode;
            end = root;
        }
        else {
            end.setNext(newNode);
            newNode.setPrevious(end);
            end = newNode;
        }
        size++;
    }

    public void add (int index, E value) {
        ListNode<E> newNode = new ListNode<>(value);
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (isEmpty() || index == size) {
            add(value);
        }
        else if (index == 0) {
            root.setPrevious(newNode);
            newNode.setNext(root);
            root = newNode;
            size++;
        }
        else {
            ListNode<E> temp = root;
            for (int x = 0; x < index; x++) {
                temp = temp.getNext();
            }
            ListNode<E> previous = temp.getPrevious();
            newNode.setNext(temp);
            newNode.setPrevious(previous);
            previous.setNext(newNode);
            temp.setPrevious(newNode);
            size++;
        }
    }

    public void push(E value) {
        add(value);
    }

    public E pop() {
        E value = null;
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            value = end.getValue();
            if (size == 1) {
                root = null;
                end = null;
            }
            else {
                end = end.getPrevious();
                end.setNext(null);
            }
            size--;
        }
        return value;
    }


    public E poll() {
        E value = null;
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            value = root.getValue();
            if (size == 1) {
                root = null;
                end = null;
            }
            else {
                root = root.getNext();
                root.setPrevious(null);
            }
            size--;
        }
        return value;
    }

    public E stackPeek() {
        if(end != null)
            return end.getValue();
        return null;
    }

    public E queuePeek() {
        if(root != null)
            return root.getValue();
        return null;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            ListNode<E> temp = root;
            for (int x = 0; x < size; x++) {
                if (x == index) {
                    return temp.getValue();
                }
                temp = temp.getNext();
            }
        }
        return null;
    }


    public int size() {
        return size;
    }

    public void setSize(int value) {
        size = value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else if (index == 0) {
            return poll();
        }
        else if(index == size - 1) {
            return pop();
        }
        else {
            ListNode<E> temp = root;
            for (int x = 0; x < size; x++) {
                if (x == index) {
                    ListNode<E> node = temp;
                    temp.getPrevious().setNext(node.getNext());
                    temp.getNext().setPrevious(node.getPrevious());
                    size--;
                    return (E) temp;
                }
                temp = temp.getNext();
            }
        }
        return null;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        root = null;
        end = null;
    }

    public boolean contains(E value) {
        boolean found = false;
        ListNode<E> temp = root;
        for (int x = 0; x < size; x++) {
            if (temp.getValue() == value) {
                found = true;
            }
            temp = temp.getNext();
        }
        return found;
    }

    @Override
    public String toString() {
        String output = "";
        ListNode<E> temp = root;
        output+="[";
        for (int x = 0; x < size; x++) {
            if (temp != null) {
                output += temp.getValue();
            }
            temp = temp.getNext();
            if (x != size-1)
                output+=",";
        }
        output+="]";
        return output;
    }

    public class ListNode<E> implements Comparable<E> {

        private E value;
        private ListNode<E> next;
        private ListNode<E> previous;

        public ListNode(E value) {
            this.value = value;
            next = null;
        }

        public E getValue() {
            return value;
        }

        public ListNode<E> getPrevious() {
            return previous;
        }

        public ListNode<E> getNext() {
            return next;
        }

        public int size() {
            return 0;
        }

        public boolean hasPrevious() {
            return true;
        }

        public boolean hasNext() {
            return true;
        }
        public void setPrevious(ListNode<E> newNode) {
            previous = newNode;
        }

        public void setNext(ListNode<E> newNode) {
            next = newNode;
        }
        @Override
        public int compareTo(E o) {
            return 0;
        }
    }
}
