package speechsdk.quickstart;

public interface Parser {
	
	public String parseSyntax(String docpath);
	public void writeAnnotations(String key, String annotation);

}
