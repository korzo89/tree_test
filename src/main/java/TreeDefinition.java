import com.google.gson.Gson;
import lombok.Data;

import java.util.List;

@Data
public class TreeDefinition {
    private static final Gson gson = new Gson();

    private final List<TreeNodeDefinition> nodes;
    private final String root;

    public static TreeDefinition fromJson(String json) {
        return gson.fromJson(json, Wrapper.class).tree;
    }

    @Data
    private static class Wrapper {
        private final TreeDefinition tree;
    }
}
