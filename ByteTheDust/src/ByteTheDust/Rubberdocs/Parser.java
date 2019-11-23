package ByteTheDust.Rubberdocs;

import com.github.javaparser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Parser {
	
	public String parseSyntax(String docpath) throws IOException, ParseException;

}
