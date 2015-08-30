package findcommonancestor;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AncestorTest {

    @Test
    public void testDepthCalculation() {
        //TODO test if commit =null
        FindCommonAncestor finder = new MyFindCommonAncestor();
        String[] commits = {"G", "F", "E", "D", "C", "B", "A"};
        String[][] parents = {{"F", "D"}, {"E"}, {"B"}, {"C"}, {"B"}, {"A"}, null};

        String commit1 = "D";
        String commit2 = "G";
        String commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("D", commit);

        commit1 = "D";
        commit2 = "G";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("D", commit);


        commit1 = "A";
        commit2 = "A";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("A", commit);

        commit1 = "E";
        commit2 = "B";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("B", commit);

        commit1 = "E";
        commit2 = "G";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("E", commit);

        commit1 = "G";
        commit2 = "G";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("G", commit);

        commit1 = "B";
        commit2 = "B";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("B", commit);

        commit1 = "G";
        commit2 = "A";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("A", commit);


        commits = new String[]{"L", "K", "J", "I", "H", "G", "F", "E", "D", "C", "B", "A"};
        parents = new String[][]{{"K"}, {"I", "J"}, {"I"}, {"H"}, {"G", "C"}, {"E", "F"}, {"D"}, {"D"}, {"B"}, {"A"}, {"A"}, null};

        commit1 = "B";
        commit2 = "C";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("A", commit);


        commit1 = "A";
        commit2 = "F";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("A", commit);

        commit1 = "J";
        commit2 = "C";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("C", commit);


        commit1 = "E";
        commit2 = "H";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("E", commit);

        commit1 = "E";
        commit2 = "C";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("A", commit);

        commit1 = "K";
        commit2 = "J";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("J", commit);

        commits = new String[]{"F", "E", "D", "C", "B", "A"};
        parents = new String[][]{{"D","E"}, {"C","B"}, {"C","B"}, {"A"}, {"A"}, null};

        commit1 = "D";
        commit2 = "E";
        commit = finder.findCommonAncestor(commits, parents, commit1, commit2);
        Assert.assertEquals("C", commit);

    }
}
