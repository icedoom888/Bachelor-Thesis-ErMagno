package it.polimi.ingsw.GC_29.GUI;

import java.util.HashMap;

/**
 * Created by AlbertoPennino on 21/06/2017.
 */
public class ClientSocketView {
    private HashMap<String,String> cardMap;
    //TODO: HashMap Leaders
    //TODO: HashMap BonusTiles

    public ClientSocketView(){
        HashMap<String,String> cardMap = new HashMap<>();
        cardMap.put("Avamposto Commerciale","@lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_1.png");
        cardMap.put("Bosco","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_2.png");
        cardMap.put("Borgo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_3.png");
        cardMap.put("Cave di Ghiaia","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_4.png");
        cardMap.put("Foresta","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_5.png");
        cardMap.put("Monastero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_6.png");
        cardMap.put("Rocca","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_7.png");
        cardMap.put("Città","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_8.png");
        cardMap.put("Miniera d'Oro","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_9.png");
        cardMap.put("Villaggio Montano","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_10.png");
        cardMap.put("Villaggio Minerario","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_11.png");
        cardMap.put("Cava di Pietra","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_12.png");
        cardMap.put("Possedimento","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_13.png");
        cardMap.put("Eremo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_14.png");
        cardMap.put("Maniero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_15.png");
        cardMap.put("Ducato","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_16.png");
        cardMap.put("Città Mercantile","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_17.png");
        cardMap.put("Tenuta","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_18.png");
        cardMap.put("Colonia","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_19.png");
        cardMap.put("Cava di Marmo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_20.png");
        cardMap.put("Provincia","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_21.png");
        cardMap.put("Santuario","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_22.png");
        cardMap.put("Castello","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_23.png");
        cardMap.put("Città Fortificata","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_24.png");

        cardMap.put("Zecca","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_25.png");
        cardMap.put("Esattoria","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_26.png");
        cardMap.put("Arco di Trionfo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_27.png");
        cardMap.put("Teatro","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_28.png");
        cardMap.put("Falegnameria","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_29.png");
        cardMap.put("Tagliapietre","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_30.png");
        cardMap.put("Cappella","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_31.png");
        cardMap.put("Residenza","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_32.png");
        cardMap.put("Mercato","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_33.png");
        cardMap.put("Tesoreria","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_34.png");
        cardMap.put("Gilda dei Pittori","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_35.png");
        cardMap.put("Gilda degli Scultori","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_36.png");
        cardMap.put("Gilda dei Costruttori","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_37.png");
        cardMap.put("Battistero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_38.png");
        cardMap.put("Caserma","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_39.png");
        cardMap.put("Fortezza","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_40.png");
        cardMap.put("Banca","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_41.png");
        cardMap.put("Fiera","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_42.png");
        cardMap.put("Giardino","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_43.png");
        cardMap.put("Castelletto","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_44.png");
        cardMap.put("Palazzo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_45.png");
        cardMap.put("Basilica","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_46.png");
        cardMap.put("Accademia Militare","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_47.png");
        cardMap.put("Cattedrale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_48.png");

        cardMap.put("Condottiero","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_49.png");
        cardMap.put("Costruttore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_50.png");
        cardMap.put("Dama","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_51.png");
        cardMap.put("Cavaliere","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_52.png");
        cardMap.put("Contadino","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_53.png");
        cardMap.put("Artigiano","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_54.png");
        cardMap.put("Predicatore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_55.png");
        cardMap.put("Badessa","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_56.png");
        cardMap.put("Capitano","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_57.png");
        cardMap.put("Architetto","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_58.png");
        cardMap.put("Mecenate","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_59.png");
        cardMap.put("Eroe","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_60.png");
        cardMap.put("Fattore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_61.png");
        cardMap.put("Studioso","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_62.png");
        cardMap.put("Messo Papale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_63.png");
        cardMap.put("Messo Reale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_64.png");
        cardMap.put("Nobile","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_65.png");
        cardMap.put("Governatore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_66.png");
        cardMap.put("Cortigiana","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_67.png");
        cardMap.put("Araldo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_68.png");
        cardMap.put("Cardinale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_69.png");
        cardMap.put("Vescovo","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_70.png");
        cardMap.put("Generale","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_71.png");
        cardMap.put("Ambasciatore","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_72.png");

        cardMap.put("Hiring Recruits","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_73.png");
        cardMap.put("Repairing the Church","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_74.png");
        cardMap.put("Building the Walls","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_75.png");
        cardMap.put("Raising a Statue","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_76.png");
        cardMap.put("Military Campaign","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_77.png");
        cardMap.put("Hosting Panhandlers","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_78.png");
        cardMap.put("Fighting Heresies","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_79.png");
        cardMap.put("Support to the Bishop","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_80.png");
        cardMap.put("Hiring Soldiers","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_81.png");
        cardMap.put("Repairing the Abbey","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_82.png");
        cardMap.put("Building the Bastions","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_83.png");
        cardMap.put("Support to the King","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_84.png");
        cardMap.put("Improving the Canals","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_85.png");
        cardMap.put("Hosting Foreigners","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_86.png");
        cardMap.put("Crusade","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_87.png");
        cardMap.put("Support to the Cardinal","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_88.png");
        cardMap.put("Hiring Mercenaries","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_89.png");
        cardMap.put("Repairing the Cathedral","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_90.png");
        cardMap.put("Building the Towers","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_91.png");
        cardMap.put("Promoting Sacred Art","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_92.png");
        cardMap.put("Military Conquest","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_93.png");
        cardMap.put("Improving the Roads","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_94.png");
        cardMap.put("Sacred War","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_95.png");
        cardMap.put("Support to the Pope","lorenzo_materiale_grafico_compr/LorenzoCards_compressed_png/devcards_f_en_c_96.png");

    }
    public static void sendInput(String input){
        System.out.println(input);
    }

    public HashMap<String, String> getCardMap() {
        return cardMap;
    }
}
