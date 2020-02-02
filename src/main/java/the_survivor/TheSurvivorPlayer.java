package the_survivor;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import the_survivor.cards.Strike_Survivor;
import the_survivor.patches.TheSurvivorEnum;

import java.util.ArrayList;

import static the_survivor.Constants.Player.*;

public class TheSurvivorPlayer extends CustomPlayer {

    public TheSurvivorPlayer() {
        super(NAME, TheSurvivorEnum.THE_SURVIVOR, ORB_TEXTURES, ORB_VFX_PATH, LAYER_SPEEDS, null, null);
        initializeClass(
                THE_SURVIVOR_MAIN,
                THE_SURVIVOR_SHOULDER_2,
                THE_SURVIVOR_SHOULDER_1,
                THE_SURVIVOR_CORPSE,
                getLoadout(),
                20.0F, -10.0F, 220.0F, 290.0F, //TODO
                new EnergyManager(ENERGY_PER_TURN)
        );
    }

    @Override
    public String getPortraitImageName() {
        return PORTRAIT_NAME;
    }

    public ArrayList<String> getStartingDeck() { //TODO
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);
        retVal.add(Strike_Survivor.ID);

        return retVal;
    }

    public ArrayList<String> getStartingRelics() { //TODO
        ArrayList<String> retVal = new ArrayList<>();
//        retVal.add("Uniform");
//        UnlockTracker.markRelicAsSeen("Uniform");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo(NAME, FLAVOR_TEXT,
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CARD_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return Constants.Cards.BASE_COLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_Survivor(); //TODO
    }

    @Override
    public Color getCardTrailColor() {
        return Constants.Cards.BASE_COLOR;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return ENERGY_NUM_FONT;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA(CHARACTER_SELECT_SOUND, MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return CHARACTER_SELECT_SOUND;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAME;
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheSurvivorPlayer();
    }

    @Override
    public String getSpireHeartText() {
        return HEART_TEXT;
    }

    @Override
    public Color getSlashAttackColor() {
        return Constants.Cards.BASE_COLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        };
    }

    @Override
    public String getVampireText() {
        return VAMPIRE_TEXT;
    }
}
