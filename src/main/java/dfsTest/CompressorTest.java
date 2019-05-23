package dfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

public class CompressorTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        CompressionCodec codec = ReflectionUtils.newInstance(GzipCodec.class,conf);
        CompressionOutputStream cos = codec.createOutputStream(System.out);
        cos.write("Text".getBytes());
        cos.finish();
    }
}
