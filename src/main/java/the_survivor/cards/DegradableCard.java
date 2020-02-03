package the_survivor.cards;

public abstract class DegradableCard extends SurvivorCard implements Degradable {

    public DegradableCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void degradeName(String name){
        if (timesUpgraded > 0)
            --this.timesUpgraded;
        if (timesUpgraded == 0)
            this.upgraded = false;
        this.name = name;
        this.initializeTitle();
    }

    protected void degradeDamage(int amount, boolean isToBase) {
        this.baseDamage -= amount;
        this.upgradedDamage = isToBase;
    }
}
