package array;

import junit.framework.TestCase;

import java.util.Iterator;

/**
 * Created by Настя on 16.07.2017.
 */
public class ArrayIteratorTester extends TestCase {
    ArrayCollection<Integer> collection;
    Iterator<Integer> iter;
    protected void setUp() throws Exception {
        collection = new ArrayCollectionImpl<Integer>();
        Integer[] array = {1, 2, -3, 7, 5, -11};
        collection.setArray(array);
        iter = collection.iterator();
    }

    protected void tearDown() throws Exception {
        collection.clear();
    }
}
