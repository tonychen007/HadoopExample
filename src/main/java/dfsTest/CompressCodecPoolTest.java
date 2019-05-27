package dfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

public class CompressCodecPoolTest {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        CompressionCodec codec = ReflectionUtils.newInstance(GzipCodec.class,conf);
        Compressor compressor = null;

        compressor = CodecPool.getCompressor(codec);
        CompressionOutputStream cos = codec.createOutputStream(System.out, compressor);
        IOUtils.copyBytes(System.in, cos, 4096, false);
        cos.finish();
        CodecPool.returnCompressor(compressor);
    }
}
