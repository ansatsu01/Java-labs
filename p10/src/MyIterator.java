import java.util.ArrayList;

class MyIterator<T> implements MyQueue.Iterator<T> {
    int index;
    ArrayList<T> list;

    MyIterator(ArrayList<T> arr) {
        index = 0;
        list = new ArrayList<>(arr);
    }

    @Override
    public T currentItem() throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException("");
        }
        return list.get(index);
    }

    @Override
    public void next() {
        index++;
    }

    @Override
    public boolean isDone() {
        return index >= list.size();
    }
}
