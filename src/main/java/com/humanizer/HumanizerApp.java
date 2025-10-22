package com.humanizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Point d'entrée principal de l'application Text Humanizer.
 * Permet l'exécution en ligne de commande et la démonstration fonctionnelle.
 */
public class HumanizerApp {
    private static final Logger logger = LoggerFactory.getLogger(HumanizerApp.class);

    public static void main(String[] args) {
        logger.info("Démarrage de Text Humanizer v1.0.0");
        
        demonstrationSimple();
    }

    private static void demonstrationSimple() {
        String texteOriginal = "Je dois vous informer que nous avons procédé à une " +
            "analyse approfondie de votre situation. Suite à notre examen minutieux, " +
            "il s'avère que l'ensemble des critères nécessaires ont été satisfaits. " +
            "Par conséquent, nous sommes en mesure de confirmer que votre demande " +
            "a été traitée conformément aux dispositions légales applicables.";

        System.out.println("=== HUMANIZER DEMO ===\n");
        System.out.println("AVANT:");
        System.out.println(texteOriginal);
        System.out.println("\n" + "=".repeat(80) + "\n");

        try {
            TextHumanizer humanizer = new TextHumanizer();
            HumanizationResult result = humanizer.humanize(texteOriginal);
            
            System.out.println("APRÈS:");
            System.out.println(result.getHumanizedText());
            System.out.println("\n" + "=".repeat(80));
            System.out.println("\nRAPPORT:");
            System.out.println("Score d'humanisation: " + result.getHumanizationScore() + "%");
            System.out.println("Modifications appliquées: " + result.getModifications());
            System.out.println("Règles utilisées: " + result.getAppliedRules());
            
            logger.info("Humanisation complétée avec succès");
        } catch (Exception e) {
            logger.error("Erreur lors de l'humanisation", e);
            System.err.println("Erreur: " + e.getMessage());
            System.exit(1);
        }
    }
}
