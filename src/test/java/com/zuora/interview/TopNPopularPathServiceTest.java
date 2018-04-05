package com.zuora.interview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TopNPopularPathServiceImpl.class)
public class TopNPopularPathServiceTest {

    private final String top3URLs = "\n"
            + "/ -> login -> subscriber : 3\n"
            + "/ -> login -> product : 2\n"
            + "login -> subscriber -> / : 1";

    TopNPopularPathService topNPopularPathService;

    @Before
    public void setUp(){
        //instantiate
        topNPopularPathService = new TopNPopularPathServiceImpl();

        //setup data
        topNPopularPathService.setup(getData());
    }


    @Test
    public void testPositivePath() {

        String[] topURLs = topNPopularPathService.getTopNPopularPaths(3);

        //Then
        Assert.assertEquals("The top N elements don't match", top3URLs, topURLs[0]);
    }


    private static Map<String, List<String>> getData() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> urls = new ArrayList<>();

        urls.add("/");
        urls.add("login");
        urls.add("subscriber");
        urls.add("/");
        data.put("u1", urls);

        urls = new ArrayList<>();
        urls.add("/");
        urls.add("login");
        urls.add("subscriber");
        data.put("u2", urls);

        urls = new ArrayList<>();
        urls.add("/");
        urls.add("login");
        urls.add("product");
        data.put("u3", urls);

        urls = new ArrayList<>();
        urls.add("/");
        urls.add("login");
        urls.add("product");
        data.put("u4", urls);

        urls = new ArrayList<>();
        urls.add("/");
        urls.add("login");
        urls.add("subscriber");
        data.put("u5", urls);

        return data;
    }

}
