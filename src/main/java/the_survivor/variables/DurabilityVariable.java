package the_survivor.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_survivor.cards.DurableCard;

public class DurabilityVariable extends DynamicVariable {
    public static final String KEY = "DUR";

    @Override
    public String key() {
        return KEY;
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        if (abstractCard instanceof DurableCard) {
            return ((DurableCard) abstractCard).durability != ((DurableCard) abstractCard).maxDurability;
        }
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        if (abstractCard instanceof DurableCard) {
            return ((DurableCard) abstractCard).durability;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        if (abstractCard instanceof DurableCard) {
            return ((DurableCard) abstractCard).maxDurability;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        if (abstractCard instanceof DurableCard) {
            return ((DurableCard) abstractCard).upgradedDurability;
        }
        return false;
    }
}
