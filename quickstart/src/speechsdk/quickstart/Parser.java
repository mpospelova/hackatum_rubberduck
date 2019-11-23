package speechsdk.quickstart;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Parser {
	
	public String parseSyntax(String docpath) throws IOException;

}
