package com.peanubnutter.collectionlogluck.util;

import com.google.common.collect.ImmutableMap;
import com.peanubnutter.collectionlogluck.luck.LogItemInfo;
import com.peanubnutter.collectionlogluck.luck.LogItemSourceInfo;
import com.peanubnutter.collectionlogluck.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionLogBuilder {

    // Get a fake collection log with all items zeroed out
    public static CollectionLog getEmptyCollectionLog(String username) {
        List<CollectionLogItem> allLogItems = LogItemInfo.getAllLogItemInfos().stream()
                .map(logItemInfo -> new CollectionLogItem(
                        logItemInfo.getItemId(), logItemInfo.getItemName(), 0, false, 0))
                .collect(Collectors.toList());

        List<CollectionLogKillCount> allKillCounts = Arrays.stream(LogItemSourceInfo.values())
                .map(itemSourceInfo -> new CollectionLogKillCount(itemSourceInfo.getName(), 0, 0))
                .collect(Collectors.toList());

        // Create a default page which contains ALL items exactly once, since we lost all information about
        // what page each item was on after the collectionlog.net shutdown.
        CollectionLogPage defaultPage = new CollectionLogPage("Default Page", allLogItems, allKillCounts, true);
        Map<String, CollectionLogPage> pages = ImmutableMap.of(defaultPage.getName(), defaultPage);

        CollectionLogTab defaultTab = new CollectionLogTab("Default Tab", pages);
        Map<String, CollectionLogTab> tabs = ImmutableMap.of(defaultTab.getName(), defaultTab);

        return new CollectionLog(username, 0, 0, 0, 0, tabs);
    }
}
