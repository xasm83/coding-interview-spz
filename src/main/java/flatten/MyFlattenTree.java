package flatten;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MyFlattenTree<T> implements FlattenTree<T> {
    private List<T> flattenedTree;
    private Deque<Tree<T>> traverseQueue;
    private NodeHandler nodeHandler = new NodeHandler();
    private LeafHandler leafHandler = new LeafHandler();

    public List<T> flattenInOrder(Tree<T> tree) {
        flattenedTree = new LinkedList<T>();
        traverseQueue = new LinkedList<Tree<T>>();

        if (null == tree) {
            throw new IllegalArgumentException();
        }

        Either<T, Triple<Tree<T>>> treeNode = tree.get();
        if (treeNode.isLeft()) {
            treeNode.ifLeft(leafHandler);
            return flattenedTree;
        } else {
            treeNode.ifRight(nodeHandler);
        }

        while (!traverseQueue.isEmpty()) {
            treeNode = traverseQueue.pop().get();
            if (treeNode.isLeft()) {
                treeNode.ifLeft(leafHandler);
            } else {
                treeNode.ifRight(nodeHandler);
            }
        }
        return flattenedTree;
    }

    private class LeafHandler implements Function<T, Boolean> {
        @Override
        public Boolean apply(T t) {
            flattenedTree.add(t);
            return !traverseQueue.isEmpty();
        }
    }

    private class NodeHandler implements Function<Triple<Tree<T>>, Boolean> {
        @Override
        public Boolean apply(Triple<Tree<T>> t) {
            traverseQueue.push(t.right());
            traverseQueue.push(t.middle());
            traverseQueue.push(t.left());
            return true;
        }
    }

}