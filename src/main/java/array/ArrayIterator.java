package array;

import java.util.Iterator;

/**
 * Created by Настя on 08.07.2017.
 */
public interface ArrayIterator<E> extends Iterator<E> {
    Object[] getArray();
}
