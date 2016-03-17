import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Michael Nguyen on 3/7/16.
 */
public class HashMap<KeyType, ValueType> {
    public HashMap(int tableSize) {
        this.tableSize = tableSize;
        map = (Node[]) Array.newInstance(Node.class, tableSize);
    }

    private int tableSize;
    private Node[] map;
    private int numberOfNodes = 0;

    public void insert(KeyType k, ValueType v) {
        int hash = Math.abs(k.hashCode()); //use Math.abs because hashCode sometimes gives
                                           // negative values
        int index = hash % tableSize;
        Node toInsert = new Node(k,v);


        if (map[index] == null) { //no node present at location
            map[index] = toInsert;
        } else if (map[index].mKey == k) { //keys are the same, override value
            map[index].mValue = v;
        } else { //node present, check to see if key is present within chain

            Node current = map[index];
            while (current != null) { //check chain to see if key is already present. if it is,
                // replace value with v
                if (current.mKey.equals(k)) {
                    current.mValue = v;
                    return;
                }
                current = current.mNext;
            }
            //key is not present, insert new Node as the head
            toInsert.mNext = map[index];
            map[index] = toInsert;
        }
        numberOfNodes ++;
    }

    public void remove(KeyType k) {
        int hash = Math.abs(k.hashCode());
        int index = hash % tableSize;

        Node current = map[index];
        Node previous = null;

        if (current.mNext == null) { //this node is the head, no children
            map[index] = null;
        } else {
            while (current != null) {
                if (current.mKey.equals(k)) {
                    //remove node

                    if (previous == null) { //this node is the head, but has children
                        map[index] = current.mNext;
                    } else { //this node is not the head
                        previous.mNext = current.mNext;
                    }

                    break;
                } else {
                    previous = current;
                    current = current.mNext;
                }
            }
        }

    }

    public ValueType find(KeyType k) throws RuntimeException {
        int hash = Math.abs(k.hashCode());
        int index = hash % tableSize;

        if (map[index] == null) { //head where node would be is empty
            throw new RuntimeException("No node found at the head");
        } else {
            Node current = map[index];
            while (current != null) { //iterate through chain to find key
                if (current.mKey.equals(k))
                    return current.mValue;
                current = current.mNext;
            }
            //iterated through chain, could not find key
            throw new RuntimeException("\"" + k.toString() + "\"" + " was not found");
        }
    }

    boolean containsKey(Object k) {
        int hash = Math.abs(k.hashCode());
        int index = hash % tableSize;

        Node current = map[index];
        while (current != null) {
            if (current.mKey.equals(k))
                return true;
            current = current.mNext;
        }
        return false;
    }

    int count() {
        return numberOfNodes;
    }

    public ArrayList<KeyType> keySet() {

        ArrayList<KeyType> keys = new ArrayList<KeyType>(numberOfNodes);

        for (int i = 0; i < tableSize; i++) {
            Node current = map[i];
            while (current != null) {
                keys.add(current.mKey);
                current = current.mNext;
            }
        }

        return keys;
    }

    private class Node {
        public KeyType mKey;
        public ValueType mValue;
        public Node mNext;

        public Node(KeyType k, ValueType v) {
            mKey = k;
            mValue = v;
            mNext = null;
        }
    }
}
