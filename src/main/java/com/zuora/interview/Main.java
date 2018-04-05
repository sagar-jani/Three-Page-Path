package com.zuora.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sagarj
 *
 * Main class to prepare data and invoke the service.
 */
public class Main {

    public static void main(String args[]) {
        com.zuora.interview.TopNPopularPathService topNPopularPathService = new TopNPopularPathServiceImpl();
        Map<String, List<String>> data = getData();

        topNPopularPathService.setup(data);

        String[] finalURLs = topNPopularPathService.getTopNPopularPaths(3);
        StringBuilder builder = new StringBuilder();
        for (String value : finalURLs) {
            builder.append(value);
        }
        String text = builder.toString();
        System.out.println(text);

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
