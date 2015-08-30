package findarray;

import findarray.MyFindArray;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ArrayTest {
    private MyFindArray finder;

    @BeforeTest
    private void initFinder() {
        finder = new MyFindArray();
    }

    @Test
    public void testBasicCases() {
        int index = finder.findArray(new int[]{6, 7, 3, 4, 3, 4, 5, 6}, new int[]{3, 4, 5, 6});
        Assert.assertEquals(index, 4);

        index = finder.findArray(new int[]{6, 7, 3}, new int[]{6, 7, 3, 4, 3, 4, 5, 6});
        Assert.assertEquals(index, -1);

        index = finder.findArray(new int[]{6, 7, 3}, new int[]{});
        Assert.assertEquals(index, -1);

        index = finder.findArray(new int[]{}, new int[]{6, 7, 3});
        Assert.assertEquals(index, -1);

        index = finder.findArray(new int[]{6, 7, 3, 4, 3, 4, 5, 6}, new int[]{6, 6});
        Assert.assertEquals(index, -1);

        index = finder.findArray(new int[]{6, 7, 3, 4, 3, 4, 5, 6}, new int[]{6});
        Assert.assertEquals(index, 0);

        index = finder.findArray(new int[]{6, 7, 3, 4, 3, 4, 5, 6}, new int[]{9});
        Assert.assertEquals(index, -1);

        index = finder.findArray(new int[]{6, 7, 3, 4, 3, 4, 5, 6}, new int[]{5, 6});
        Assert.assertEquals(index, 6);

        index = finder.findArray(new int[]{6, 7, 3, 4, 3, 4, 5, 6}, new int[]{5, 6, 7});
        Assert.assertEquals(index, -1);
    }


}
