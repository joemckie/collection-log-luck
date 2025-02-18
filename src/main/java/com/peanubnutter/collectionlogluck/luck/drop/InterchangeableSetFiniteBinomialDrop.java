package com.peanubnutter.collectionlogluck.luck.drop;

import com.peanubnutter.collectionlogluck.CollectionLogLuckConfig;
import com.peanubnutter.collectionlogluck.luck.RollInfo;
import com.peanubnutter.collectionlogluck.model.CollectionLog;
import com.peanubnutter.collectionlogluck.model.CollectionLogItem;

import java.util.List;

// Combines the properties of InterchangeableSetBinomialDrop and FiniteBinomialDrop
// For example, the player can only obtain one of each Royal Titans scroll, but can choose freely which one to go for.
// This makes a rather large assumption that the player tries to go for the other scroll upon receiving the first one.
public class InterchangeableSetFiniteBinomialDrop extends InterchangeableSetBinomialDrop {

    private final int maxDropCount;

    public InterchangeableSetFiniteBinomialDrop(RollInfo rollInfo, List<Integer> setItemIds, int maxDropCount) {
        super(rollInfo, setItemIds);
        this.maxDropCount = maxDropCount;
    }

    @Override
    public double calculateDryness(CollectionLogItem item, CollectionLog collectionLog, CollectionLogLuckConfig config) {
        double normalDryness = super.calculateDryness(item, collectionLog, config);

        if (normalDryness <= 0) {
            return normalDryness;
        }

        int numSuccesses = getNumSuccesses(item, collectionLog, config);
        if (numSuccesses >= maxDropCount)
            return 0;

        return normalDryness;
    }
}
