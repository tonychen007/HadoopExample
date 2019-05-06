package dfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class FileSystemCat {
    private static final String FILE_URI = "input/sample.txt";

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String defaultFS = conf.get("fs.defaultFS");
        System.out.println(defaultFS);
        FileSystem fs = FileSystem.get(URI.create(FILE_URI), conf);

        try(FSDataInputStream in = fs.open(new Path(FILE_URI))) {
            in.seek(1);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {}
    }
}
