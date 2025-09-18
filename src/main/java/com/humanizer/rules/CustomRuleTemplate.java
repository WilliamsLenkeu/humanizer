/**
 * Template pour créer une règle d'humanisation personnalisée
 * 
 * Utilisation:
 * 1. Copier ce fichier en CustomaRule.java
 * 2. Implémenter la logique apply()
 * 3. L'ajouter au TextHumanizer: humanizer.addRule(new CustomRule());
 */
package com.humanizer.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomRuleTemplate implements HumanizerRule {
    private static final Logger logger = LoggerFactory.getLogger(CustomRuleTemplate.class);
    
    private int modificationCount = 0;

    @Override
    public String apply(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        modificationCount = 0;
        String result = text;

        // TODO: Implémenter votre logique de transformation ici
        // Exemple simple:
        // result = result.replace("ancien", "nouveau");

        logger.debug("CustomRuleTemplate appliquée, {} modifications", modificationCount);
        return result;
    }

    @Override
    public String getName() {
        return "CustomRuleTemplate";
    }

    @Override
    public int getModificationCount() {
        return modificationCount;
    }

    @Override
    public void resetModificationCount() {
        modificationCount = 0;
    }

    // ============================================================
    // EXEMPLES D'IMPLÉMENTATION
    // ============================================================

    /**
     * Exemple 1: Règle simple de remplacement
     */
    public static class SimpleReplacementRule implements HumanizerRule {
        private int modificationCount = 0;

        @Override
        public String apply(String text) {
            modificationCount = 0;
            String result = text;
            
            // Remplacements simples
            if (result.contains("terme1")) {
                result = result.replace("terme1", "terme2");
                modificationCount++;
            }
            
            return result;
        }

        @Override
        public String getName() {
            return "SimpleReplacementRule";
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

    /**
     * Exemple 2: Règle avec regex
     */
    public static class RegexRule implements HumanizerRule {
        private int modificationCount = 0;

        @Override
        public String apply(String text) {
            modificationCount = 0;
            String before = text;
            
            // Exemple: remplacer les nombres répétés
            String result = text.replaceAll("(\\w)\\1{2,}", "$1");
            
            if (!result.equals(before)) {
                modificationCount++;
            }
            
            return result;
        }

        @Override
        public String getName() {
            return "RegexRule";
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

    /**
     * Exemple 3: Règle complexe avec analyse
     */
    public static class AnalysisRule implements HumanizerRule {
        private int modificationCount = 0;

        @Override
        public String apply(String text) {
            modificationCount = 0;
            
            // Analyser les phrases
            String[] phrases = text.split("[.!?]");
            StringBuilder result = new StringBuilder();
            
            for (String phrase : phrases) {
                String processed = processPhrase(phrase);
                result.append(processed).append(". ");
                
                if (!processed.equals(phrase)) {
                    modificationCount++;
                }
            }
            
            return result.toString().trim();
        }

        private String processPhrase(String phrase) {
            // TODO: Implémenter la logique de traitement
            return phrase;
        }

        @Override
        public String getName() {
            return "AnalysisRule";
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
}

/**
 * GUIDE D'IMPLÉMENTATION
 * =====================
 * 
 * 1. Comprendre l'interface HumanizerRule
 *    - apply(String text): Transforme le texte et retourne le résultat
 *    - getName(): Retourne le nom unique de la règle
 *    - getModificationCount(): Retourne le nombre de modifications
 *    - resetModificationCount(): Réinitialise le compteur
 * 
 * 2. Implémentation minimale:
 *    @Override
 *    public String apply(String text) {
 *        // Votre logique
 *        return transformedText;
 *    }
 * 
 * 3. Bonnes pratiques:
 *    - Gérer les null et textes vides
 *    - Logger les modifications importantes
 *    - Compter les changements appliqués
 *    - Préserver les patterns importants
 *    - Documenter avec des commentaires
 * 
 * 4. Tests:
 *    - Créer une classe *RuleTest
 *    - Tester les cas normaux et limites
 *    - Vérifier preservation du sens
 * 
 * 5. Ajouter au humanizer:
 *    TextHumanizer humanizer = new TextHumanizer();
 *    humanizer.addRule(new VotreNouvelleRule());
 */
