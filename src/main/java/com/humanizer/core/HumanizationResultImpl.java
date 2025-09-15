package com.humanizer.core;

import com.humanizer.HumanizationResult;
import java.util.*;

/**
 * Classe résultat contenant les informations d'humanisation.
 * Encapsule le texte transformé et les métadonnées.
 */
public class HumanizationResultImpl implements HumanizationResult {
    private final String originalText;
    private final String humanizedText;
    private final int humanizationScore;
    private final int modifications;
    private final List<String> appliedRules;
    private final Map<String, Object> metadata;

    public HumanizationResultImpl(String originalText, String humanizedText, 
                            int score, int mods, List<String> rules) {
        this.originalText = originalText;
        this.humanizedText = humanizedText;
        this.humanizationScore = score;
        this.modifications = mods;
        this.appliedRules = new ArrayList<>(rules);
        this.metadata = new HashMap<>();
    }

    @Override
    public String getOriginalText() {
        return originalText;
    }

    @Override
    public String getHumanizedText() {
        return humanizedText;
    }

    @Override
    public int getHumanizationScore() {
        return humanizationScore;
    }

    @Override
    public int getModifications() {
        return modifications;
    }

    @Override
    public List<String> getAppliedRules() {
        return Collections.unmodifiableList(appliedRules);
    }

    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    @Override
    public String toString() {
        return "HumanizationResult{" +
                "score=" + humanizationScore +
                ", modifications=" + modifications +
                ", appliedRules=" + appliedRules +
                '}';
    }
}
