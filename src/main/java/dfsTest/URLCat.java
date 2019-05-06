package dfsTest;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLCat {
    private final static String HDFS_FILE = "hdfs://M4600-Master:9000/user/tony/input/sample.txt";

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) {
        try(InputStream in = new URL(HDFS_FILE).openStream()) {
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
