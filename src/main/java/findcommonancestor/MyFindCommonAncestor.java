package findcommonancestor;

import java.util.*;

public class MyFindCommonAncestor implements FindCommonAncestor {
    public String findCommonAncestor(String[] commitHashes,
                                     String[][] parentHashes,
                                     String commitHash1,
                                     String commitHash2) {

        if (commitHash1.equals(commitHash2)) {
            return commitHash1;
        }
        Map<String, Integer> commitPositions = getHashPosition(commitHashes);
        Map<String, Integer> parentsDepth = getParentsDepths(commitPositions, parentHashes, commitHash1, commitHash2);

        TreeMap<Integer, Set<String>> groupedDepthsHash1 = groupParents(commitPositions, parentsDepth, parentHashes, commitHash1);
        TreeMap<Integer, Set<String>> groupedDepthsHash2 = groupParents(commitPositions, parentsDepth, parentHashes, commitHash2);

        Iterator<Integer> depthIterator1 = groupedDepthsHash1.keySet().iterator();
        Iterator<Integer> depthIterator2 = groupedDepthsHash2.keySet().iterator();

        int depth1 = depthIterator1.next();
        int depth2 = depthIterator2.next();

        while (depthIterator1.hasNext() || depthIterator2.hasNext()) {
            if (depth1 == depth2) {
                String commonCommit = compareSameDepthParents(groupedDepthsHash1.get(depth1), groupedDepthsHash2.get(depth2), commitPositions, commitHashes);
                if (commonCommit != null) {
                    return commonCommit;
                }
                depth2 = depthIterator2.next();
                depth1 = depthIterator1.next();
            } else if (depth1 > depth2) {
                depth2 = depthIterator2.next();
            } else {
                depth1 = depthIterator1.next();
            }
        }
        return groupedDepthsHash2.get(0).iterator().next();
    }

    private String compareSameDepthParents(Set<String> parents2,
                                           Set<String> parents1,
                                           Map<String, Integer> commitPositions,
                                           String[] commitHashes
    ) {
        int ancestorPosition = -1;
        for (String parent : parents1) {
            if (parents2.contains(parent)) {
                if (ancestorPosition == -1) {
                    ancestorPosition = commitPositions.get(parent);
                    continue;
                }
                ancestorPosition = Math.min(ancestorPosition, commitPositions.get(parent));
            }
        }
        return ancestorPosition != -1 ? commitHashes[ancestorPosition] : null;
    }

    TreeMap<Integer, Set<String>> groupParents(Map<String, Integer> commitPositions,
                                               Map<String, Integer> parentsDepth,
                                               String[][] parentHashes,
                                               String commitHash
    ) {
        TreeMap<Integer, Set<String>> groupedParents = new TreeMap<Integer, Set<String>>();
        String[] parents = getParent(commitPositions, commitHash, parentHashes);

        Deque<String> parentsToVisit = new LinkedList<String>();
        parentsToVisit.add(commitHash);
        if (parents != null) {
            parentsToVisit.addAll(Arrays.asList(parents));
        }

        while (!parentsToVisit.isEmpty()) {
            String parent = parentsToVisit.pop();
            String[] parentsOfParent = getParent(commitPositions, parent, parentHashes);
            if (parentsOfParent != null) {
                parentsToVisit.addAll(Arrays.asList(parentsOfParent));
            }
            int depthLevel = parentsDepth.get(parent);
            Set<String> levelParents = groupedParents.get(depthLevel);
            if (levelParents == null) {
                HashSet<String> newParents = new HashSet<String>();
                newParents.add(parent);
                groupedParents.put(depthLevel, newParents);
            } else {
                levelParents.add(parent);
            }
        }
        return groupedParents;
    }

    private Map<String, Integer> getParentsDepths(Map<String, Integer> commitPositions,
                                                  String[][] parentHashes,
                                                  String commitHash1,
                                                  String commitHash2) {
        LinkedList<String> visit = new LinkedList<String>();
        visit.push(commitHash1);
        visit.push(commitHash2);

        Map<String, Integer> allParentsDepth = new HashMap<String, Integer>();
        while (!visit.isEmpty()) {
            String commit = visit.peek();
            String[] parents = parentHashes[commitPositions.get(commit)];
            if (parents == null) {
                allParentsDepth.put(visit.pop(), 0);
            } else {
                for (String parent : parents) {
                    if (!allParentsDepth.containsKey(parent)) {
                        visit.push(parent);
                    }
                }
                if (commit.equals(visit.peek())) {
                    allParentsDepth.put(commit, getMinimalDepth(allParentsDepth, parents) - 1);
                    visit.pop();
                }
            }
        }
        return allParentsDepth;
    }

    private int getMinimalDepth(Map<String, Integer> allParentsDepth, String[] parents) {
        int minDepth = 0;
        for (String parent : parents) {
            int parentsDepth = allParentsDepth.get(parent);
            minDepth = minDepth < parentsDepth ? minDepth : parentsDepth;
        }
        return minDepth;
    }

    private String[] getParent(Map<String, Integer> commitPositions,
                               String commitHash,
                               String[][] parentHashes) {
        return parentHashes[commitPositions.get(commitHash)];
    }

    private Map<String, Integer> getHashPosition(String[] commitHashes) {
        Map<String, Integer> nodesPositionCache = new HashMap<String, Integer>();
        for (int i = 0; i < commitHashes.length; i++) {
            if (commitHashes[i] == null) {
                throw new IllegalArgumentException();
            }
            nodesPositionCache.put(commitHashes[i], i);
        }
        return nodesPositionCache;
    }
}
