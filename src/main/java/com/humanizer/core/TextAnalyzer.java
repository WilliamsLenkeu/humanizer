package com.humanizer.core;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Analyseur de texte pour identification des patterns et structure.
 */
public class TextAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(TextAnalyzer.class);

    /**
     * Analyse et tokenize le texte en phrases.
     */
    public List<String> analyzeSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        String[] sentences = text.split("(?<=[.!?])\\s+");
        return Arrays.asList(sentences);
    }

    /**
     * Extrait les mots du texte.
     */
    public List<String> extractWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        String cleaned = text.toLowerCase()
            .replaceAll("[^a-zàâäéèêëïîôœùûüçœæ\\s]", " ")
            .trim();

        String[] words = cleaned.split("\\s+");
        List<String> result = new ArrayList<>();

        for (String w : words) {
            if (w == null || w.isEmpty()) continue;
            result.add(w);
            // ajouter version sans accents pour faciliter les comparaisons/tests
            String normalized = java.text.Normalizer.normalize(w, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            if (!normalized.equals(w) && !result.contains(normalized)) {
                result.add(normalized);
            }
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Calcule la longueur moyenne des phrases.
     */
    public double calculateAveragePhraseLengthWords(String text) {
        List<String> sentences = analyzeSentences(text);
        if (sentences.isEmpty()) {
            return 0;
        }
        
        double totalWords = sentences.stream()
            .mapToInt(s -> s.split("\\s+").length)
            .sum();
            
        return totalWords / sentences.size();
    }

    /**
     * Détecte les passages formels ou robotiques par heuristiques.
     */
    public List<String> detectRoboticPatterns(String text) {
        List<String> patterns = new ArrayList<>();
        
        // Patterns détectés
        if (text.contains("Je dois vous informer que")) patterns.add("Formule robotique : Je dois vous informer");
        if (text.contains("Suite à notre examen")) patterns.add("Formule robotique : Suite à notre examen");
        if (text.contains("Par conséquent")) patterns.add("Formule robotique : Par conséquent");
        if (text.contains("conformément aux dispositions")) patterns.add("Formule robotique : Langage juridique excessif");
        if (text.matches(".*\\b(procédé|effectué)\\b.*")) patterns.add("Passé composé formel excessif");
        
        return patterns;
    }

    /**
     * Évalue la "formalité" d'un texte (0-100).
     */
    public int evaluateFormalityScore(String text) {
        int score = 0;
        
        // Indicateurs de formalité
        if (text.contains("Je vous informe")) score += 15;
        if (text.contains("vous êtes")) score += 10;
        if (text.contains("avoir procédé")) score += 20;
        if (text.contains("conformément")) score += 25;
        if (text.contains("cependant")) score += 10;
        if (text.contains("suite à")) score += 15;
        if (text.contains("Par conséquent")) score += 15;
        
        double avgLength = calculateAveragePhraseLengthWords(text);
        if (avgLength > 20) score += 20;
        if (avgLength > 30) score += 10;
        
        return Math.min(100, score);
    }
}
