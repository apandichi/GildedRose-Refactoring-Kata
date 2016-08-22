package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// test adapted from http://craftedsw.blogspot.ro/2012/11/testing-legacy-code-with-golden-master.html
public class GildedRoseApprovalTest {

    private static final int FIXED_SEED = 100;
    private static final int NUMBER_OF_RANDOM_ITEMS = 2000;
    private static final int MINIMUM = 3;
    private static final int MAXIMUN = 101;

    private String[] itemNames = {"+5 Dexterity Vest",
            "Aged Brie",
            "Elixir of the Mongoose",
            "Sulfuras, Hand of Ragnaros",
            "Backstage passes to a TAFKAL80ETC concert",
            "Conjured Mana Cake"
    };

    private Random random = new Random(FIXED_SEED);
    private GildedRose gildedRose;

    @Before
    public void initialise() {

    }

    @Test
    public void
    should_generate_update_quality_output() throws Exception {
        List<Item> items = generateRandomItems(NUMBER_OF_RANDOM_ITEMS);
        String dayOne = getStringRepresentationFor(items);
        gildedRose = new GildedRose(items.toArray(new Item[items.size()]));
        gildedRose.updateQuality();
        String dayTwo = getStringRepresentationFor(items);

        Approvals.verify(dayOne + dayTwo);
    }

    private List<Item> generateRandomItems(int totalNumberOfRandomItems) {
        List<Item> items = new ArrayList<Item>();
        for (int cnt = 0; cnt < totalNumberOfRandomItems; cnt++) {
            items.add(new Item(itemName(), sellIn(), quality()));
        }
        return items;
    }

    private String itemName() {
        return itemNames[0 + random.nextInt(itemNames.length)];
    }

    private int sellIn() {
        return randomNumberBetween(MINIMUM, MAXIMUN);
    }

    private int quality() {
        return randomNumberBetween(MINIMUM, MAXIMUN);
    }

    private int randomNumberBetween(int minimum, int maximum) {
        return minimum + random.nextInt(maximum);
    }

    private String getStringRepresentationFor(List<Item> items) {
        StringBuilder builder = new StringBuilder();
        for (Item item : items) {
            builder.append(item).append("\r");
        }
        return builder.toString();
    }

}
