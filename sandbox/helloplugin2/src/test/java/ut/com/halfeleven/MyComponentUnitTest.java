package ut.com.halfeleven;

import org.junit.Test;
import com.halfeleven.api.MyPluginComponent;
import com.halfeleven.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}