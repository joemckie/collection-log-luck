package com.peanubnutter.collectionlogluck.luck.drop;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.peanubnutter.collectionlogluck.CollectionLogLuckConfig;
import com.peanubnutter.collectionlogluck.luck.LogItemSourceInfo;
import com.peanubnutter.collectionlogluck.luck.RollInfo;
import com.peanubnutter.collectionlogluck.model.CollectionLog;
import com.peanubnutter.collectionlogluck.model.CollectionLogItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InterchangeableSetBinomialDropTest {

    @Test
    public void test_allOneItem() {
        // At 80% contribution, effective drop chance is 1/83.33 * 0.8
        int kc = 100;
        // 3 pieces obtained
        double expectedLuck = 0.92779;
        double expectedDryness = 0.01608;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        InterchangeableSetBinomialDrop drop = (InterchangeableSetBinomialDrop) new InterchangeableSetBinomialDrop(
                new RollInfo(LogItemSourceInfo.ROYAL_TITAN_KILLS, 1.0 / 83.33),
                ImmutableList.of(30628, 30631)
        ).withConfigOption(CollectionLogLuckConfig.AVG_ROYAL_TITANS_CONTRIBUTION_KEY);

        List<CollectionLogItem> items = new ArrayList<>();
        items.add(new CollectionLogItem(30628, "Ice element staff crown", 0, true, 0));
        items.add(new CollectionLogItem(30631, "Fire element staff crown", 3, true, 0));

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcsAndItems(
                ImmutableMap.of(LogItemSourceInfo.ROYAL_TITAN_KILLS.getName(), kc),
                items
        );

        CollectionLogLuckConfig config = new CollectionLogLuckConfig() {
            @Override
            public double avgRoyalTitansContribution() {
                return 0.8;
            }
        };

        // All items should have the same luck, since even for unobtained items, the progress towards the complete set
        // is what we're really measuring for luck. This might confuse some users, but it might be best / easiest.
        for (CollectionLogItem mockItem : items) {
            double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, config);
            assertEquals(expectedLuck, actualLuck, tolerance);

            double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, config);
            assertEquals(expectedDryness, actualDryness, tolerance);
        }
    }

    @Test
    public void test_allOtherItem() {
        // At 80% contribution, effective drop chance is 1/83.33 * 0.8
        int kc = 100;
        // 3 pieces obtained
        double expectedLuck = 0.92779;
        double expectedDryness = 0.01608;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        InterchangeableSetBinomialDrop drop = (InterchangeableSetBinomialDrop) new InterchangeableSetBinomialDrop(
                new RollInfo(LogItemSourceInfo.ROYAL_TITAN_KILLS, 1.0 / 83.33),
                ImmutableList.of(30628, 30631)
        ).withConfigOption(CollectionLogLuckConfig.AVG_ROYAL_TITANS_CONTRIBUTION_KEY);

        List<CollectionLogItem> items = new ArrayList<>();
        items.add(new CollectionLogItem(30628, "Ice element staff crown", 3, true, 0));
        items.add(new CollectionLogItem(30631, "Fire element staff crown", 0, true, 0));

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcsAndItems(
                ImmutableMap.of(LogItemSourceInfo.ROYAL_TITAN_KILLS.getName(), kc),
                items
        );

        CollectionLogLuckConfig config = new CollectionLogLuckConfig() {
            @Override
            public double avgRoyalTitansContribution() {
                return 0.8;
            }
        };

        // All items should have the same luck, since even for unobtained items, the progress towards the complete set
        // is what we're really measuring for luck. This might confuse some users, but it might be best / easiest.
        for (CollectionLogItem mockItem : items) {
            double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, config);
            assertEquals(expectedLuck, actualLuck, tolerance);

            double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, config);
            assertEquals(expectedDryness, actualDryness, tolerance);
        }
    }

    @Test
    public void test_bothItems() {
        // At 80% contribution, effective drop chance is 1/83.33 * 0.8
        int kc = 100;
        // 3 pieces obtained
        double expectedLuck = 0.92779;
        double expectedDryness = 0.01608;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        InterchangeableSetBinomialDrop drop = (InterchangeableSetBinomialDrop) new InterchangeableSetBinomialDrop(
                new RollInfo(LogItemSourceInfo.ROYAL_TITAN_KILLS, 1.0 / 83.33),
                ImmutableList.of(30628, 30631)
        ).withConfigOption(CollectionLogLuckConfig.AVG_ROYAL_TITANS_CONTRIBUTION_KEY);

        List<CollectionLogItem> items = new ArrayList<>();
        items.add(new CollectionLogItem(30628, "Ice element staff crown", 1, true, 0));
        items.add(new CollectionLogItem(30631, "Fire element staff crown", 2, true, 0));

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcsAndItems(
                ImmutableMap.of(LogItemSourceInfo.ROYAL_TITAN_KILLS.getName(), kc),
                items
        );

        CollectionLogLuckConfig config = new CollectionLogLuckConfig() {
            @Override
            public double avgRoyalTitansContribution() {
                return 0.8;
            }
        };

        // All items should have the same luck, since even for unobtained items, the progress towards the complete set
        // is what we're really measuring for luck. This might confuse some users, but it might be best / easiest.
        for (CollectionLogItem mockItem : items) {
            double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, config);
            assertEquals(expectedLuck, actualLuck, tolerance);

            double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, config);
            assertEquals(expectedDryness, actualDryness, tolerance);
        }
    }
}