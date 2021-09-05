import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class TreeTest {
    @Test
    void test() throws IOException {
        // Given
        var json = readResource("tree.json");

        // When
        var tree = TreeBuilder.fromJson(json);
        var result = tree.calculateBranchSums();

        // Then
        assertThat(result).containsExactly(15, 16, 18, 10, 11);
    }

    private String readResource(String name) throws IOException {
        var stream = getClass().getResourceAsStream(name);
        return new String(stream.readAllBytes());
    }
}
