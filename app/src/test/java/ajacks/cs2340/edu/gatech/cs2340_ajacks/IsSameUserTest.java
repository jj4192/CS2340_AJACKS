package ajacks.cs2340.edu.gatech.cs2340_ajacks;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.User;

/**
 * Tests isSameUser method in User class.
 */

public class IsSameUserTest {
    private User u1;
    private User u2;
    private User u3;
    private User u4;
    @Before
    public void setup() {
        u1 = new User("1", "abc", "cde", "test@gmail.com", "Admin", "locked");
        u2 = new User("2", "123", "pass", "test@mail.com", "User", "locked");
        u3 = new User("1", "test", "passw", "test@hotmail.com", "Admin", "unlocked");
        u4 = new User("2", "chsieh123", "code", "test@charter.net", "User", "unlocked");
    }

    @Test
    public void isSame() {
        Assert.assertEquals(true, u1.isSameUser(u3));
        Assert.assertEquals(true, u2.isSameUser(u4));
        Assert.assertEquals(true, u2.isSameUser(u2));
    }

    @Test
    public void isNotSame() {
        Assert.assertEquals(false, u1.isSameUser(u2));
        Assert.assertEquals(false, u2.isSameUser(u3));
        Assert.assertEquals(false, u4.isSameUser(u1));

    }

}
