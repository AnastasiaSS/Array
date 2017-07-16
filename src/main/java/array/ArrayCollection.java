package array;

import java.util.Collection;

/**
 * Created by Настя on 08.07.2017.
 */
public interface ArrayCollection<E> extends Collection<E> {
    void setArray(E[] array);
    Object[] getArray();
}
