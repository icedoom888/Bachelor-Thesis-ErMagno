package it.polimi.ingsw.GC_29.Components;

import it.polimi.ingsw.GC_29.Player.*;
import it.polimi.ingsw.GC_29.Player.PlayerStatus;

/**
 * Created by Christian on 18/05/2017.
 */
public class PayToObtainEffect extends ObtainEffect{

    private GoodSet cost;

    public PayToObtainEffect(GoodSet cost, GoodSet goodsObtained) {
        super(goodsObtained); // va chiamato poichè non esiste costruttore di defaut classe padre
        this.cost = cost;
    }

    @Override
    public void execute(PlayerStatus status) {
        // TODO qui inserisci il controllo chiamando il metodo check, se positivo chiami super.execute(status)
    }

    private boolean checkSufficientGoods(PlayerStatus status){
        // implementazione controllo risorse o punti sufficienti per ottenere il risultato dell'effetto
        return true;
    }
}
