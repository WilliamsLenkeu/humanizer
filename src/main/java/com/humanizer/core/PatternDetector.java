package com.humanizer.core;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Détecteur de patterns robotiques dans le texte.
 * Identifie les éléments typiques des textes générés par IA.
 */
public class PatternDetector {
    private static final Logger logger = LoggerFactory.getLogger(PatternDetector.class);

    private static final Map<String, String> ROBOTIC_PATTERNS = Map.ofEntries(
        Map.entry("Je suis", "Je"),
        Map.entry("Je dois vous informer que", "Je dois te dire que"),
        Map.entry("Je dois vous", "Je dois te"),
        Map.entry("Suite à notre examen", "Après vérification"),
        Map.entry("il s'avère que", "il se trouve que"),
        Map.entry("l'ensemble des", "tous les"),
        Map.entry("Par conséquent,", "Donc,"),
        Map.entry("Cependant", "Mais"),
        Map.entry("conformément aux", "selon les"),
        Map.entry("dispositions légales", "règles légales"),
        Map.entry("avoir procédé à", "avoir"),
        Map.entry("nous sommes en mesure de", "nous pouvons"),
        Map.entry("suite à examen", "après examen"),
        Map.entry("s'avère", "s'est avéré"),
        Map.entry("procédé", "effectué"),
        Map.entry("effectué", "fait")
    );

    /**
     * Détecte les patterns robotiques dans le texte.
     * @return liste des patterns détectés
     */
    public List<DetectedPattern> detect(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }

        List<DetectedPattern> patterns = new ArrayList<>();

        for (Map.Entry<String, String> entry : ROBOTIC_PATTERNS.entrySet()) {
            String pattern = entry.getKey();
            int count = countOccurrences(text, pattern);
            if (count > 0) {
                patterns.add(new DetectedPattern(pattern, entry.getValue(), count));
                logger.debug("Pattern détecté: {} (x{})", pattern, count);
            }
        }

        // Détection de listes énumératives
        if (hasExcessiveEnumeration(text)) {
            patterns.add(new DetectedPattern("Énumération excessive", "Combiner en paragraphes", 1));
        }

        // Détection de phrases trop longues
        int longSentenceCount = countLongSentences(text);
        if (longSentenceCount > 0) {
            patterns.add(new DetectedPattern("Phrases longues", "Diviser en phrases courtes", longSentenceCount));
        }

        return patterns;
    }

    /**
     * Compte les occurrences d'une sous-chaîne (case-insensitive).
     */
    private int countOccurrences(String text, String pattern) {
        String lower = text.toLowerCase();
        String lowerPattern = pattern.toLowerCase();
        int count = 0;
        int index = 0;
        
        while ((index = lower.indexOf(lowerPattern, index)) != -1) {
            count++;
            index += lowerPattern.length();
        }
        
        return count;
    }

    /**
     * Détecte les énumérations excessives (listes à puces, tirets).
     */
    private boolean hasExcessiveEnumeration(String text) {
        String[] lines = text.split("\n");
        int enumeratedLines = 0;
        
        for (String line : lines) {
            if (line.matches("^\\s*[-*•].*") || line.matches("^\\s*\\d+\\..*")) {
                enumeratedLines++;
            }
        }
        
        return enumeratedLines > 4;
    }

    /**
     * Compte les phrases dont la longueur dépasse 15 mots.
     */
    private int countLongSentences(String text) {
        String[] sentences = text.split("(?<=[.!?])\\s+");
        int longCount = 0;
        
        for (String sentence : sentences) {
            int wordCount = sentence.trim().split("\\s+").length;
            if (wordCount > 15) {
                longCount++;
            }
        }
        
        return longCount;
    }

    /**
     * Classe interne pour représenter un pattern détecté.
     */
    public static class DetectedPattern {
        public final String pattern;
        public final String suggestion;
        public final int occurrences;

        public DetectedPattern(String pattern, String suggestion, int occurrences) {
            this.pattern = pattern;
            this.suggestion = suggestion;
            this.occurrences = occurrences;
        }

        @Override
        public String toString() {
            return String.format("%s (x%d) → %s", pattern, occurrences, suggestion);
        }
    }
}
