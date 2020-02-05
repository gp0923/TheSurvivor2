package the_survivor.cards.common;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_survivor.cards.CardUtils;
import the_survivor.Constants;
import the_survivor.cards.SurvivorCard;
import the_survivor.cards.special.Scrap;
import the_survivor.patches.AbstractCardEnum;

public class Salvage extends SurvivorCard {
    public static final String ID = CardUtils.getCardIDFromName("Salvage");
    public static final String IMG_PATH = Constants.Cards.DEFAULT_SKILL_PATH;
    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    public Salvage() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.TOPAZ,
                CardRarity.COMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
        this.cardsToPreview = new Scrap();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(this.magicNumber, false, true, true));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Scrap(), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Salvage();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
