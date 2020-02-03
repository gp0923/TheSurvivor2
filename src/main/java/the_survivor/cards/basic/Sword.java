package the_survivor.cards.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_survivor.CardUtils;
import the_survivor.Constants;
import the_survivor.CustomTags;
import the_survivor.cards.DegradableCard;
import the_survivor.patches.AbstractCardEnum;

public class Sword extends DegradableCard {
    public static final String ID = CardUtils.getCardIDFromName("Sword");
    public static final String IMG_PATH = Constants.Cards.DEFAULT_ATTACK_PATH;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int SECOND_UPGRADE_PLUS_DMG = 10;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DAMAGED_NAME = "Damaged " + NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public Sword() {
        super(ID, DAMAGED_NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.TOPAZ,
                CardRarity.BASIC, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.DAMAGED);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sword();
    }

    @Override
    public void upgrade() {
        if (timesUpgraded == 0) {
            this.upgraded = true;
            upgradeName(NAME);
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.tags.remove(CustomTags.DAMAGED);
        } else if (timesUpgraded == 1) {
            this.upgradeName();
            this.upgradeDamage(SECOND_UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public void degrade() {
        if (timesUpgraded == 1) {
            degradeName(DAMAGED_NAME);
            degradeDamage(UPGRADE_PLUS_DMG, true);
            this.tags.add(CustomTags.DAMAGED);
        } else if (timesUpgraded == 2){
            degradeName(NAME);
            degradeDamage(SECOND_UPGRADE_PLUS_DMG, false);
        }
    }
}
