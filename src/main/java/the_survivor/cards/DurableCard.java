package the_survivor.cards;

public abstract class DurableCard extends DegradableCard {
    public int durability = 1;
    public int baseDurability = durability;
    public int maxDurability = durability;
    public boolean upgradedDurability = false;

    public DurableCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void upgradeMaxDurability(int amount){
        upgradeMaxDurability(amount, true);
    }

    public void upgradeMaxDurability(int amount, boolean resetCurrent){
        maxDurability += amount;
        if (resetCurrent)
            durability = maxDurability;
        else if (amount > 0)
            durability += amount;
        if (durability < 0)
            durability = 0;
        if (maxDurability < 0)
            maxDurability = 0;
        if (durability > maxDurability)
            durability = maxDurability;
        upgradedDurability = true;
    }

    public void degradeMaxDurability(int amount){
        degradeMaxDurability(amount, true);
    }

    public void degradeMaxDurability(int amount, boolean resetCurrent){
        upgradeMaxDurability(-amount, resetCurrent);
        if (maxDurability == baseDurability)
            upgradedDurability = false;
    }

}
