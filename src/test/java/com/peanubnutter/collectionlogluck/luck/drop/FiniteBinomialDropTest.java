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

public class FiniteBinomialDropTest {

    @Test
    public void testFiniteBinomial_singleDropSource_multiplePossible() {
        double dropChance = 0.01;
        int kc = 100;
        int numObtained = 1;
        double expectedLuck = 0.36603;
        // dryness is calculated as normal here because the player could receive a max of 2, but only has 1.
        double expectedDryness = 0.26424;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 2);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_singleDropSource_notObtainedYet() {
        double dropChance = 0.01;
        int kc = 100;
        int numObtained = 0;
        double expectedLuck = 0;
        // luck and dryness are both calculated as normal if not received yet.
        double expectedDryness = 0.63397;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_singleDropSource() {
        double dropChance = 0.01;
        int kc = 100;
        int numObtained = 1;
        double expectedLuck = 0.36603;
        // luck is calculated as normal, but having received the item, dryness is zeroed out because 0 players have
        // more drops.
        //        double expectedDryness = 0.26424;
        double expectedDryness = 0;

        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_withMultipleSources() {
        double dropChance = 0.01;
        int kc1 = 40;
        int kc2 = 60;
        int numObtained = 1;
        double expectedLuck = 0.36603;
        // luck is calculated as normal, but having received the item, dryness is zeroed out because 0 players have
        // more drops.
        //        double expectedDryness = 0.26424;
        double expectedDryness = 0;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        Map<String, Integer> kcs = ImmutableMap.of(
                LogItemSourceInfo.ARTIO_KILLS.getName(), kc1,
                LogItemSourceInfo.CALLISTO_KILLS.getName(), kc2);
        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcs(kcs);

        List<RollInfo> rollInfos = ImmutableList.of(
                new RollInfo(LogItemSourceInfo.ARTIO_KILLS, dropChance),
                new RollInfo(LogItemSourceInfo.CALLISTO_KILLS, dropChance));
        FiniteBinomialDrop drop = new FiniteBinomialDrop(rollInfos, 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_0Obtained() {
        double dropChance = 0.01;
        int kc = 100;
        int numObtained = 0;
        double expectedLuck = 0;
        double expectedDryness = 0.63397;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, false, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_0Trials() {
        double dropChance = 0.5;
        int kc = 0;
        int numObtained = 0;
        double expectedLuck = 0;
        double expectedDryness = 0;
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_spooooooooooooooooned() {
        double dropChance = 0.5;
        int kc = 100000000;
        int numObtained = 100000000;
        double expectedLuck = 1;
        double expectedDryness = 0;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.000000001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_dryyyyyyyyyyyyyyyyyyyyy() {
        double dropChance = 0.5;
        int kc = 100000;
        int numObtained = 0;
        double expectedLuck = 0;
        double expectedDryness = 1;
        double tolerance = 0.000000001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, false, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(LogItemSourceInfo.ABYSSAL_SIRE_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testFiniteBinomial_missingDropSource() {
        double dropChance = 0.5;
        int kc = 10;
        int numObtained = 11;
        double expectedLuck = -1;
        double expectedDryness = -1;
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

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
        String expectedKcString = "100x Abyssal Sire kills";

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ABYSSAL_SIRE_KILLS, dropChance), 1);

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

        FiniteBinomialDrop drop = new FiniteBinomialDrop(rollInfos, 1);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcs(kcs);

        String actualKcString = drop.getKillCountDescription(mockCollectionLog);
        assertEquals(expectedKcString, actualKcString);
    }

    @Test
    public void testFiniteBinomial_multiRoll_singleDropSource() {
        double dropChance = 0.01;
        int kc = 50;
        int rollsPerKc = 2;
        int numObtained = 1;
        double expectedLuck = 0.36603;
        // luck is calculated as normal, but having received the item, dryness is zeroed out because 0 players have
        // more drops.
        //        double expectedDryness = 0.26424;
        double expectedDryness = 0;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        FiniteBinomialDrop drop = new FiniteBinomialDrop(new RollInfo(LogItemSourceInfo.ZULRAH_KILLS, dropChance, rollsPerKc), 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKc(
                LogItemSourceInfo.ZULRAH_KILLS.getName(), kc);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }

    @Test
    public void testMultiRollFiniteBinomial_multiRoll_withMultipleSources() {
        double dropChance = 0.01;
        int kc1 = 20;
        int rollsPerBoss1 = 2;
        int kc2 = 20;
        int rollsPerBoss2 = 3;
        int numObtained = 1;
        double expectedLuck = 0.36603;
        // luck is calculated as normal, but having received the item, dryness is zeroed out because 0 players have
        // more drops.
        //        double expectedDryness = 0.26424;
        double expectedDryness = 0;
        // expected probabilities calculated online, with the following sig digits
        double tolerance = 0.00001;

        Map<String, Integer> kcs = ImmutableMap.of(
                LogItemSourceInfo.ARTIO_KILLS.getName(), kc1,
                LogItemSourceInfo.CALLISTO_KILLS.getName(), kc2);
        List<RollInfo> rollInfos = ImmutableList.of(
                new RollInfo(LogItemSourceInfo.ARTIO_KILLS, dropChance, rollsPerBoss1),
                new RollInfo(LogItemSourceInfo.CALLISTO_KILLS, dropChance, rollsPerBoss2));
        CollectionLog mockCollectionLog = CollectionLogLuckTestUtils.getMockCollectionLogWithKcs(kcs);

        FiniteBinomialDrop drop = new FiniteBinomialDrop(rollInfos, 1);

        CollectionLogItem mockItem = new CollectionLogItem(1234, "some item name", numObtained, true, 0);

        double actualLuck = drop.calculateLuck(mockItem, mockCollectionLog, null);
        assertEquals(expectedLuck, actualLuck, tolerance);

        double actualDryness = drop.calculateDryness(mockItem, mockCollectionLog, null);
        assertEquals(expectedDryness, actualDryness, tolerance);
    }
}