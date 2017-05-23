package it.polimi.ingsw.GC_29.ProveGSon;

import com.google.gson.Gson;
import it.polimi.ingsw.GC_29.Components.*;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.Effect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.ObtainEffect;
import it.polimi.ingsw.GC_29.EffectBonusAndActions.PayToObtainEffect;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lorenzotara on 22/05/17.
 */
public class MainGSonToFile {

    public static void main(String[] args) throws IOException {


        // Ospitare i mendicanti

        ArrayList<Effect> immediateEffectsOIM = new ArrayList<Effect>();
        immediateEffectsOIM.add(new PayToObtainEffect(new GoodSet(1,1,1,1,1,1,1), new GoodSet(1,1,1,1,1,1,1)));
        immediateEffectsOIM.add(new CouncilPrivilegeEffect(3));


        ArrayList<Effect> permanentEffectsOIM = new ArrayList<Effect>();
        //permanentEffectsOIM.add(new ObtainEffect(new GoodSet(5,1,1,1,1,1,1)));


        DevelopmentCard ospitareIMendicanti = new DevelopmentCard(
                "Ospitare i Mendicanti",
                "descrizione",
                Era.FIRSTERA,
                new CardCost(false, true, new GoodSet(4,0,0,0,0,0,0), new GoodSet(), false, new GoodSet()),
                CardColor.PURPLE,
                immediateEffectsOIM,
                permanentEffectsOIM,
                false,
                0);

        // toJson

        FileWriter fileWriter = new FileWriter("C:\\Users\\Christian\\Desktop\\cartaProva");

        Gson gson = new Gson();


        gson.toJson(ospitareIMendicanti, fileWriter);

        fileWriter.close();
    }


}
