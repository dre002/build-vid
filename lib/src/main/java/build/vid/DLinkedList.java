public class DLinkedList<T> implements List<T>{
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T d, Node<T> n, Node<T> p) {
            data = d;
            next = n;
            prev = p;
        }
    }

    private int size;
    private Node<T> dummy;

    public DLinkedList() {
        size = 0;
        dummy = new Node<T>(null, dummy, dummy);
    }
    private Node<T> getNode(int i) {
        Node<T> n = dummy;
        if (i < size / 2) {
            n = dummy.next;
            for (int j = 0; j < i; j++) {
                n = n.next;
            }
        } else {
            for (int k = size; k > i; k--) {
                n = n.prev;
            }
        }
        return n;

    }

    @Override
    public boolean add(int i, T x) {
        if (i > size) {
            return false;
        }
        if (size == 0 ) {
            return addFront(x);
        }
        if (i == size) {
            return addBack(x);
        }
        Node<T> n = getNode(i);
        Node<T> addedNode = new Node<>(x, n, n.prev);
        addedNode.next.prev = addedNode;
        addedNode.prev.next = addedNode;
        size++;
        return true;
    }


    @Override
    public void set(int i, T x) {
        Node<T> n = getNode(i);
        n.data = x;
    }

    @Override
    public T remove(int i) {
        if (i > size - 1) {
            return null;
        }
        Node<T> n = getNode(i);
        n.prev.next = n.next;
        n.next.prev = n.prev;
        size--;
        return n.data;
    }

    @Override
    public boolean addFront(T x) {
        if (size == 0) {
            Node<T> addNode = new Node<>(x,dummy,dummy);
            dummy.next = addNode;
            dummy.prev = addNode;
            size++;
            return true;
        }
        else {
            Node<T> addedNode = new Node<>(x, dummy.next, dummy);
            Node<T> oldFirst = dummy.next;
            dummy.next = addedNode;
            oldFirst.prev = addedNode;
            size++;
            return true;
        }
    }

    @Override
    public boolean addBack(T x) {
        if (size == 0) {
            Node<T> addNode = new Node<> (x, dummy, dummy.prev);
            dummy.prev = addNode;
            dummy.next = addNode;
            size++;
            return true;
        }
        else {
            Node<T> addedNode = new Node<>(x, dummy, dummy.prev);
            Node<T> oldLast = dummy.prev;
            dummy.prev = addedNode;
            oldLast.next = addedNode;
            size++;
            return true;
        }
    }

    @Override
    public T removeFront() {
        if (isEmpty()) {
            return null;
        }
        Node<T> ret = dummy.next;

        if(size == 1){
            dummy.next = dummy;
            dummy.prev = dummy;
            Node<T> newHead = ret.next;
            dummy.next = newHead;
            newHead.prev = dummy;
        }
        else{
            Node<T> newHead = ret.next;
            dummy.next = newHead;
            newHead.prev = dummy;
        }
        size--;
        return ret.data;
    }

    @Override
    public T removeBack() {
        if (isEmpty()) {
            return null;
        }
        Node<T> ret = dummy.prev;
        if(size == 1) {
            dummy.next = dummy;
            dummy.prev =dummy;
        }
        else {
            Node<T> newTail = ret.prev;
            dummy.prev = newTail;
            newTail.next = dummy;
        }
        size--;
        return ret.data;
    }

    @Override
    public T find(T x) {
        Node<T> n = dummy.next;
        for(int i = 0; i<size; i++) {
            if (n.data.equals(x)) {
                return n.data;
            }
            n = n.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;
    }

    @Override
    public void zip(List<T> other) { //Daryn
        if(size != 0) {
            int i = 1;
            while (i < size + other.size()-1 && other.size() != 0) {
                this.add(i, other.removeFront());
                i = i + 2;
            }
        }
        if(other.size() != 0) {
            this.concatenate(other);
        }
    }

    @Override
    public void removeDuplicates() { //Justin
        Node<T> n = dummy.next;
        int i = 0;
        while(i < size) {
            int index = i+1;
            Node<T> comp = n.next;
            while (comp.data != null) {
                if (n.data.equals(comp.data)) {
                    remove(index);
                    index--;
                }
                index++;
                comp = comp.next;
            }
            i++;
            n = n.next;
        }


    }

    @Override
    public void concatenate(List<T> other) { //Daryn
        while(other.size() > 0) {
            this.addBack(other.removeFront());
        }
    }

    @Override
    public List<T> prefix(int i) {
        return null;
    }

    @Override
    public void promote(int i) { //Justin
        if(i>=size) {
            throw new IndexOutOfBoundsException("The index you chose is not a valid index for your list");
        }
        Node<T> head = getNode(i);
        head.prev.next = head.next;
        head.next.prev = head.prev;
        Node<T> oldHead = dummy.next;
        dummy.next = head;
        head.prev = dummy;
        head.next = oldHead;
        oldHead.prev = head;
    }

    @Override
    public void diff(List<T> A, List<T> B) {

    }
    public void printList() {
        Node<T> n = dummy.next;
        while(n.data !=null) {
            System.out.print(n.data + " ");
            n=n.next;
        }
        System.out.println();
    }
}