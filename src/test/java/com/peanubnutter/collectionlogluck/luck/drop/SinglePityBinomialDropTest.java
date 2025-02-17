package com.peanubnutter.collectionlogluck.luck.drop;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.peanubnutter.collectionlogluck.luck.LogItemSourceInfo;
import com.peanubnutter.collectionlogluck.luck.RollInfo;
import com.peanubnutter.collectionlogluck.model.CollectionLog;
import com.peanubnutter.collectionlogluck.model.CollectionLogItem;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SinglePityBinomialDropTest {

    @Test
    public void testSinglePityBinomial_singleDropSource_underThreshold_obtained() {
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc + 1;
        int numObtained = 1;
        double expectedLuck = 0.36603;
        // The player can only get one of these, so dryness is 0. 0 people have more than 1.
        double expectedDryness = 0;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_singleDropSource_atThreshold_obtained() {
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc;
        int numObtained = 1;

        // above the threshold, the player must have received the item (so has everyone else). So both luck and dryness
        // are 0.
        double expectedLuck = 0;
        double expectedDryness = 0;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_singleDropSource_aboveThreshold_obtained() {
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc - 1;
        int numObtained = 1;

        // above the threshold, the player must have received the item (so has everyone else). So both luck and dryness
        // are 0.
        double expectedLuck = 0;
        double expectedDryness = 0;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_singleDropSource_underThreshold_unobtained() {
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc + 1;
        int numObtained = 0;

        double expectedLuck = 0;
        // dryness is calculated as normal here because the player could receive a max of 2, but only has 1.
        double expectedDryness = 0.63397;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_singleDropSource_atThreshold_unobtained() {
        // This SHOULD be impossible, but will return 0 luck/dryness just in case, since the player can always just do
        // 1 kc to get the drop
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc;
        int numObtained = 0;

        // above the threshold, the player must have received the item (so has everyone else). So both luck and dryness
        // are 0.
        double expectedLuck = 0;
        double expectedDryness = 0;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_singleDropSource_aboveThreshold_unobtained() {
        // This SHOULD be impossible, but will return 0 luck/dryness just in case, since the player can always just do
        // 1 kc to get the drop
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc - 1;
        int numObtained = 0;

        // above the threshold, the player must have received the item (so has everyone else). So both luck and dryness
        // are 0.
        double expectedLuck = 0;
        double expectedDryness = 0;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_0Obtained() {
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc + 1;
        int numObtained = 0;
        double expectedLuck = 0;
        double expectedDryness = 0.63397;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, false, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_0Trials() {
        double dropChance = 0.5;
        int kc = 0;
        int numObtained = 0;
        double expectedLuck = 0;
        double expectedDryness = 0;
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_spooooooooooooooooned() {
        double dropChance = 0.5;
        int kc = 100000000;
        int dropGuaranteedOnKc = kc + 1;
        int numObtained = 100000000;
        double expectedLuck = 1;
        double expectedDryness = 0;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.000000001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_dryyyyyyyyyyyyyyyyyyyyy() {
        double dropChance = 0.5;
        int kc = 100000;
        int dropGuaranteedOnKc = kc + 1;
        int numObtained = 0;
        double expectedLuck = 0;
        double expectedDryness = 1;
        double tolerance = 0.000000001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, false, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testSinglePityBinomial_missingDropSource() {
        double dropChance = 0.5;
        int kc = 10;
        int dropGuaranteedOnKc = kc + 1;
        int numObtained = 11;
        double expectedLuck = -1;
        double expectedDryness = -1;
        double tolerance = 0.00001;

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testKcString() {
        double dropChance = 0.01;
        int kc = 100;
        int dropGuaranteedOnKc = kc + 1;
        String expectedKcString = "100x Abyssal Sire kills";

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), dropGuaranteedOnKc);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        String actualKcString = drop.getKillCountDescription(mockCollectionLog);
        assertEquals(expectedKcString, actualKcString);
    }

    @Test
    public void testKcString_multipleDropSources() {
        double dropChance = 0.01;
        int kc1 = 100;
        int kc2 = 200;
        int kc3 = 150;
        String expectedKcString = "200x Calvar'ion kills, 150x Spindel kills, 100x Artio kills";

        List<RollInfo> rollInfos = ImmutableList.of(
                new RollInfo(LogItemSourceInfo.ARTIO_KILLS, dropChance),
                new RollInfo(LogItemSourceInfo.CALVARION_KILLS, dropChance),
                new RollInfo(LogItemSourceInfo.SPINDEL_KILLS, dropChance));
        Map<String, Integer> kcs = ImmutableMap.of(
                LogItemSourceInfo.ARTIO_KILLS.getName(), kc1,
                LogItemSourceInfo.CALVARION_KILLS.getName(), kc2,
                LogItemSourceInfo.SPINDEL_KILLS.getName(), kc3
        );

        SinglePityBinomialDrop drop = new SinglePityBinomialDrop(rollInfos, 1);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcs(kcs);

        String actualKcString = drop.getKillCountDescription(mockCollectionLog);
        assertEquals(expectedKcString, actualKcString);
    }

}