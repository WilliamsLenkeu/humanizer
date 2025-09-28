package com.humanizer.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Règle pour ajouter des contractions naturelles au texte.
 */
public class ContractionsRule implements HumanizerRule {
    private static final Logger logger = LoggerFactory.getLogger(ContractionsRule.class);
    
    private static final String[][] CONTRACTIONS = {
        {"ne pas", "pas"},
        {"ne sais pas", "sais pas"},
        {"à le", "au"},
        {"à les", "aux"},
        {"de le", "du"},
        {"de les", "des"},
        {"il est", "c'est"},
        {"elle est", "c'est"},
        {"ce est", "c'est"},
        {"je vais", "je vois"},
        {"avoir besoin", "avoir besoin"}
    };
    
    private int modificationCount = 0;

    @Override
    public String apply(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        modificationCount = 0;
        String result = text;

        // Applique les contractions
        for (String[] pair : CONTRACTIONS) {
            String from = pair[0];
            String to = pair[1];
            
            String pattern = "(?i)\\b" + java.util.regex.Pattern.quote(from) + "\\b";
            String before = result;
            result = result.replaceAll(pattern, to);
            
            if (!result.equals(before)) {
                modificationCount++;
            }
        }

        logger.debug("ContractionsRule appliquée, {} modifications", modificationCount);
        return result;
    }

    @Override
    public String getName() {
        return "ContractionsRule";
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
