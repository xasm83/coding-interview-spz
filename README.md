Real solved coding interview example. The tasks:

-For any two commits in the commit history of VCS find nearest common ancestor. Here:
commitHashes - all commits from latest to earlier;
parentHashes - stores parents of commit from commitHashes with the same index. Last value in this array is always
               null, because obviously initial commit have no ancestors.

If one commit is an ancestor of another, then return this commit.

Example:
        D - E
      /        \
A - B - C - F - J

Here for E and F nearest common ancestor is B.

Note that this task could be solved with complexity O(n)




-Find index of first occurrence of subarray into array.


-Write a method flattenInOrder to flatten a ternary tree and store it into an array. Traversal of the tree should be
done from left to right.


-Given recursive implementation of MyFolder rewrite it so it not throw an stack overflow exception on large queue.
