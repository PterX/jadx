package jadx.tests.integration.inner;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import jadx.core.dex.nodes.ClassNode;
import jadx.tests.api.IntegrationTest;

import static jadx.tests.api.utils.JadxMatchers.containsOne;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class TestAnonymousClass9 extends IntegrationTest {

    @Test
    public void test() {
        ClassNode cls = getClassNode(TestCls.class);
        String code = cls.getCode().toString();

        assertThat(code, containsOne("c = new Callable<String>() {"));
        assertThat(code, containsOne("return new FutureTask<String>(this.c) {"));
        assertThat(code, not(containsString("synthetic")));
    }

    public static class TestCls {

        public Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "str";
            }
        };

        public Runnable test() {
            return new FutureTask<String>(this.c) {
                public void run() {
                    System.out.println(6);
                }
            };
        }
    }
}
