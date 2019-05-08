package dfsTest;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

public class FileCopyWithProgress {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: FileCopyWithProgress <local input path> <hdfs output path>");
            System.exit(-1);
        }

        String localSrc = args[0];
        String dst = args[1];
        Configuration conf = new Configuration();
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        FileSystem fs = FileSystem.get(URI.create(dst),conf);
        FSDataOutputStream out = fs.create(new Path(dst), new Progressable() {
            @Override
            public void progress() {
                System.out.print("#");
            }
        });
        IOUtils.copyBytes(in,out,4096,true);
    }
}