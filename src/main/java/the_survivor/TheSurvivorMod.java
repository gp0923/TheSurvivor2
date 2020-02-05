package the_survivor;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import the_survivor.cards.common.Salvage;
import the_survivor.cards.common.Sword;
import the_survivor.cards.basic.Defend_Topaz;
import the_survivor.cards.basic.Pickaxe;
import the_survivor.cards.basic.Strike_Topaz;
import the_survivor.cards.rare.SwordR;
import the_survivor.cards.special.Scrap;
import the_survivor.cards.uncommon.SwordU;
import the_survivor.patches.AbstractCardEnum;
import the_survivor.patches.TheSurvivorEnum;
import the_survivor.variables.DurabilityVariable;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class TheSurvivorMod implements EditCharactersSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber {

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
        BaseMod.addCard(new Pickaxe());

        BaseMod.addCard(new Salvage());
        BaseMod.addCard(new Sword());

        BaseMod.addCard(new SwordU());

        BaseMod.addCard(new SwordR());

        //Special
        BaseMod.addCard(new Scrap());
        logger.info("Finished adding cards");

        logger.info("Adding variables");
        BaseMod.addDynamicVariable(new DurabilityVariable());
        logger.info("Finished adding variables");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Editing strings");
        BaseMod.loadCustomStringsFile(CardStrings.class, "theSurvivor/localization/eng/cardStrings.json");
        logger.info("Finished editing strings");
    }

    @Override
    public void receiveEditKeywords() {
        String path = "theSurvivor/localization/eng/keywords.json";

        Gson gson = new Gson();
        String json = Gdx.files.internal(path).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
