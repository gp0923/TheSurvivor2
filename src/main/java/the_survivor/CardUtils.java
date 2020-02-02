package the_survivor;

import the_survivor.cards.SurvivorCard;

public class CardUtils {
    public static String getCardIDFromName(String name){
        return Constants.Mod.CARD_PREFIX + ":" + name;
    }
}
