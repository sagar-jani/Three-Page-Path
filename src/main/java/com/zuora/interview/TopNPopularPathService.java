package com.zuora.interview;

import java.util.List;
import java.util.Map;

/**
 * Contract for TopNPopularPathService
 */
public interface TopNPopularPathService {
    void setup(Map<String, List<String>> data);
    String[] getTopNPopularPaths(int n);
}
