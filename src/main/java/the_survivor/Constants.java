package the_survivor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import the_survivor.patches.AbstractCardEnum;

public final class Constants {
    public static final class Mod{
        public static String ID = "TheSurvivor";
        public static String CARD_PREFIX = "TS";
    }


    public static final class Player {
        public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
        public static final String THE_SURVIVOR_MAIN = "theSurvivor/img/char/main.png";
        public static final String THE_SURVIVOR_SHOULDER_2 = "theSurvivor/img/char/shoulder2.png"; // campfire pose
        public static final String THE_SURVIVOR_SHOULDER_1 = "theSurvivor/img/char/shoulder.png"; // another campfire pose
        public static final String THE_SURVIVOR_CORPSE = "theSurvivor/img/char/corpse.png"; // dead corpse

        public static final String NAME = "The Survivor";
        public static final String FLAVOR_TEXT = "A rugged outcast, alienated from society. Has persevered through the most extreme conditions. NL Can he survive in the spire?";
        public static final String HEART_TEXT = "NL You muster one final burst of energy...";
        public static final String VAMPIRE_TEXT = "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us~ ~outcast,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
        public static final int STARTING_HP = 75;
        public static final int MAX_HP = 75;
        public static final int STARTING_GOLD = 99;
        public static final int HAND_SIZE = 5;
        public static final int ORB_SLOTS = 0;
        public static final int ASCENSION_MAX_HP_LOSS = 5;
        public static final AbstractCard.CardColor CARD_COLOR = AbstractCardEnum.TOPAZ;
        public static final String CHARACTER_SELECT_SOUND = "ATTACK_HEAVY";
        public static final BitmapFont ENERGY_NUM_FONT = FontHelper.energyNumFontBlue;

        public static final String[] ORB_TEXTURES = {
                "theSurvivor/img/orb/layer1.png",
                "theSurvivor/img/orb/layer2.png",
                "theSurvivor/img/orb/layer3.png",
                "theSurvivor/img/orb/layer4.png",
                "theSurvivor/img/orb/layer5.png",
                "theSurvivor/img/orb/layer6.png",
                "theSurvivor/img/orb/layer1d.png",
                "theSurvivor/img/orb/layer2d.png",
                "theSurvivor/img/orb/layer3d.png",
                "theSurvivor/img/orb/layer4d.png",
                "theSurvivor/img/orb/layer5d.png",
        };

        public static final String ORB_VFX_PATH = "theSurvivor/img/orb/vfx.png";

        public static final float[] LAYER_SPEEDS = {
                80.0F, 40.0F, -40.0F, 20.0F, 0.0F,
                16.0F, 8.0F, -8.0F, 5.0F, 0.0F,
        };

        public static final String PORTRAIT_NAME ="survivorPortraitBG.jpg";
        public static final String PORTRAIT_PATH ="theSurvivor/img/char/survivorPortraitBG.jpg";
        public static final String BUTTON_PATH ="theSurvivor/img/char/button.png";
    }

    public static final class Cards{
        public static final Color BASE_COLOR = CardHelper.getColor(131.0f, 156.0f, 165.0f);

        public static final String ATTACK_BG_PATH_SMALL ="theSurvivor/img/512/bg_attack_topaz.png";
        public static final String ATTACK_BG_PATH_LARGE ="theSurvivor/img/1024/bg_attack_topaz.png";

        public static final String SKILL_BG_PATH_SMALL ="theSurvivor/img/512/bg_skill_topaz.png";
        public static final String SKILL_BG_PATH_LARGE ="theSurvivor/img/1024/bg_skill_topaz.png";

        public static final String POWER_BG_PATH_SMALL ="theSurvivor/img/512/bg_power_topaz.png";
        public static final String POWER_BG_PATH_LARGE ="theSurvivor/img/1024/bg_power_topaz.png";

        public static final String ORB_PATH_SMALL ="theSurvivor/img/512/card_topaz_orb.png";
        public static final String ORB_PATH_LARGE ="theSurvivor/img/1024/card_topaz_orb.png";

        public static final String DEFAULT_ATTACK_PATH ="theSurvivor/img/cards/strike_topaz.png";
        public static final String DEFAULT_SKILL_PATH ="theSurvivor/img/cards/defend_topaz.png";


    }

}
