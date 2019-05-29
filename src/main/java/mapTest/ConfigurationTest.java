package mapTest;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigurationTest {
    public static void main(String[] args) {

    }

    @Test
    public void testConf1() {
        Configuration conf = new Configuration();
        conf.addResource("conf1.xml");
        assertThat(conf.get("color"), is("yellow"));
        assertThat(conf.getInt("size", 0), is(10));
        assertThat(conf.get("breadth", "wide"), is("wide"));
    }

    @Test
    public void testConf2() {
        Configuration conf = new Configuration();
        conf.addResource("conf1.xml");
        conf.addResource("conf2.xml");
        assertThat(conf.get("color"), is("yellow"));
        //assertThat(conf.getInt("size", 0), is(10));
        assertThat(conf.getInt("size", 0), is(12));
        assertThat(conf.get("weight"), is("heavy"));
        //assertThat(conf.get("weight"), is("light"));
        assertThat(conf.get("size-weight"), is("12,heavy"));
    }

    @Test
    public void testConf3() {
        Configuration conf = new Configuration();
        conf.addResource("conf1.xml");
        conf.addResource("conf2.xml");
        System.setProperty("size", "14");
        assertThat(conf.get("size-weight"), is("14,heavy"));
        System.setProperty("length", "2");
        assertThat(conf.get("length"), is((String) null));
    }
}
