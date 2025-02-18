package com.peanubnutter.collectionlogluck.luck.drop;

import com.peanubnutter.collectionlogluck.CollectionLogLuckConfig;
import com.peanubnutter.collectionlogluck.luck.LogItemInfo;
import com.peanubnutter.collectionlogluck.luck.RollInfo;
import com.peanubnutter.collectionlogluck.model.CollectionLog;
import com.peanubnutter.collectionlogluck.model.CollectionLogItem;

import java.util.ArrayList;
import java.util.List;

// Represents a Binomial drop whose set of gear (configured in constructor) are considered equivalent. If the player
// receives any one of these items, in any order or amount, it is considered a success. The drop chance represents the
// chance of hitting one of the individual items.
// NOTE: This class assumes that all items in the set have the same drop chance and drop mechanics.
public class InterchangeableSetBinomialDrop extends BinomialDrop {

    // The item IDs of all items in the set, including this item.
    // For technical reasons (LogItemInfos are static variables rather than an enum because of its size), these should
    // be integer item IDs.
    private final List<Integer> setItemIds;
    protected final List<LogItemInfo> setLogItemInfos = new ArrayList<>();

    public InterchangeableSetBinomialDrop(RollInfo rollInfo, List<Integer> setItemIds) {
        super(rollInfo);
        this.setItemIds = setItemIds;
    }

    protected void fetchLogItemInfos() {
        if (setLogItemInfos.isEmpty()) {
            for (Integer itemId : setItemIds) {
                setLogItemInfos.add(LogItemInfo.findByItemId(itemId));
            }
        }
    }

    @Override
    protected int getNumSuccesses(CollectionLogItem item, CollectionLog collectionLog, CollectionLogLuckConfig config) {
        fetchLogItemInfos();

        // Sum the number of successes for all items in this set.
        int numSuccesses = 0;
        for (LogItemInfo setPiece : setLogItemInfos) {
            CollectionLogItem setItem = collectionLog.searchForItem(setPiece.getItemName());
            if (setItem != null) {
                numSuccesses += super.getNumSuccesses(setItem, collectionLog, config);
            }
        }
        return numSuccesses;
    }

    @Override
    protected double getDropChance(RollInfo rollInfo, CollectionLog collectionLog, CollectionLogLuckConfig config) {
        fetchLogItemInfos();

        // Assumes all items in the set drop with equal chance
        return super.getDropChance(rollInfo, collectionLog, config);
    }
}
