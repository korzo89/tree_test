import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        var data = Files.readString(Path.of(args[0]));

        var tree = TreeBuilder.fromJson(data);
        var sums = tree.calculateBranchSums();

        System.out.println(sums.toString());
    }
}
