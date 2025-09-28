package com.humanizer.rules;

import java.util.regex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Règle pour restructurer les phrases longues en phrases plus courtes.
 */
public class SentenceStructureRule implements HumanizerRule {
    private static final Logger logger = LoggerFactory.getLogger(SentenceStructureRule.class);
    private static final int OPTIMAL_SENTENCE_LENGTH = 15; // Nombre de mots maximum
    
    private int modificationCount = 0;

    @Override
    public String apply(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        modificationCount = 0;
        String[] sentences = text.split("(?<=[.!?])\\s+");
        StringBuilder result = new StringBuilder();

        for (String sentence : sentences) {
            String processed = processSentence(sentence.trim());
            if (!processed.equals(sentence.trim())) {
                modificationCount++;
            }
            result.append(processed).append(" ");
        }

        logger.debug("SentenceStructureRule appliquée, {} modifications", modificationCount);
        return result.toString().trim();
    }

    private String processSentence(String sentence) {
        // Compte les mots
        String[] words = sentence.split("\\s+");

        // Toujours remplacer certaines conjonctions pour améliorer la lisibilité
        String processed = sentence
            .replaceAll("(?i),\\s+et\\s+", ". Et ")
            .replaceAll("(?i),\\s+mais\\s+", ". Mais ")
            .replaceAll("(?i),\\s+or\\s+", ". Or ")
            .replaceAll("(?i),\\s+donc\\s+", ". Donc ")
            .replaceAll("(?i),\\s+car\\s+", ". Car ")
            .replaceAll("(?i);\\s+", ". ");

        // Si la phrase est courte, ne pas ajouter de point supplémentaire si présent
        processed = processed.trim();
        if (processed.endsWith(".") || processed.endsWith("?") || processed.endsWith("!")) {
            return processed;
        }

        // Pour les phrases longues, s'assurer qu'on termine par un point
        if (words.length > OPTIMAL_SENTENCE_LENGTH) {
            return processed + ".";
        }

        return processed;
    }

    @Override
    public String getName() {
        return "SentenceStructureRule";
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
