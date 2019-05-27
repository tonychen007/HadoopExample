package dfsTest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class SerializationTest {
    public static void main(String[] args) throws Exception {
        // serialize
        Writable intW = new IntWritable(163);
        Writable intW2 = new IntWritable();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        intW.write(dataOut);
        dataOut.close();
        byte bys[] = out.toByteArray();

        // deserialize
        ByteArrayInputStream in = new ByteArrayInputStream(bys);
        DataInputStream dataIn = new DataInputStream(in);
        intW2.readFields(dataIn);
        int a = 0;

        // raw comparator
    }
}
