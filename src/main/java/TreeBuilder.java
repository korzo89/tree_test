import com.google.gson.Gson;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TreeBuilder {
    private static final Gson gson = new Gson();

    @Data
    private static class TreeWrapper {
        private final TreeDefinition tree;
    }

    @Data
    private static class TreeDefinition {
        private final List<TreeNodeDefinition> nodes;
        private final String root;
    }

    @Data
    private static class TreeNodeDefinition {
        private final String id;
        private final String left;
        private final String right;
        private final int value;
    }

    public static TreeNode fromJson(String json) {
        var treeDefinition = gson.fromJson(json, TreeWrapper.class).getTree();

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
