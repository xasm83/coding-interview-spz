package iteration;

import java.util.Queue;

public class MyFolder<T, U> implements Folder<T, U> {
    public U fold(U u, Queue<T> ts, Function2<T, U, U> function) {
        if (u == null || ts == null || function == null)
            throw new IllegalArgumentException();

        if (ts.isEmpty()) {
            return u;
        }

        U result = u;
        T item;

        while ((item = ts.poll()) != null) {
            result = function.apply(item, result);
        }

        return result;
    }

    public U foldOld(U u, Queue<T> ts, Function2<T, U, U> function) {
        if (u == null || ts == null || function == null)
            throw new IllegalArgumentException();

        if (ts.isEmpty()) {
            return u;
        }

        return foldOld(function.apply(ts.poll(), u), ts, function);
    }
}