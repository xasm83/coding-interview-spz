package iteration;

import findarray.MyFindArray;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FolderTest {

    @Test
    public void testBasicCases() {

        MyFolder<Integer, String> folderConcat = new MyFolder<Integer, String>();
        MyFolder<Integer, Integer> folderXor = new MyFolder<Integer, Integer>();
        XorFunction2 xorer = new XorFunction2();
        ConcatFunction2 concater = new ConcatFunction2();

        String seed = "test";
        Integer seedNumber = 55757555;

        Queue<Integer> integerQueue = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Queue<Integer> integerQueueOld = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertEquals(folderConcat.fold(seed, integerQueue, concater), folderConcat.foldOld(seed, integerQueueOld, concater));

        integerQueue = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        integerQueueOld = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertEquals(folderXor.fold(seedNumber, integerQueue, xorer), folderXor.foldOld(seedNumber, integerQueueOld, xorer));

        integerQueue = new LinkedList<Integer>();
        integerQueueOld = new LinkedList<Integer>();
        Assert.assertEquals(folderXor.fold(seedNumber, integerQueue, xorer), folderXor.foldOld(seedNumber, integerQueueOld, xorer));

        integerQueue = new LinkedList<Integer>(Arrays.asList(1));
        integerQueueOld = new LinkedList<Integer>(Arrays.asList(1));
        Assert.assertEquals(folderXor.fold(seedNumber, integerQueue, xorer), folderXor.foldOld(seedNumber, integerQueueOld, xorer));

    }
}
