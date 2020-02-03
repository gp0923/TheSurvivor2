package the_survivor;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_survivor.cards.SurvivorCard;

public class CardUtils {
    public static String getCardIDFromName(String name){
        return Constants.Mod.CARD_PREFIX + ":" + name;
    }

    //Function from STSLib -- Copyright (c) 2018 Anthony Moore
    public static AbstractCard getMasterDeckEquivalent(AbstractCard card)
    {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(card.uuid)) {
                return c;
            }
        }
        return null;
    }

    public static boolean removeMasterDeckEquivalent(AbstractCard card)
    {
        AbstractCard masterCard = getMasterDeckEquivalent(card);
        if (masterCard != null){
            AbstractDungeon.player.masterDeck.removeCard(masterCard);
            return true;
        }
        return false;
    }
}
