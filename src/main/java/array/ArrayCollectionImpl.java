package array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Настя on 08.07.2017.
 */

/**
 * Class ArrayCollectionImpl implements the operations
 * of ArrayCollection interface.
 *
 * @author Anastasia Sokolan
 * @version 1.0
 * @since 2017-07-07.
 */
public class ArrayCollectionImpl<E> implements ArrayCollection<E> {
    /**
     *array of E class objects
     */
    private E[] elements;

    /**
     *size of array
     */
    private int size;

    /**
     * Class constructor creates empty object
     */
    public ArrayCollectionImpl() {
        size = 0;
        elements = (E[])(new Object[size]);
    }

    /**
     * The method returns E array in Object form.
     * @return Object[] array of objects
     */
     public Object[] getArray() {
        return (Object[])elements.clone();
    }

    /**
     * The method sets E array.
     * @param es array of E objects
     * @return Nothing
     * @exception NullPointerException in case
     * if es equals null.
     * @see NullPointerException
     */
     public void setArray(E[] es) {
        if (es == null) {
            throw new NullPointerException();
        }
        size = es.length;
        elements = (E[])new Object[size];
        System.arraycopy(es, 0, elements, 0, size);
    }

     public int size() {
        return size;
    }

     public boolean isEmpty() {
        return size == 0 ? true : false;
    }

     public boolean contains(Object o) {
        if (o == null) {
            for (E e : elements) {
                if (e == null) {
                    return true;
                }
            }
        }
        else {
            for (E e : elements) {
                if (e.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

     public Iterator<E> iterator() {
        return new ArrayIteratorImpl<E>();
    }

     public Object[] toArray() {
        return this.getArray();
    }

     public <T> T[] toArray(T[] ts) {
        if (ts == null) {
            throw new NullPointerException();
        }
        return ((T[]) elements).clone();
    }

     public boolean add(E e) {
        int oldSize = size;
        //E[] temp = Arrays.copyOf(elements, size);
        E[] temp = elements.clone();
        //elements = Arrays.copyOf(temp, ++size);
        elements = (E[])(new Object[++size]);
        for (int i = 0; i < size - 1; i++)
            elements[i] = temp[i];
        elements[size - 1] = e;
        return oldSize == size ? false : true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        int index;
        for (index = 0; index < size; index++) {
            if (elements[index].equals(o)) {
                break;
            }
        }
        return removeIndex(index);
    }

    /**
     * The method removes the object of elements
     * having certain index.
     * @param index
     * @return boolean true in case of success or
     * false otherwise.
     */
    private boolean removeIndex(int index) {
        if (index < size) {
            //E[] temp = Arrays.copyOf(elements, size);
            E[] temp = elements.clone();
            elements = (E[])(new Object[--size]);
            System.arraycopy(temp, 0, elements, 0, index);
            System.arraycopy(temp, index + 1, elements, index, size - index);
            return true;
        }
        return false;
    }

     public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        int checkSize = 0;
        for (int i = 0; i < size; i++) {
            if (collection.contains(elements[i])) {
                ++checkSize;
            }
        }
        return checkSize == collection.size() ? true : false;
    }

     public boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        if (collection.equals(this)) {
            throw new IllegalArgumentException();
        }
        if (!(collection.isEmpty())) {
            //E[] temp1 = Arrays.copyOf(elements, size);
            E[] temp1 = elements.clone();
            size += collection.size();
            elements = (E[]) (new Object[size]);
            System.arraycopy(temp1, 0, elements, 0, temp1.length);
            E[] temp2 = (E[]) (collection.toArray());
            System.arraycopy(temp2, 0, elements, temp1.length, temp2.length);
            return true;
        }
        return false;
    }

     public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        int oldSize = size;
        if (!(collection.isEmpty())) {
            for (int i = 0; i < size; i++) {
                if (collection.contains(elements[i])) {
                    this.removeIndex(i);
                    --i;
                }
            }
        }
        return oldSize == size ? false : true;
    }

    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        int oldSize = size;
        int index = 0;
        E[] temp = (E[])(new Object[size]);
        for (int i = 0; i < size; i++) {
            if (collection.contains(elements[i])) {
                temp[index] = elements[i];
                ++index;
            }
        }
        size = index;
        elements = (E[])(new Object[size]);
        System.arraycopy(temp, 0, elements, 0, size);
        return oldSize == size ? false : true;
    }

    public void clear() {
        size = 0;
        elements = (E[])(new Object[size]);
    }

    public class ArrayIteratorImpl<E> implements ArrayIterator<E> {
        /**
         * pointer for handling elements
         */
        private int pointer;

        /**
         * Class constructor creates empty object
         */
        public ArrayIteratorImpl() {
            pointer = -1;
        }

        /**
         * The method returns array of objects.
         * @return Object[]
         */
        public Object[] getArray() {
            return ArrayCollectionImpl.this.getArray();
        }

        public boolean hasNext() {
            return pointer < (size - 1) ? true : false;
        }

        public E next() {
            if (hasNext()) {
                return (E)elements[++pointer];
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (pointer < 0) {
                throw new IllegalStateException();
            }
            ArrayCollectionImpl.this.removeIndex(pointer);
            --pointer;
        }
    }
}
