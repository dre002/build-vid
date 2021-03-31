public interface List<T> {

    // The following seven list and deque operations shouldn't need description
    boolean add(int i, T x);
    void set(int i, T x);
    T remove(int i);

    boolean addFront(T x);
    boolean addBack(T x);

    T removeFront();
    T removeBack();

    // The fundamental set operation
    T find(T x);

    // bookkeeping
    boolean isEmpty();
    int size();

    /** empty the list. */
    void clear();

    // A few of less common operations. Each teammate is primary author for 2
    // of these. Each team must implement zip() and removeDuplicates().

    /** zip this list and other together. When done, other will be empty. The
    * entries in this and other will appear, alternating, in this list.
    * After the shorter of the two lists is depleted, the remaining entries
    * in the longer list will follow.
    */
     void zip(List<T> other);

    /** eliminate elements as necessary to eliminate all duplicates. Do not
    * otherwise change the order of the elements. When multiple elements
    * exist that compare equal, retain the first such element
    * in the list.
    */
    void removeDuplicates();

    /** append the entries in other to this list. leave other empty.
     */
    void concatenate(List<T> other);

    /** return a list of elements from index 0 up to i-1. This list should now
    * only contain the remaining elements (from i forward).
    */
    List<T> prefix(int i);

    /** move the entry at index i to the front of the list
     */
    void promote(int i);

    /** Previous contents of this list are cleared, and replaced with the
    *"difference" of the two argument lists. That is, an element will
    * appear in this if it appears in A but nothing that compares equal to
    * it is in B, or vice versa.
    */
    void diff(List<T> A, List<T> B);

}
