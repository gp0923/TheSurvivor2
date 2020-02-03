package the_survivor.cards.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_survivor.CardUtils;
import the_survivor.Constants;
import the_survivor.CustomTags;
import the_survivor.cards.SurvivorCard;
import the_survivor.patches.AbstractCardEnum;

public class Scrap extends SurvivorCard {
    public static final String ID = CardUtils.getCardIDFromName("Scrap");
    public static final String IMG_PATH = Constants.Cards.DEFAULT_SKILL_PATH;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Scrap() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.TOPAZ,
                CardRarity.SPECIAL, CardTarget.NONE);
        this.tags.add(CustomTags.SCRAP);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) { /*Exhausts itself*/}

    @Override
    public AbstractCard makeCopy() {
        return new Scrap();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }
}
