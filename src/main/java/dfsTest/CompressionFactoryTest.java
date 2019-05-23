package dfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class CompressionFactoryTest {
    public static void main(String[] args) throws Exception {
        String uri = args[0];
        if (uri == null || "".equals(uri)) {
            System.err.println("Please provide the filename!!!");
            System.exit(1);
        }

        Path inputPath = new Path(uri);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        String ss = System.getProperty("java.library.path");
        ss = System.getProperty("io.native.lib.available");

        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(inputPath);
        if (codec == null) {
            System.err.println("No codec found for " + uri + "!!!");
            System.exit(1);
        }

        String output = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());
        InputStream ins = codec.createInputStream(fs.open(inputPath));
        OutputStream ons = fs.create(new Path(output));
        IOUtils.copyBytes(ins,ons,conf);
        ins.close();
        ons.close();
    }
}
