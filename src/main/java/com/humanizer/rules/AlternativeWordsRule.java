package com.humanizer.rules;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Règle pour remplacer le vocabulaire formel par des alternatives plus naturelles.
 */
public class AlternativeWordsRule implements HumanizerRule {
    private static final Logger logger = LoggerFactory.getLogger(AlternativeWordsRule.class);

    private static final Map<String, String> FORMAL_TO_NATURAL = Map.ofEntries(
        // Remplacements courants
        Map.entry("Je dois vous informer que", "Je veux te dire que"),
        Map.entry("l'ensemble des", "tous les"),
        Map.entry("Je dois vous", "Je dois te"),
        Map.entry("vous", "tu"),
        Map.entry("suite à notre examen", "après vérification"),
        Map.entry("il s'avère que", "il se trouve que"),
        Map.entry("l'ensemble", "tout"),
        Map.entry("Par conséquent", "Donc"),
        Map.entry("Cependant", "Mais"),
        Map.entry("conformément aux", "selon les"),
        Map.entry("nous sommes en mesure de confirmer", "nous pouvons confirmer"),
        Map.entry("nous sommes en mesure de", "nous pouvons"),
        Map.entry("dispositions légales applicables", "règles en vigueur"),
        Map.entry("avoir procédé à une analyse", "avoir analysé"),
        Map.entry("avoir procédé à", "avoir"),
        Map.entry("effectuer", "faire"),
        Map.entry("s'avère", "s'avère"),
        Map.entry("requière", "nécessite"),
        Map.entry("demeure", "reste"),
        Map.entry("antérieurement", "avant"),
        Map.entry("ultérieurement", "plus tard"),
        Map.entry("considérant", "parce que"),
        Map.entry("ledit", "ce"),
        Map.entry("desdits", "de ces"),
        Map.entry("auprès", "près de"),
        Map.entry("moyennant", "par"),
        Map.entry("nonobstant", "malgré"),
        Map.entry("toutefois", "cependant"),
        Map.entry("à l'attention de", "pour"),
        Map.entry("en sus", "en plus"),
        Map.entry("à titre gratuit", "gratuitement"),
        Map.entry("prendre connaissance", "connaître"),
        Map.entry("mettre en œuvre", "utiliser"),
        Map.entry("opération", "action"),
        Map.entry("traitement", "processus"),
        Map.entry("votre situation personnelle", "votre situation")
    );

    private int modificationCount = 0;

    @Override
    public String apply(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        String result = text;
        modificationCount = 0;

        // Appliquer les remplacements en commençant par les patterns les plus longs
        List<Map.Entry<String, String>> entries = new ArrayList<>(FORMAL_TO_NATURAL.entrySet());
        entries.sort((a, b) -> Integer.compare(b.getKey().length(), a.getKey().length()));

        for (Map.Entry<String, String> entry : entries) {
            String formal = entry.getKey();
            String natural = entry.getValue();

            // Remplacement case-insensitive avec préservation de la casse
            String pattern = "(?i)" + java.util.regex.Pattern.quote(formal);
            String beforeCount = result;
            result = result.replaceAll(pattern, natural);

            if (!result.equals(beforeCount)) {
                modificationCount++;
            }
        }

        logger.debug("AlternativeWordsRule appliquée, {} modifications", modificationCount);
        return result;
    }

    @Override
    public String getName() {
        return "AlternativeWordsRule";
    }

    @Override
    public int getModificationCount() {
        return modificationCount;
    }

    @Override
    public void resetModificationCount() {
        modificationCount = 0;
    }
}
