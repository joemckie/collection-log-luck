package com.peanubnutter.collectionlogluck.model;

import lombok.Value;

import java.util.Map;
import java.util.Set;

@Value
public class CollectionLogCacheData {
    Set<Integer> itemIds;
    Map<Integer, Set<Integer>> categoryItems;
    Map<String, Integer> categoryStructIds;
    Map<Integer, Set<String>> categorySlugs;
	Map<Integer, String> categoryKcs;
}