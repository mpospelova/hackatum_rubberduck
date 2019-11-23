package ByteTheDust.Rubberdocs;

import com.github.javaparser.ParseException;

import java.io.IOException;
import java.util.List;

public interface Parser {
	
	public List<String> parseSyntax(String docpath) throws IOException, ParseException;

}
