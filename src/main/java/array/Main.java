package array;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Настя on 09.07.2017.
 */
public class Main {
    void copyFile(Reader reader, Writer writer, String s, String s1) {
        ///
        try {
            int c;
            while ((c = reader.read()) >= 0) {
                //
                writer.append((char)c);
                writer.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
            }
        }
    }
    void copyFileBuffered(File in, File out) {
        ///
        try {
            BufferedReader input = new BufferedReader(new FileReader(in));
            BufferedWriter output = new BufferedWriter(new FileWriter(out));
            int c;
            while ((c = input.read()) >= 0) {
                //???
                //output.append((char)c);
                output.write(c);
            }
            output.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    void copyByte(File in, File out){
        try {
            InputStream input = new FileInputStream(in);
            OutputStream output = new FileOutputStream(out);
            int c;
            while ((c = input.read()) >= 0) {
                //???
                //output.append((char)c);
                output.write(c);
                output.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    void func(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String pattern = "^([0-9]|[a-z]|[A-Z]){3}$";
        for (int i = 0; i < s.length() - 2; i++) {
            String check = s.substring(i, i + 3);
            if (check.matches(pattern)) {
                Integer frequency = map.get(check);
                if (frequency == null) {
                    frequency = 0;
                }
                map.put(check, ++frequency);
            }
        }
        for (Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + " - " + e.getValue());
    }
    void bestWay(File f1, File f2) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(f1));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f2)));
            String s;
            while ((s = input.readLine()) != null) {
                writer.println(s);
            }
            writer.flush();
        } catch (IOException ex) {

        }
    }
    public static void main(String[] args) throws IOException {
        //Reader reader = new FileReader("D:\\input.txt");
        //Writer writer = new FileWriter("D:\\output.txt");

        long time = System.nanoTime();
        //new Main().copyFile(reader, writer, " ", " ");
        new Main().copyByte(new File("D:\\input.txt"), new File("D:\\output111.txt"));
        System.out.println(System.nanoTime() - time);
        time = System.nanoTime();

        new Main().copyFileBuffered(new File("D:\\input.txt"), new File("D:\\output.txt"));
        System.out.println(System.nanoTime() - time);
        time = System.nanoTime();
        new Main().bestWay(new File("D:\\input.txt"), new File("D:\\output.txt"));
        System.out.println(System.nanoTime() - time);
        int n = 89;
        char c = (char)n;
        System.out.print(c);
    }
}
