import javax.swing.*;
import java.util.ArrayList;

// isEmpty
//	clear
//	equals
//	toString
//	возврата структуры для JList

//front – получить значение первого элемента
//	back – получить значение последнего элемента
//	push – добавить новый элемент в конец
//	pop – удалить элемент из начала
//	pushAll – добавить новые элементы в конец
public class MyQueue<T> implements MyIterable<T> {
    private ArrayList<T> list;
    MyQueue(){
        list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean equals(MyQueue<T> q) {
        return list.equals(q.list);
    }

    public String toString() {
        return list.toString();
    }

    public DefaultListModel<T> getModel() {
        DefaultListModel<T> model = new DefaultListModel<>();
        Iterator<T> iterator = createIterator();
        while (!iterator.isDone()){
            model.addElement(iterator.currentItem());
            iterator.next();
        }
        return model;
    }

    public T front() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        } else {
            return list.get(list.size() - 1);
        }
    }

    public T back() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        } else {
            return list.get(0);
        }
    }

    void push(T item) {
        list.add(0, item);
    }

    void pop() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        } else {
            list.remove(list.size() - 1);
        }
    }

    void pushAll(MyQueue<T> q) {
        list.addAll(0, q.getList());
    }

    private ArrayList<T> getList() {
        return list;
    }

    @Override
    public MyIterator<T> createIterator() {
        return new MyIterator<>(list);
    }

    interface Iterator<T> {
        T currentItem();

        void next();

        boolean isDone();
    }
}
