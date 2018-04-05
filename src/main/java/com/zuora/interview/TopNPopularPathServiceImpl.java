package com.zuora.interview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author sagarj
 * Implements the contract mentioned in {{@link TopNPopularPathService}}
 */
public class TopNPopularPathServiceImpl implements TopNPopularPathService {

    private static final int THREE_PAGE_PATH_SIZE = 3;
    private Map<String, List<String>> data;
    private Map<ThreePageUrl, Integer> threePageUrlMap = new HashMap<>();

    @Override public void setup(Map<String, List<String>> data) {
        this.data = data;
    }

    /**
     * Returns the first N popular results.
     * @param n
     * @return
     */
    @Override public String[] getTopNPopularPaths(int n) {

        Function<ThreePageUrl, Map<ThreePageUrl, Integer>> countEntries = new CountEntries();
        Function<Map<ThreePageUrl, Integer>, Map<ThreePageUrl, Integer>> sortedFunction = new SortedFunction();

        // Limits the number of entries to specified in argument.

        Function<Map<ThreePageUrl, Integer>, String> limitFunction = (sortedMap) -> sortedMap.entrySet().stream().limit(n).collect(Collectors
                .mapping((Map.Entry entry) -> entry.getKey().toString().concat(" : ").concat(entry.getValue().toString()), Collectors.joining()));

        //Chaining of functions.
        List<String> list = data.entrySet().stream().map(entry -> splitArray(entry.getValue())).flatMap(Collection::stream).
                map(countEntries).map(sortedFunction).map(limitFunction).collect(Collectors.toList());

        List<String> finalList = list.stream().skip(list.size() - 1).collect(Collectors.toList());

        String[] threePageUrlArray = new String[finalList.size()];
        threePageUrlArray = finalList.toArray(threePageUrlArray);

        return threePageUrlArray;
    }

    private class CountEntries implements Function<ThreePageUrl, Map<ThreePageUrl, Integer>> {

        @Override public Map<ThreePageUrl, Integer> apply(ThreePageUrl threePageUrl) {
            Integer value = threePageUrlMap.get(threePageUrl);
            if (value != null) {
                threePageUrlMap.put(threePageUrl, ++value);
            } else {
                threePageUrlMap.put(threePageUrl, 1);
            }
            return threePageUrlMap;
        }
    }

    public List<ThreePageUrl> splitArray(List<String> urls) {
        List<ThreePageUrl> splitUrls = new ArrayList<>();

        for (int i = 0; i < urls.size() - THREE_PAGE_PATH_SIZE + 1; i++) {
            List<String> list = urls.subList(i, Math.min(urls.size(), i + THREE_PAGE_PATH_SIZE));
            ThreePageUrl threePageUrl = new ThreePageUrl(list);
            splitUrls.add(threePageUrl);
        }
        return splitUrls;
    }

    class SortedFunction implements Function<Map<ThreePageUrl, Integer>, Map<ThreePageUrl, Integer>> {

        @Override public Map<ThreePageUrl, Integer> apply(Map<ThreePageUrl, Integer> unsortedMap) {
            return sortMap(unsortedMap);
        }
    }

    private Map<ThreePageUrl, Integer> sortMap(Map<ThreePageUrl, Integer> unsortedMap) {

        List<Map.Entry<ThreePageUrl, Integer>> entryList = new ArrayList<>(unsortedMap.entrySet());
        Comparator<Map.Entry<ThreePageUrl, Integer>> comparator = Comparator.comparingInt(Map.Entry<ThreePageUrl, Integer>::getValue).reversed();
        Collections.sort(entryList, comparator);

        Map<ThreePageUrl, Integer> sortedMap = new LinkedHashMap<>();
        entryList.forEach(entryItem -> sortedMap.put(entryItem.getKey(), entryItem.getValue()));

        return sortedMap;
    }


}
