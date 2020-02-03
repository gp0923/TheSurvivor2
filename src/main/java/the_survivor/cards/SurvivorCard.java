package the_survivor.cards;

import basemod.abstracts.CustomCard;

public abstract class SurvivorCard extends CustomCard {
    public SurvivorCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void upgradeName(String name){
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = name;
        this.initializeTitle();
    }
}
