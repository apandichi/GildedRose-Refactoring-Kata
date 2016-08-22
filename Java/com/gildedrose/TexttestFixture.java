package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.Test;

public class TexttestFixture {

    @Test
    public void should_update_quality_of_items() throws Exception {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 2;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < days; i++) {
            builder.append("-------- day " + i + " --------").append("\r");
            builder.append("name, sellIn, quality").append("\r");
            for (Item item : items) {
                builder.append(item).append("\r");
            }
            app.updateQuality();
        }

        Approvals.verify(builder.toString());
    }

}
