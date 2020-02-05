package the_survivor.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import the_survivor.Constants;
import the_survivor.CustomTags;
import the_survivor.actions.ModifyDurabilityAction;
import the_survivor.cards.CardUtils;
import the_survivor.cards.DurableCard;
import the_survivor.patches.AbstractCardEnum;

public class SwordR extends DurableCard {
    public static final String ID = CardUtils.getCardIDFromName("SwordR");
    public static final String IMG_PATH = Constants.Cards.DEFAULT_ATTACK_PATH;
    public static final int MAX_DURABILITY = 0;
    public static final int UPGRADE_PLUS_DURABILITY_MAX = 2;
    public static final int SECOND_UPGRADE_PLUS_DURABILITY_MAX = -1;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int SECOND_UPGRADE_PLUS_DMG = 8;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;
    private static final int SECOND_UPGRADE_PLUS_MAGIC_NUMBER = 2;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DAMAGED_NAME = "Damaged Sword";


    public SwordR() {
        super(ID, DAMAGED_NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.TOPAZ,
                CardRarity.RARE, CardTarget.ENEMY);
        damage = this.baseDamage = ATTACK_DMG;
        magicNumber = baseMagicNumber = MAGIC_NUMBER;
        tags.add(CustomTags.DAMAGED);
        durability = this.maxDurability = this.baseDurability = MAX_DURABILITY;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (timesUpgraded > 0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber)));
        if (maxDurability > 0)
            AbstractDungeon.actionManager.addToBottom(new ModifyDurabilityAction(this, -1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordR();
    }

    @Override
    public void upgrade() {
        if (timesUpgraded == 0) {
            upgradeName(NAME);
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            upgradeMaxDurability(UPGRADE_PLUS_DURABILITY_MAX);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
            tags.remove(CustomTags.DAMAGED);
        } else if (timesUpgraded == 1) {
            upgradeName();
            upgradeDamage(SECOND_UPGRADE_PLUS_DMG);
            upgradeMagicNumber(SECOND_UPGRADE_PLUS_MAGIC_NUMBER);
            upgradeMaxDurability(SECOND_UPGRADE_PLUS_DURABILITY_MAX);
        }
    }

    @Override
    public void degrade() {
        if (timesUpgraded == 1) {
            degradeName(DAMAGED_NAME);
            degradeDamage(UPGRADE_PLUS_DMG, true);
            degradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER, true);
            degradeMaxDurability(UPGRADE_PLUS_DURABILITY_MAX);
            this.rawDescription = DESCRIPTION;
            this.initializeDescription();
            tags.add(CustomTags.DAMAGED);
        } else if (timesUpgraded == 2) {
            degradeName(NAME);
            degradeDamage(SECOND_UPGRADE_PLUS_DMG, false);
            degradeMagicNumber(SECOND_UPGRADE_PLUS_MAGIC_NUMBER, true);
            degradeMaxDurability(SECOND_UPGRADE_PLUS_DURABILITY_MAX);
        }
    }
}
