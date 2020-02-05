package the_survivor.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_survivor.Constants;
import the_survivor.CustomTags;
import the_survivor.actions.ModifyDurabilityAction;
import the_survivor.cards.CardUtils;
import the_survivor.cards.DurableCard;
import the_survivor.patches.AbstractCardEnum;

public class Sword extends DurableCard {
    public static final String ID = CardUtils.getCardIDFromName("Sword");
    public static final String IMG_PATH = Constants.Cards.DEFAULT_ATTACK_PATH;
    public static final int MAX_DURABILITY = 0;
    public static final int UPGRADE_PLUS_DURABILITY_MAX = 3;
    public static final int SECOND_UPGRADE_PLUS_DURABILITY_MAX = -1;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int SECOND_UPGRADE_PLUS_DMG = 6;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DAMAGED_NAME = "Damaged Sword";


    public Sword() {
        super(ID, DAMAGED_NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.TOPAZ,
                CardRarity.COMMON, CardTarget.ENEMY);
        damage = this.baseDamage = ATTACK_DMG;
        tags.add(CustomTags.DAMAGED);
        durability = this.maxDurability = this.baseDurability = MAX_DURABILITY;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (maxDurability > 0)
            AbstractDungeon.actionManager.addToBottom(new ModifyDurabilityAction(this, -1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sword();
    }

    @Override
    public void upgrade() {
        if (timesUpgraded == 0) {
            upgradeName(NAME);
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMaxDurability(UPGRADE_PLUS_DURABILITY_MAX);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
            tags.remove(CustomTags.DAMAGED);
        } else if (timesUpgraded == 1) {
            upgradeName();
            upgradeDamage(SECOND_UPGRADE_PLUS_DMG);
            upgradeMaxDurability(SECOND_UPGRADE_PLUS_DURABILITY_MAX);
        }
    }

    @Override
    public void degrade() {
        if (timesUpgraded == 1) {
            degradeName(DAMAGED_NAME);
            degradeDamage(UPGRADE_PLUS_DMG, true);
            degradeMaxDurability(UPGRADE_PLUS_DURABILITY_MAX);
            this.rawDescription = DESCRIPTION;
            this.initializeDescription();
            tags.add(CustomTags.DAMAGED);
        } else if (timesUpgraded == 2) {
            degradeName(NAME);
            degradeDamage(SECOND_UPGRADE_PLUS_DMG, false);
            degradeMaxDurability(SECOND_UPGRADE_PLUS_DURABILITY_MAX);
        }
    }
}
