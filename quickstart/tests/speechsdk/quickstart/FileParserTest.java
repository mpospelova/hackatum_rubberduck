package speechsdk.quickstart;

import com.github.javaparser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileParserTest {

    @Test
    void parseSyntax() throws IOException, ParseException {
        String filePath = "/home/masha/development/hackatum/hackatum_rubberduck/quickstart/src/speechsdk/quickstart/TestFile.java";


        FileParser javaParser = new FileParser();
        javaParser.parseSyntax(filePath);
    }
}