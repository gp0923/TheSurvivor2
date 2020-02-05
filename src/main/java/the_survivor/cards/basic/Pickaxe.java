package the_survivor.cards.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import the_survivor.Constants;
import the_survivor.CustomTags;
import the_survivor.actions.ModifyDurabilityAction;
import the_survivor.cards.CardUtils;
import the_survivor.cards.DurableCard;
import the_survivor.patches.AbstractCardEnum;

public class Pickaxe extends DurableCard {
    public static final String ID = CardUtils.getCardIDFromName("Pickaxe");
    public static final String IMG_PATH = Constants.Cards.DEFAULT_ATTACK_PATH;
    public static final int MAX_DURABILITY = 0;
    public static final int UPGRADE_PLUS_DURABILITY_MAX = 3;
    public static final int SECOND_UPGRADE_PLUS_DURABILITY_MAX = -2;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int SECOND_UPGRADE_PLUS_DMG = 4;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;
    private static final int SECOND_UPGRADE_PLUS_MAGIC_NUMBER = 2;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DAMAGED_NAME = "Damaged Pickaxe";


    public Pickaxe() {
        super(ID, DAMAGED_NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.TOPAZ,
                CardRarity.BASIC, CardTarget.ENEMY);
        damage = baseDamage = ATTACK_DMG;
        magicNumber = baseMagicNumber = MAGIC_NUMBER;
        durability = maxDurability = baseDurability = MAX_DURABILITY;
        tags.add(CustomTags.DAMAGED);

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, this.magicNumber, false)));
        if (maxDurability > 0)
            AbstractDungeon.actionManager.addToBottom(new ModifyDurabilityAction(this, -1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pickaxe();
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
            degradeMagicNumber(SECOND_UPGRADE_PLUS_MAGIC_NUMBER, false);
            degradeMaxDurability(SECOND_UPGRADE_PLUS_DURABILITY_MAX);
        }
    }
}
