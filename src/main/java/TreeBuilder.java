import java.util.Optional;
import java.util.stream.Collectors;

public class TreeBuilder {
    public static TreeNode fromJson(String json) {
        var treeDefinition = TreeDefinition.fromJson(json);

        var nodes = treeDefinition.getNodes()
            .stream()
            .map(x -> new TreeNode(x.getId(), x.getValue()))
            .collect(Collectors.toMap(TreeNode::getId, x -> x));

        treeDefinition.getNodes().forEach(nodeDefinition -> {
            var node = nodes.get(nodeDefinition.getId());

            Optional.ofNullable(nodeDefinition.getLeft())
                .map(nodes::get)
                .ifPresent(node::setLeft);

            Optional.ofNullable(nodeDefinition.getRight())
                .map(nodes::get)
                .ifPresent(node::setRight);
        });

        return nodes.get(treeDefinition.getRoot());
    }
}
