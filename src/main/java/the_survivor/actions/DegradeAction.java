package the_survivor.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_survivor.cards.CardUtils;
import the_survivor.cards.DegradableCard;

public class DegradeAction extends AbstractGameAction {

    private DegradableCard degradable;
    private boolean updateDeck;

    public DegradeAction(DegradableCard d, boolean updateDeck) {
        degradable = d;
        this.updateDeck = updateDeck;
    }

    @Override
    public void update() {
        if (updateDeck) {
            AbstractCard masterCard = (CardUtils.getMasterDeckEquivalent(degradable));
            if (masterCard instanceof DegradableCard && masterCard.timesUpgraded == degradable.timesUpgraded)
                ((DegradableCard) masterCard).degrade();
        }
        degradable.degrade();
        isDone = true;
    }
}
