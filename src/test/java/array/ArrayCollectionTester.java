package array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Настя on 16.07.2017.
 */
public class ArrayCollectionTester extends Assert {
    ArrayCollection<Integer> collection;
    @Before
    public void init() {
        collection = new ArrayCollectionImpl<Integer>();
        Integer[] array = {1, 2, -3, null, 7, 5, -11};
        collection.setArray(array);
    }
    @Test
    public void testIsEmpty() {
        int size = collection.size();
        assertTrue("Wrong res. Collection is not empty", size > 0);
    }
    @Test
    public void testContains() {
        assertTrue("Null value is in colletion", collection.contains(null));
    }

    @After
    protected void destroy() {
        collection.clear();
    }
}
