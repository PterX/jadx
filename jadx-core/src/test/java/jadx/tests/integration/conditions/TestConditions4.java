package jadx.tests.integration.conditions;

import org.junit.Test;

import jadx.core.dex.nodes.ClassNode;
import jadx.tests.api.IntegrationTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TestConditions4 extends IntegrationTest {

    @Test
    public void test() {
        ClassNode cls = getClassNode(TestCls.class);
        String code = cls.getCode().toString();

        assertThat(code, containsString("num >= 59 && num <= 66"));
        assertThat(code, containsString("return inRange ? num + 1 : num;"));
        assertThat(code, not(containsString("else")));
    }

    public static class TestCls {
        public int test(int num) {
            boolean inRange = (num >= 59 && num <= 66);
            return inRange ? num + 1 : num;
        }
    }
}
