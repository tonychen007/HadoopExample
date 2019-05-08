package dfsTest;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ShowFileStatusTest {
    private static final String FILE_URI = "/";

    private FileSystem fs;

    @Before
    public void setUp() throws IOException {
        Configuration conf = new Configuration();
        fs = FileSystem.get(conf);
    }

    @After
    public void tearDown() throws IOException {
        if (fs != null)
            fs.close();
    }

    @Test
    public void testFileStatus4File() throws IOException {
        Path file = new Path("/user/tony/input/sample.txt");
        FileStatus st = fs.getFileStatus(file);
        String path = st.getPath().toUri().getPath();
        String owner = st.getOwner();
        String permission = st.getPermission().toString();
        long bs = st.getBlockSize();
        long len = st.getLen();
    }

    @Test
    public void testFileStatus4Dir() throws IOException {
        Path file = new Path("/user/tony/");
        FileStatus st = fs.getFileStatus(file);
        String path = st.getPath().toUri().getPath();
        String owner = st.getOwner();
        String permission = st.getPermission().toString();
        long bs = st.getBlockSize();
        long len = st.getLen();

    }
}
