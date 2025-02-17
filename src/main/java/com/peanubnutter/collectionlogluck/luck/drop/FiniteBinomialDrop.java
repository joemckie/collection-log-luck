package com.peanubnutter.collectionlogluck.luck.drop;

import com.google.common.collect.ImmutableList;
import com.peanubnutter.collectionlogluck.CollectionLogLuckConfig;
import com.peanubnutter.collectionlogluck.luck.RollInfo;
import com.peanubnutter.collectionlogluck.model.CollectionLog;
import com.peanubnutter.collectionlogluck.model.CollectionLogItem;

import java.util.List;

// This is identical to BinomialDrop, but you can only receive the item X times.
// Dryness and luck are calculated as normal if the item has not been received (or is received less than X times),
// but once receiving the item the Xth time, the dryness becomes exactly 0 since we cannot know when exactly the player
// received the item, and there are 0 players that have received the item more times than X by this KC.
// The luck is always calculated as normal (defines as the fraction of players less lucky than you). This fraction
// tends towards 0 as KC increases, since fewer and fewer people go dry, so luck tends towards 0 and "overall luck"
// evens out towards 50% as everyone has all received their X maximum drops by infinite KC.
public class FiniteBinomialDrop extends BinomialDrop {

    private final int maxDropCount;

    public FiniteBinomialDrop(List<RollInfo> rollInfos, int maxDropCount) {
        super(rollInfos);
        this.maxDropCount = maxDropCount;
    }

    public FiniteBinomialDrop(RollInfo rollInfo, int maxDropCount) {
        this(ImmutableList.of(rollInfo), maxDropCount);
    }

    @Override
    public double calculateDryness(CollectionLogItem item, CollectionLog collectionLog, CollectionLogLuckConfig config) {
        double normalDryness = super.calculateDryness(item, collectionLog, config);

        if (normalDryness <= 0) {
            return normalDryness;
        }

        int numSuccesses = getNumSuccesses(item, collectionLog, config);
        if (numSuccesses >= maxDropCount) {
            return 0;
        }

        return normalDryness;
    }
}
