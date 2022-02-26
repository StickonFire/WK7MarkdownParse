import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    
    @Test
    public void testFile2() throws IOException {
        String contents= Files.readString(Path.of("./test-file2.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testMissingCloseParen() {
        String contents= "[link title](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSpaceAroundLink() {
        String contents= "[link title](   a.com   )";
        List<String> expect = List.of("a.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    /**
     * Tests getLinks with snippet1.md
     */
    @Test
    public void testSnippet1(){
        List<String> snippet1Results = List.of("`google.com","google.com","ucsd.edu");
        assertEquals(snippet1Results,MarkdownParse.getLinks("snippet1.md"));
    }

    /**
     * Tests getLinks with snippet2.md
     */
    @Test
    public void testSnippet2(){
        List<String> snippet2Results = List.of("a.com","a.com(())","example.com");
        assertEquals(snippet2Results,MarkdownParse.getLinks("snippet2.md"));
    }

    /**
     * Tests getLinks with snippet3.md
     */
    @Test
    public void testSnippet3(){
        List<String> snippet3Results = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(snippet3Results,MarkdownParse.getLinks("snippet3.md"));
    }
}
