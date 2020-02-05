package the_survivor.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import the_survivor.cards.DurableCard;

/**
 * Action that changes durability by the passed amount (limit [0, maxDurability]).
 * If this leaves the card with 0 durability, the card is degraded.
 */
public class ModifyDurabilityAction extends AbstractGameAction {
    private DurableCard card;
    private int durabilityChange;
    private final Logger logger = LogManager.getLogger(ModifyDurabilityAction.class.getName());

    public ModifyDurabilityAction(DurableCard card, int change) {
        this.card = card;
        durabilityChange = change;
    }

    @Override
    public void update() {
        logger.info("Started update");
        int d = card.durability + durabilityChange;
        if (d < 0)
            d = 0;
        else if (d > card.maxDurability)
            d = card.maxDurability;

        card.durability = d;
        card.initializeDescription();

        if (card.durability == 0)
            AbstractDungeon.actionManager.addToBottom(new DegradeAction(card, true));
        logger.info("Finished update");
        isDone = true;
    }
}
