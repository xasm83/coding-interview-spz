package flatten;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeTest {
    Tree<Integer> tree = null;
    Tree<Integer> simpleTree = null;
    Tree<Integer> complexTree1 = null;
    Tree<Integer> complexTree2 = null;
    Tree<Integer> complexTree3 = null;

    @BeforeClass
    private void initTree() {
        simpleTree = new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.left(5);
            }
        };


        tree = new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.right(new Triple<Tree<Integer>>(getLeafNode(5), getLeafNode(3), getLeafNode(1)));
            }
        };

        complexTree1 = new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.right(new Triple<Tree<Integer>>(getLeafNode(5), getLeafNode(3), getComplexNode(4, 6, 8)));
            }
        };

        complexTree2 = new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.right(new Triple<Tree<Integer>>(getLeafNode(5), getLeafNode(3),
                        getMoreComplexNode(getComplexNode(11, 44, 5), 9, 77)));
            }
        };

        complexTree3 = new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.right(new Triple<Tree<Integer>>(getMoreComplexNode(getComplexNode(12, 33, 66), 76, 23), getLeafNode(3),
                        getMoreComplexNode(getComplexNode(11, 44, 5), 9, 77)));
            }
        };

    }

    private Tree<Integer> getLeafNode(final Integer value) {
        return new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.left(value);
            }
        };
    }

    private Tree<Integer> getComplexNode(final Integer left, final Integer middle, final Integer right) {
        return new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.right(new Triple<Tree<Integer>>(getLeafNode(left), getLeafNode(middle), getLeafNode(right)));
            }
        };
    }


    private Tree<Integer> getMoreComplexNode(final Tree<Integer> tree, final Integer middle, final Integer right) {
        return new Tree<Integer>() {
            @Override
            public Either<Integer, Triple<Tree<Integer>>> get() {
                return Either.right(new Triple<Tree<Integer>>(tree, getLeafNode(middle), getLeafNode(right)));
            }
        };
    }


    @Test
    public void treeTest() {
        MyFlattenTree<Integer> treeFlattener = new MyFlattenTree<Integer>();


        List<Integer> flatList = treeFlattener.flattenInOrder(simpleTree);
        Assert.assertEquals(new ArrayList<Integer>(
                Arrays.asList(5)), flatList);

        flatList = treeFlattener.flattenInOrder(tree);
        Assert.assertEquals(new ArrayList<Integer>(
                Arrays.asList(5, 3, 1)), flatList);

        flatList = treeFlattener.flattenInOrder(complexTree1);
        Assert.assertEquals(new ArrayList<Integer>(
                Arrays.asList(5, 3, 4, 6, 8)), flatList);

        flatList = treeFlattener.flattenInOrder(complexTree2);
        Assert.assertEquals(new ArrayList<Integer>(
                Arrays.asList(5, 3, 11, 44, 5,9,77)), flatList);

        flatList = treeFlattener.flattenInOrder(complexTree3);
        Assert.assertEquals(new ArrayList<Integer>(
                Arrays.asList(12,33,66,76,23,3,11,44,5,9,77)), flatList);
    }

}
