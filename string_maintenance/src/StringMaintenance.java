import java.io.*;
class StringMaintenace {

    public static void main(String[] args) throws IOException {
        String filepath = "/home/masha/development/hackatum/hackatum_rubberduck/README.md";
        File readme= new File(filepath);
        String str = "\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        writeToReadme(readme, str);
    }

    private static void writeToReadme(File readme, String data)  {
        OutputStream outputStream = null;
        try {
            //boolean append: new string data has to be written to the end
            outputStream = new FileOutputStream(readme, true);
            //write(b, off, len)
            //b: data
            //off: start offset in data
            //len: length of data, number of bytes to write
            outputStream.write(data.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
