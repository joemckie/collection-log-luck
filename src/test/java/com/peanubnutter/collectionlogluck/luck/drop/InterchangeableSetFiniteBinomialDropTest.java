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

public class InterchangeableSetFiniteBinomialDropTest {

    @Test
    public void test_allOneItem() {
        // At 60% contribution, effective drop chance is 1/83.33 * 0.6
        int kc = 100;
        // 1 scroll obtained
        double expectedLuck = 0.48547;
        double expectedDryness = 0.16244;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        InterchangeableSetFiniteBinomialDrop drop = (InterchangeableSetFiniteBinomialDrop)
                new InterchangeableSetFiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ROYAL_TITAN_KILLS, 1.0 / 83.33),
                ImmutableList.of(30626, 30627),
                // You can only get 1 of each scroll... This does make the assumption that the player starts trying to get
                // the other prayer scroll once receiving one of them... Otherwise, someone who only ever loots one
                // corpse will be considered dry even though it's their fault / choice that they aren't receiving
                // both drops.
                2
        ).withConfigOption(CollectionLogLuckConfig.AVG_ROYAL_TITANS_CONTRIBUTION_KEY);

        List<CollectionLogItem> items = new ArrayList<>();
        items.add(new CollectionLogItem(30626, "Deadeye prayer scroll", 1, true, 0));
        items.add(new CollectionLogItem(30627, "Mystic vigour prayer scroll", 0, false, 0));

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcsAndItems(
                ImmutableMap.of(LogItemSourceInfo.ROYAL_TITAN_KILLS.getName(), kc),
                items
        );

        CollectionLogLuckConfig config = new CollectionLogLuckConfig() {
            @Override
            public double avgRoyalTitansContribution() {
                return 0.6;
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
        // At 60% contribution, effective drop chance is 1/83.33 * 0.6
        int kc = 100;
        // 1 scroll obtained
        double expectedLuck = 0.48547;
        double expectedDryness = 0.16244;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        InterchangeableSetFiniteBinomialDrop drop = (InterchangeableSetFiniteBinomialDrop)
                new InterchangeableSetFiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ROYAL_TITAN_KILLS, 1.0 / 83.33),
                        ImmutableList.of(30626, 30627),
                        // You can only get 1 of each scroll... This does make the assumption that the player starts trying to get
                        // the other prayer scroll once receiving one of them... Otherwise, someone who only ever loots one
                        // corpse will be considered dry even though it's their fault / choice that they aren't receiving
                        // both drops.
                        2
                ).withConfigOption(CollectionLogLuckConfig.AVG_ROYAL_TITANS_CONTRIBUTION_KEY);

        List<CollectionLogItem> items = new ArrayList<>();
        items.add(new CollectionLogItem(30626, "Deadeye prayer scroll", 0, false, 0));
        items.add(new CollectionLogItem(30627, "Mystic vigour prayer scroll", 1, true, 0));

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcsAndItems(
                ImmutableMap.of(LogItemSourceInfo.ROYAL_TITAN_KILLS.getName(), kc),
                items
        );

        CollectionLogLuckConfig config = new CollectionLogLuckConfig() {
            @Override
            public double avgRoyalTitansContribution() {
                return 0.6;
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
    public void test_bothObtained() {
        // At 60% contribution, effective drop chance is 1/83.33 * 0.6
        int kc = 100;
        // 1 scroll obtained
        double expectedLuck = 0.83756;
        double expectedDryness = 0;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        InterchangeableSetFiniteBinomialDrop drop = (InterchangeableSetFiniteBinomialDrop)
                new InterchangeableSetFiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ROYAL_TITAN_KILLS, 1.0 / 83.33),
                        ImmutableList.of(30626, 30627),
                        // You can only get 1 of each scroll... This does make the assumption that the player starts trying to get
                        // the other prayer scroll once receiving one of them... Otherwise, someone who only ever loots one
                        // corpse will be considered dry even though it's their fault / choice that they aren't receiving
                        // both drops.
                        2
                ).withConfigOption(CollectionLogLuckConfig.AVG_ROYAL_TITANS_CONTRIBUTION_KEY);

        List<CollectionLogItem> items = new ArrayList<>();
        items.add(new CollectionLogItem(30626, "Deadeye prayer scroll", 1, true, 0));
        items.add(new CollectionLogItem(30627, "Mystic vigour prayer scroll", 1, true, 0));

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcsAndItems(
                ImmutableMap.of(LogItemSourceInfo.ROYAL_TITAN_KILLS.getName(), kc),
                items
        );

        CollectionLogLuckConfig config = new CollectionLogLuckConfig() {
            @Override
            public double avgRoyalTitansContribution() {
                return 0.6;
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