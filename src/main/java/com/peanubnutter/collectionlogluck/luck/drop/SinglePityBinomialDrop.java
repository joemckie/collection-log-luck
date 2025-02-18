package com.peanubnutter.collectionlogluck.luck.drop;

import com.google.common.collect.ImmutableList;
import com.peanubnutter.collectionlogluck.CollectionLogLuckConfig;
import com.peanubnutter.collectionlogluck.luck.RollInfo;
import com.peanubnutter.collectionlogluck.model.CollectionLog;
import com.peanubnutter.collectionlogluck.model.CollectionLogItem;

import java.util.List;

// Represents a drop that can drop only once but has a "pity" mechanic where it is guaranteed to drop on the Xth kc
// If the KC is >= X, and the player has indeed received the drop, then both luck and dryness are zeroed out and become meaningless.
// If the KC is >= X and the player somehow has not received the drop, then it will still return 0 for both luck and
// dryness, since the player should be able to get the drop guaranteed on the next KC.
// Otherwise, this behaves identically to FiniteBinomialDrop (i.e., 0 dryness after receiving the item once).
public class SinglePityBinomialDrop extends FiniteBinomialDrop {

    private final int dropGuaranteedOnKc;

    public SinglePityBinomialDrop(List<RollInfo> rollInfos, int dropGuaranteedOnKc) {
        super(rollInfos, 1);
        this.dropGuaranteedOnKc = dropGuaranteedOnKc;
    }

    public SinglePityBinomialDrop(RollInfo rollInfo, int dropGuaranteedOnKc) {
        this(ImmutableList.of(rollInfo), dropGuaranteedOnKc);
    }

    @Override
    public double calculateLuck(CollectionLogItem item, CollectionLog collectionLog, CollectionLogLuckConfig config) {
        int numTrials = getNumTrials(collectionLog, config);
        if (numTrials >= dropGuaranteedOnKc) {
            return 0;
        }

        return super.calculateLuck(item, collectionLog, config);
    }

    @Override
    public double calculateDryness(CollectionLogItem item, CollectionLog collectionLog, CollectionLogLuckConfig config) {
        int numTrials = getNumTrials(collectionLog, config);
        if (numTrials >= dropGuaranteedOnKc) {
            return 0;
        }

        return super.calculateDryness(item, collectionLog, config);
    }
}
