import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class TreeNode {
    private final String id;
    private final int value;

    private TreeNode left;
    private TreeNode right;

    public List<Integer> calculateBranchSums() {
        var sums = Stream
            .concat(
                getBranchSums(left).stream(),
                getBranchSums(right).stream()
            )
            .map(x -> x + value)
            .collect(Collectors.toList());

        return sums.isEmpty() ? List.of(value) : sums;
    }

    private List<Integer> getBranchSums(TreeNode branch) {
        return Optional.ofNullable(branch)
            .map(TreeNode::calculateBranchSums)
            .orElseGet(Collections::emptyList);
    }
}
