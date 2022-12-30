package com.doej.hyblockutils.item;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an item from the Hypixel SkyBlock bazaar
 *
 * @author doej1367
 */
public class BazaarItem implements Comparable<BazaarItem> {
    private static final Map<String, String> nameAlias;

    static {
        nameAlias = new HashMap<>();
        nameAlias.put("LOG", "OAK_LOG");
        nameAlias.put("LOG:1", "SPRUCE_LOG");
        nameAlias.put("LOG:2", "BIRCH_LOG");
        nameAlias.put("LOG_2", "ACACIA_LOG");
        nameAlias.put("LOG_2:1", "DARK_OAK_LOG");
        nameAlias.put("LOG:3", "JUNGLE_LOG");
        nameAlias.put("HUGE_MUSHROOM_1", "BROWN_MUSHROOM_BLOCK");
        nameAlias.put("HUGE_MUSHROOM_2", "RED_MUSHROOM_BLOCK");
        nameAlias.put("ENCHANTED_HUGE_MUSHROOM_1", "ENCHANTED_BROWN_MUSHROOM_BLOCK");
        nameAlias.put("ENCHANTED_HUGE_MUSHROOM_2", "ENCHANTED_RED_MUSHROOM_BLOCK");
        nameAlias.put("SULPHUR", "GUNPOWDER");
        nameAlias.put("RABBIT", "RAW_RABBIT");
        nameAlias.put("ENCHANTED_RABBIT", "ENCHANTED_RAW_RABBIT");
        nameAlias.put("INK_SACK:3", "COCOA_BEANS");
        nameAlias.put("INK_SACK:4", "LAPIS_LAZULI");
        nameAlias.put("RAW_FISH:1", "RAW_SALMON");
        nameAlias.put("RAW_FISH:2", "CLOWNFISH");
        nameAlias.put("RAW_FISH:3", "PUFFERFISH");
        nameAlias.put("ENCHANTED_CARROT_STICK", "ENCHANTED_CARROT_ON_A_STICK");
    }

    private static final Map<String, String> categoryName;

    static {
        categoryName = new HashMap<>();
        categoryName.put("WHEAT", "WHEAT01");
        categoryName.put("ENCHANTED BREAD", "BREAD01");
        categoryName.put("HAY BLOCK", "WHEAT02");
        categoryName.put("ENCHANTED HAY BLOCK", "WHEAT03");
        categoryName.put("TIGHTLY TIED HAY BALE", "WHEAT04");
        categoryName.put("SEEDS", "SEEDS01");
        categoryName.put("ENCHANTED SEEDS", "SEEDS02");
        categoryName.put("CARROT ITEM", "CARROT01");
        categoryName.put("ENCHANTED CARROT", "CARROT02");
        categoryName.put("ENCHANTED CARROT ON A STICK", "CARROTSTICK01");
        categoryName.put("ENCHANTED GOLDEN CARROT", "CARROT03");
        categoryName.put("POTATO ITEM", "POTATO01");
        categoryName.put("ENCHANTED POTATO", "POTATO02");
        categoryName.put("ENCHANTED BAKED POTATO", "POTATO03");
        categoryName.put("PUMPKIN", "PUMPKIN01");
        categoryName.put("ENCHANTED PUMPKIN", "PUMPKIN02");
        categoryName.put("POLISHED PUMPKIN", "PUMPKIN03");
        categoryName.put("MELON", "MELON01");
        categoryName.put("ENCHANTED MELON", "MELON02");
        categoryName.put("ENCHANTED GLISTERING MELON", "MELONGLISTERING01");
        categoryName.put("ENCHANTED MELON BLOCK", "MELON03");
        categoryName.put("RED MUSHROOM", "MUSHROOM11");
        categoryName.put("ENCHANTED RED MUSHROOM", "MUSHROOM12");
        categoryName.put("RED MUSHROOM BLOCK", "MUSHROOM13");
        categoryName.put("ENCHANTED RED MUSHROOM BLOCK", "MUSHROOM14");
        categoryName.put("BROWN MUSHROOM", "MUSHROOM21");
        categoryName.put("ENCHANTED BROWN MUSHROOM", "MUSHROOM22");
        categoryName.put("BROWN MUSHROOM BLOCK", "MUSHROOM23");
        categoryName.put("ENCHANTED BROWN MUSHROOM BLOCK", "MUSHROOM24");
        categoryName.put("COCOA BEANS", "COCOA01");
        categoryName.put("ENCHANTED COCOA", "COCOA02");
        categoryName.put("ENCHANTED COOKIE", "COCOA03");
        categoryName.put("CACTUS", "CACTUS01");
        categoryName.put("ENCHANTED CACTUS GREEN", "CACTUS02");
        categoryName.put("ENCHANTED CACTUS", "CACTUS03");
        categoryName.put("SUGAR CANE", "SUGAR01");
        categoryName.put("ENCHANTED SUGAR", "SUGAR02");
        categoryName.put("ENCHANTED PAPER", "PAPER01");
        categoryName.put("ENCHANTED SUGAR CANE", "SUGAR04");
        categoryName.put("LEATHER", "LEATHER01");
        categoryName.put("ENCHANTED LEATHER", "LEATHER02");
        categoryName.put("RAW BEEF", "BEEF01");
        categoryName.put("ENCHANTED RAW BEEF", "BEEF02");
        categoryName.put("PORK", "PORK01");
        categoryName.put("ENCHANTED PORK", "PORK02");
        categoryName.put("ENCHANTED GRILLED PORK", "PORK03");
        categoryName.put("RAW CHICKEN", "CHICKEN01");
        categoryName.put("ENCHANTED RAW CHICKEN", "CHICKEN02");
        categoryName.put("SUPER EGG", "EGG01");
        categoryName.put("ENCHANTED CAKE", "CAKE01");
        categoryName.put("ENCHANTED EGG", "EGG02");
        categoryName.put("FEATHER", "FEATHER01");
        categoryName.put("ENCHANTED FEATHER", "FEATHER02");
        categoryName.put("MUTTON", "MUTTON01");
        categoryName.put("ENCHANTED MUTTON", "MUTTON02");
        categoryName.put("ENCHANTED COOKED MUTTON", "MUTTON03");
        categoryName.put("RAW RABBIT", "RABBIT11");
        categoryName.put("ENCHANTED RAW RABBIT", "RABBIT12");
        categoryName.put("RABBIT FOOT", "RABBIT21");
        categoryName.put("ENCHANTED RABBIT FOOT", "RABBIT22");
        categoryName.put("RABBIT HIDE", "RABBIT31");
        categoryName.put("ENCHANTED RABBIT HIDE", "RABBIT32");
        categoryName.put("NETHER STALK", "NETHERWART01");
        categoryName.put("ENCHANTED NETHER STALK", "NETHERWART02");
        categoryName.put("MUTANT NETHER STALK", "NETHERWART03");
        categoryName.put("COBBLESTONE", "COBBLESTONE01");
        categoryName.put("ENCHANTED COBBLESTONE", "COBBLESTONE02");
        categoryName.put("COAL", "COAL01");
        categoryName.put("ENCHANTED COAL", "COAL02");
        categoryName.put("ENCHANTED CHARCOAL", "COALCHAR01");
        categoryName.put("ENCHANTED COAL BLOCK", "COAL03");
        categoryName.put("IRON INGOT", "IRON01");
        categoryName.put("ENCHANTED IRON", "IRON02");
        categoryName.put("ENCHANTED IRON BLOCK", "IRON03");
        categoryName.put("GOLD INGOT", "GOLD01");
        categoryName.put("ENCHANTED GOLD", "GOLD02");
        categoryName.put("ENCHANTED GOLD BLOCK", "GOLD03");
        categoryName.put("DIAMOND", "DIAMOND01");
        categoryName.put("ENCHANTED DIAMOND", "DIAMOND02");
        categoryName.put("ENCHANTED DIAMOND BLOCK", "DIAMOND03");
        categoryName.put("REFINED DIAMOND", "DIAMOND04");
        categoryName.put("LAPIS LAZULI", "LAPIS01");
        categoryName.put("ENCHANTED LAPIS LAZULI", "LAPIS02");
        categoryName.put("ENCHANTED LAPIS LAZULI BLOCK", "LAPIS04");
        categoryName.put("ROUGH JADE GEM", "GEM11");
        categoryName.put("FLAWED JADE GEM", "GEM12");
        categoryName.put("FINE JADE GEM", "GEM013");
        categoryName.put("FLAWLESS JADE GEM", "GEM14");
        categoryName.put("PERFECT JADE GEM", "GEM15");
        categoryName.put("ROUGH AMBER GEM", "GEM21");
        categoryName.put("FLAWED AMBER GEM", "GEM22");
        categoryName.put("FINE AMBER GEM", "GEM23");
        categoryName.put("FLAWLESS AMBER GEM", "GEM24");
        categoryName.put("PERFECT AMBER GEM", "GEM25");
        categoryName.put("ROUGH TOPAZ GEM", "GEM31");
        categoryName.put("FLAWED TOPAZ GEM", "GEM32");
        categoryName.put("FINE TOPAZ GEM", "GEM33");
        categoryName.put("FLAWLESS TOPAZ GEM", "GEM34");
        categoryName.put("PERFECT TOPAZ GEM", "GEM35");
        categoryName.put("ROUGH SAPPHIRE GEM", "GEM41");
        categoryName.put("FLAWED SAPPHIRE GEM", "GEM42");
        categoryName.put("FINE SAPPHIRE GEM", "GEM43");
        categoryName.put("FLAWLESS SAPPHIRE GEM", "GEM44");
        categoryName.put("PERFECT SAPPHIRE GEM", "GEM45");
        categoryName.put("ROUGH AMETHYST GEM", "GEM51");
        categoryName.put("FLAWED AMETHYST GEM", "GEM52");
        categoryName.put("FINE AMETHYST GEM", "GEM53");
        categoryName.put("FLAWLESS AMETHYST GEM", "GEM54");
        categoryName.put("PERFECT AMETHYST GEM", "GEM55");
        categoryName.put("ROUGH JASPER GEM", "GEM61");
        categoryName.put("FLAWED JASPER GEM", "GEM62");
        categoryName.put("FINE JASPER GEM", "GEM63");
        categoryName.put("FLAWLESS JASPER GEM", "GEM64");
        categoryName.put("PERFECT JASPER GEM", "GEM65");
        categoryName.put("ROUGH RUBY GEM", "GEM71");
        categoryName.put("FLAWED RUBY GEM", "GEM72");
        categoryName.put("FINE RUBY GEM", "GEM73");
        categoryName.put("FLAWLESS RUBY GEM", "GEM74");
        categoryName.put("PERFECT RUBY GEM", "GEM75");
        categoryName.put("EMERALD", "EMERALD01");
        categoryName.put("ENCHANTED EMERALD", "EMERALD02");
        categoryName.put("ENCHANTED EMERALD BLOCK", "EMERALD03");
        categoryName.put("REDSTONE", "REDSTONE01");
        categoryName.put("ENCHANTED REDSTONE", "REDSTONE02");
        categoryName.put("ENCHANTED REDSTONE BLOCK", "REDSTONE03");
        categoryName.put("QUARTZ", "QUARTZ01");
        categoryName.put("ENCHANTED QUARTZ", "QUARTZ02");
        categoryName.put("ENCHANTED QUARTZ BLOCK", "QUARTZ03");
        categoryName.put("OBSIDIAN", "OBSIDIAN1");
        categoryName.put("ENCHANTED OBSIDIAN", "OBSIDIAN2");
        categoryName.put("GLOWSTONE DUST", "GLOWSTONE01");
        categoryName.put("ENCHANTED GLOWSTONE DUST", "GLOWSTONE02");
        categoryName.put("ENCHANTED GLOWSTONE", "GLOWSTONE03");
        categoryName.put("ENCHANTED REDSTONE LAMP", "REDSTONELAMP01");
        categoryName.put("FLINT", "FLINT01");
        categoryName.put("ENCHANTED FLINT", "FLINT02");
        categoryName.put("GRAVEL", "FLINT00");
        categoryName.put("HARD STONE", "HARDSTONE01");
        categoryName.put("ENCHANTED HARD STONE", "HARDSTONE02");
        categoryName.put("CONCENTRATED STONE", "HARDSTONE03");
        categoryName.put("ICE", "ICE01");
        categoryName.put("PACKED ICE", "ICE02");
        categoryName.put("ENCHANTED ICE", "ICE03");
        categoryName.put("ENCHANTED PACKED ICE", "ICE04");
        categoryName.put("NETHERRACK", "NETHERRACK01");
        categoryName.put("ENCHANTED NETHERRACK", "NETHERRACK02");
        categoryName.put("SAND", "SAND01");
        categoryName.put("ENCHANTED SAND", "SAND02");
        categoryName.put("ENDER STONE", "ENDSTONE01");
        categoryName.put("ENCHANTED ENDSTONE", "ENDSTONE02");
        categoryName.put("SNOW BALL", "SNOW01");
        categoryName.put("SNOW BLOCK", "SNOW02");
        categoryName.put("ENCHANTED SNOW BLOCK", "SNOW03");
        categoryName.put("MITHRIL ORE", "MITHRIL01");
        categoryName.put("ENCHANTED MITHRIL", "MITHRIL02");
        categoryName.put("REFINED MITHRIL", "MITHRIL03");
        categoryName.put("TITANIUM ORE", "TITANIUM01");
        categoryName.put("ENCHANTED TITANIUM", "TITANIUM02");
        categoryName.put("REFINED TITANIUM", "TITANIUM03");
        categoryName.put("STARFALL", "STARFALL01");
        categoryName.put("TREASURITE", "TREASURITE01");
        categoryName.put("ROTTEN FLESH", "ROTTENFLESH01");
        categoryName.put("ENCHANTED ROTTEN FLESH", "ROTTENFLESH02");
        categoryName.put("BONE", "BONE01");
        categoryName.put("ENCHANTED BONE", "BONE02");
        categoryName.put("ENCHANTED BONE BLOCK", "BONE03");
        categoryName.put("STRING", "STRING01");
        categoryName.put("ENCHANTED STRING", "STRING02");
        categoryName.put("SPIDER EYE", "SPIDEREYE01");
        categoryName.put("ENCHANTED SPIDER EYE", "SPIDEREYE02");
        categoryName.put("ENCHANTED FERMENTED SPIDER EYE", "SPIDEREYE03");
        categoryName.put("GUNPOWDER", "GUNPOWDER01");
        categoryName.put("ENCHANTED GUNPOWDER", "GUNPOWDER02");
        categoryName.put("ENCHANTED FIREWORK ROCKET", "GUNPOWDER03");
        categoryName.put("ENDER PEARL", "ENDERPEARL01");
        categoryName.put("ENCHANTED ENDER PEARL", "ENDERPEARL02");
        categoryName.put("ENCHANTED EYE OF ENDER", "ENDERPEARL03");
        categoryName.put("ABSOLUTE ENDER PEARL", "ENDERPEARL04");
        categoryName.put("GHAST TEAR", "GHASTTEAR01");
        categoryName.put("ENCHANTED GHAST TEAR", "GHASTTEAR02");
        categoryName.put("SLIME BALL", "SLIME01");
        categoryName.put("ENCHANTED SLIME BALL", "SLIME02");
        categoryName.put("ENCHANTED SLIME BLOCK", "SLIME03");
        categoryName.put("MAGMA CREAM", "MAGMACREAM01");
        categoryName.put("ENCHANTED MAGMA CREAM", "MAGMACREAM02");
        categoryName.put("SLUDGE JUICE", "SLUDGEJUICE01");
        categoryName.put("YOGGIE", "YOGGIE01");
        categoryName.put("BLAZE ROD", "BLAZE01");
        categoryName.put("ENCHANTED BLAZE POWDER", "BLAZE02");
        categoryName.put("ENCHANTED BLAZE ROD", "BLAZE03");
        categoryName.put("GRIFFIN FEATHER", "GRIFFINFEATHER01");
        categoryName.put("DAEDALUS STICK", "DAEDALUSSTICK01");
        categoryName.put("ANCIENT CLAW", "ANCIENTCLAW01");
        categoryName.put("ENCHANTED ANCIENT CLAW", "ANCIENTCLAW02");
        categoryName.put("REVENANT FLESH", "REVENANTFLESH01");
        categoryName.put("REVENANT VISCERA", "REVENANTFLESH02");
        categoryName.put("TARANTULA WEB", "TARANTULAWEB01");
        categoryName.put("TARANTULA SILK", "TARANTULAWEB02");
        categoryName.put("WOLF TOOTH", "WOLFTOOTH01");
        categoryName.put("GOLDEN TOOTH", "WOLFTOOTH02");
        categoryName.put("NULL SPHERE", "NULLSPHERE01");
        categoryName.put("NULL OVOID", "NULLSPHERE02");
        categoryName.put("NULL ATOM", "NULLSPHERE03");
        categoryName.put("RAW SOULFLOW", "SOULFLOW01");
        categoryName.put("SOULFLOW", "SOULFLOW02");
        categoryName.put("OAK LOG", "WOOD11");
        categoryName.put("ENCHANTED OAK LOG", "WOOD12");
        categoryName.put("SPRUCE LOG", "WOOD21");
        categoryName.put("ENCHANTED SPRUCE LOG", "WOOD22");
        categoryName.put("BIRCH LOG", "WOOD31");
        categoryName.put("ENCHANTED BIRCH LOG", "WOOD32");
        categoryName.put("DARK OAK LOG", "WOOD41");
        categoryName.put("ENCHANTED DARK OAK LOG", "WOOD42");
        categoryName.put("ACACIA LOG", "WOOD51");
        categoryName.put("ENCHANTED ACACIA LOG", "WOOD52");
        categoryName.put("JUNGLE LOG", "WOOD61");
        categoryName.put("ENCHANTED JUNGLE LOG", "WOOD62");
        categoryName.put("RAW FISH", "FISH01");
        categoryName.put("ENCHANTED RAW FISH", "FISH02");
        categoryName.put("ENCHANTED COOKED FISH", "FISH03");
        categoryName.put("RAW SALMON", "FISH11");
        categoryName.put("ENCHANTED RAW SALMON", "FISH12");
        categoryName.put("ENCHANTED COOKED SALMON", "FISH13");
        categoryName.put("CLOWNFISH", "FISH21");
        categoryName.put("ENCHANTED CLOWNFISH", "FISH22");
        categoryName.put("PUFFERFISH", "FISH31");
        categoryName.put("ENCHANTED PUFFERFISH", "FISH32");
        categoryName.put("PRISMARINE SHARD", "PRISMARINESHARD01");
        categoryName.put("ENCHANTED PRISMARINE SHARD", "PRISMARINESHARD02");
        categoryName.put("PRISMARINE CRYSTALS", "PRISMARINECRYSTALS01");
        categoryName.put("ENCHANTED PRISMARINE CRYSTALS", "PRISMARINECRYSTALS02");
        categoryName.put("CLAY BALL", "CLAY01");
        categoryName.put("ENCHANTED CLAY BALL", "CLAY02");
        categoryName.put("WATER LILY", "LILYPAD01");
        categoryName.put("ENCHANTED WATER LILY", "LILYPAD02");
        categoryName.put("INK SACK", "INKSACK01");
        categoryName.put("ENCHANTED INK SACK", "INKSACK02");
        categoryName.put("SPONGE", "SPONGE01");
        categoryName.put("ENCHANTED SPONGE", "SPONGE02");
        categoryName.put("ENCHANTED WET SPONGE", "SPONGE03");
        categoryName.put("CARROT BAIT", "BAIT01");
        categoryName.put("MINNOW BAIT", "BAIT02");
        categoryName.put("FISH BAIT", "BAIT03");
        categoryName.put("LIGHT BAIT", "BAIT04");
        categoryName.put("DARK BAIT", "BAIT05");
        categoryName.put("SPOOKY BAIT", "BAIT06");
        categoryName.put("SPIKED BAIT", "BAIT07");
        categoryName.put("BLESSED BAIT", "BAIT08");
        categoryName.put("ICE BAIT", "BAIT09");
        categoryName.put("WHALE BAIT", "BAIT0A");
        categoryName.put("SHARK BAIT", "BAIT0B");
        categoryName.put("NURSE SHARK TOOTH", "SHARKTOOTH1");
        categoryName.put("BLUE SHARK TOOTH", "SHARKTOOTH2");
        categoryName.put("TIGER SHARK TOOTH", "SHARKTOOTH3");
        categoryName.put("GREAT WHITE SHARK TOOTH", "SHARKTOOTH4");
        categoryName.put("SHARK FIN", "SHARKFIN01");
        categoryName.put("ENCHANTED SHARK FIN", "SHARKFIN02");
        categoryName.put("BOOSTER COOKIE", "BOOSTERCOOKIE01");
        categoryName.put("HOT POTATO BOOK", "HOTPOTATOBOOK1");
        categoryName.put("FUMING POTATO BOOK", "HOTPOTATOBOOK2");
        categoryName.put("COMPACTOR", "COMPACTOR1");
        categoryName.put("SUPER COMPACTOR 3000", "COMPACTOR2");
        categoryName.put("DWARVEN COMPACTOR", "COMPACTOR3");
        categoryName.put("SUMMONING EYE", "SUMMONINGEYE01");
        categoryName.put("PROTECTOR FRAGMENT", "DRAGONFRAGMENT1");
        categoryName.put("OLD FRAGMENT", "DRAGONFRAGMENT2");
        categoryName.put("UNSTABLE FRAGMENT", "DRAGONFRAGMENT3");
        categoryName.put("STRONG FRAGMENT", "DRAGONFRAGMENT4");
        categoryName.put("YOUNG FRAGMENT", "DRAGONFRAGMENT5");
        categoryName.put("WISE FRAGMENT", "DRAGONFRAGMENT6");
        categoryName.put("SUPERIOR FRAGMENT", "DRAGONFRAGMENT7");
        categoryName.put("HOLY FRAGMENT", "DRAGONFRAGMENT8");
        categoryName.put("ENCHANTED LAVA BUCKET", "ENCHANTEDLAVABUCKET01");
        categoryName.put("MAGMA BUCKET", "ENCHANTEDLAVABUCKET02");
        categoryName.put("PLASMA BUCKET", "ENCHANTEDLAVABUCKET03");
        categoryName.put("HAMSTER WHEEL", "HAMSTERWHEEL01");
        categoryName.put("FOUL FLESH", "FOULFLESH01");
        categoryName.put("CATALYST", "CATALYST01");
        categoryName.put("HYPER CATALYST", "CATALYST02");
        categoryName.put("POWER CRYSTAL", "STARFALL02");
        categoryName.put("GREEN CANDY", "CANDY01");
        categoryName.put("PURPLE CANDY", "CANDY02");
        categoryName.put("ECTOPLASM", "ECTOPLASM01");
        categoryName.put("PUMPKIN GUTS", "PUMPKINGUTS01");
        categoryName.put("SPOOKY SHARD", "SPOOKYSHARD01");
        categoryName.put("WEREWOLF SKIN", "WEREWOLFSKIN01");
        categoryName.put("SOUL FRAGMENT", "SOULFRAGMENT01");
        categoryName.put("WHITE GIFT", "GIFT1");
        categoryName.put("GREEN GIFT", "GIFT2");
        categoryName.put("RED GIFT", "GIFT3");
        categoryName.put("JERRY BOX GREEN", "JERRYBOX1");
        categoryName.put("JERRY BOX BLUE", "JERRYBOX2");
        categoryName.put("JERRY BOX PURPLE", "JERRYBOX3");
        categoryName.put("JERRY BOX GOLDEN", "JERRYBOX4");
        categoryName.put("REFINED MINERAL", "REFINEDMINERAL01");
        categoryName.put("RECOMBOBULATOR 3000", "RECOMBOBULATOR01");
        categoryName.put("JACOBS TICKET", "JACOBSTICKET01");
        categoryName.put("EXP BOTTLE", "EXPBOTTLE1");
        categoryName.put("GRAND EXP BOTTLE", "EXPBOTTLE2");
        categoryName.put("TITANIC EXP BOTTLE", "EXPBOTTLE3");
        categoryName.put("COLOSSAL EXP BOTTLE", "EXPBOTTLE4");
        categoryName.put("STOCK OF STONKS", "STOCKOFSTONKS");
    }


    private final String category;
    private final String name;
    private final double price;

    public BazaarItem(JSONObject o) {
        String tmp_name;
        double tmp_price;
        try {
            tmp_name = o.getString("product_id");
            String alias = nameAlias.get(tmp_name);
            tmp_name = alias != null ? alias : tmp_name;
            tmp_name = tmp_name.replaceAll("_", " ");
            // TODO maybe change the price to some kind of score value representing e.g. demand?
            tmp_price = Math.round(o.getJSONObject("quick_status").getDouble("buyPrice") * 10) / 10.0;
        } catch (JSONException e) {
            tmp_name = "";
            tmp_price = 0;
        }
        category = lookupCategory(tmp_name);
        name = tmp_name.replaceAll("ENCHANTMENT ULTIMATE ", "").replaceAll("ENCHANTMENT ", "");
        price = tmp_price;
    }

    public char getStartLetter() {
        return category.charAt(0);
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(BazaarItem o) {
        return category.compareTo(o.getCategory());
    }

    private String lookupCategory(String itemName) {
        String category = null;
        if (itemName.startsWith("ENCHANTMENT ")) {
            String[] splitStringArray = itemName.replaceAll("ENCHANTMENT ", "").split(" ");
            if (splitStringArray[splitStringArray.length - 1].matches("[0-9]"))
                splitStringArray[splitStringArray.length - 1] = "0" + splitStringArray[splitStringArray.length - 1];
            String joinedStringArray = "";
            for (String s : splitStringArray) {
                if (!s.matches("ULTIMATE"))
                    joinedStringArray += s;
            }
            category = joinedStringArray.replaceAll(" ", "");
        } else {
            category = categoryName.get(itemName);
        }
        return category != null ? category : ("_" + itemName);
    }

    @Override
    public String toString() {
        return name + " " + price;
    }

}
