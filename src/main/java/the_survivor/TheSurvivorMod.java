package the_survivor;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.CardStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import the_survivor.cards.Common.Salvage;
import the_survivor.cards.basic.Sword;
import the_survivor.cards.basic.Defend_Topaz;
import the_survivor.cards.basic.Strike_Topaz;
import the_survivor.cards.special.Scrap;
import the_survivor.patches.AbstractCardEnum;
import the_survivor.patches.TheSurvivorEnum;

@SpireInitializer
public class TheSurvivorMod implements EditCharactersSubscriber, EditCardsSubscriber, EditStringsSubscriber{

    public static final Logger logger = LogManager.getLogger(TheSurvivorMod.class.getName());

    public TheSurvivorMod() {
        BaseMod.subscribe(this);
        addColor();
    }

    public static void initialize() {
        new TheSurvivorMod();
    }

    private static void addColor(){
        logger.info("Adding Topaz color");
        BaseMod.addColor(AbstractCardEnum.TOPAZ,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.BASE_COLOR,
                Constants.Cards.ATTACK_BG_PATH_SMALL, Constants.Cards.SKILL_BG_PATH_SMALL,
                Constants.Cards.POWER_BG_PATH_SMALL, Constants.Cards.ORB_PATH_SMALL,
                Constants.Cards.ATTACK_BG_PATH_LARGE, Constants.Cards.SKILL_BG_PATH_LARGE,
                Constants.Cards.POWER_BG_PATH_LARGE, Constants.Cards.ORB_PATH_LARGE);
        logger.info("Finished adding Topaz color");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Adding The Survivor character");

        BaseMod.addCharacter(new TheSurvivorPlayer(), Constants.Player.BUTTON_PATH, Constants.Player.PORTRAIT_PATH, TheSurvivorEnum.THE_SURVIVOR);
        logger.info("Finished adding The Survivor character");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding cards");
        BaseMod.addCard(new Strike_Topaz());
        BaseMod.addCard(new Defend_Topaz());

        BaseMod.addCard(new Salvage());
        BaseMod.addCard(new Sword());

        //Special
        BaseMod.addCard(new Scrap());

        logger.info("Finished adding cards");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Editing strings");
        BaseMod.loadCustomStringsFile(CardStrings.class, "theSurvivor/localization/TheSurvivor-CardStrings-eng.json");
        logger.info("Finished editing strings");
    }
}
