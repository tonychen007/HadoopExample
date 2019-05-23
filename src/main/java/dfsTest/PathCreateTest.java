package dfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class PathCreateTest {
    private static final String FILE_URI = "/";

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("tony");
        FSDataOutputStream out = fs.create(path);

        boolean ret = fs.exists(path);
        out.write("content".getBytes("utf-8"));
        out.hsync();
        long sz = fs.getFileStatus(path).getLen();
        System.out.printf("File length is:" + sz);
    }
}
