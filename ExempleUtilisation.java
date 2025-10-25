// ExempleUtilisation.java
// Exemples d'utilisation du Text Humanizer

import com.humanizer.TextHumanizer;
import com.humanizer.HumanizationResult;
import com.humanizer.rules.*;
import java.util.*;

public class ExempleUtilisation {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║  EXEMPLES D'UTILISATION - TEXT HUMANIZER  ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        // Exemple 1: Utilisation Simple
        exempleSimple();

        System.out.println("\n" + "═".repeat(50) + "\n");

        // Exemple 2: Analyse sans Humanisation
        exempleAnalyse();

        System.out.println("\n" + "═".repeat(50) + "\n");

        // Exemple 3: Règles Personnalisées
        exempleReglesPersonnalisees();
    }

    private static void exempleSimple() {
        System.out.println("📌 EXEMPLE 1: Humanisation Simple\n");

        String texteOriginal = "Je dois vous informer que votre demande a été " +
                              "acceptée. Par conséquent, nous sommes en mesure " +
                              "de procéder au traitement immédiatement.";

        System.out.println("Texte original:");
        System.out.println(texteOriginal + "\n");

        // Créer le humanizer avec règles par défaut
        TextHumanizer humanizer = new TextHumanizer();
        HumanizationResult result = humanizer.humanize(texteOriginal);

        System.out.println("Texte humanisé:");
        System.out.println(result.getHumanizedText() + "\n");

        System.out.println("Résumé:");
        System.out.println("  Score: " + result.getHumanizationScore() + "%");
        System.out.println("  Modifications: " + result.getModifications());
        System.out.println("  Règles appliquées: " + result.getAppliedRules());
    }

    private static void exempleAnalyse() {
        System.out.println("📌 EXEMPLE 2: Analyse de Texte\n");

        String texte = "Suite à notre examen minutieux, il s'avère que " +
                      "l'ensemble des critères nécessaires ont été satisfaits. " +
                      "Cependant, conformément aux dispositions légales, " +
                      "nous devons procéder à des vérifications supplémentaires.";

        System.out.println("Texte à analyser:");
        System.out.println(texte + "\n");

        TextHumanizer humanizer = new TextHumanizer();
        var analysis = humanizer.analyze(texte);

        System.out.println("Résultats de l'analyse:");
        System.out.println("  Nombre de phrases: " + analysis.get("sentenceCount"));
        System.out.println("  Mots/phrase en moyenne: " + 
                          String.format("%.2f", analysis.get("averageWordPerSentence")));
        System.out.println("  Score de formalité: " + analysis.get("formalityScore") + "/100");
        System.out.println("  Patterns robotiques détectés: " + 
                          analysis.get("patternCount"));
    }

    private static void exempleReglesPersonnalisees() {
        System.out.println("📌 EXEMPLE 3: Humaniseur avec Règles Personnalisées\n");

        // Créer un humanizer avec des règles spécifiques
        List<com.humanizer.rules.HumanizerRule> reglesPersonnalisees = 
            new ArrayList<>();
        reglesPersonnalisees.add(new AlternativeWordsRule());
        // Ajouter d'autres règles au besoin

        String texte = "L'ensemble des données a été procédé à l'examen " +
                      "par conséquent la décision a été communiquée.";

        System.out.println("Texte original:");
        System.out.println(texte + "\n");

        TextHumanizer humanizer = new TextHumanizer(reglesPersonnalisees);
        HumanizationResult result = humanizer.humanize(texte);

        System.out.println("Texte humanisé:");
        System.out.println(result.getHumanizedText() + "\n");

        System.out.println("Détails de l'humanisation:");
        System.out.println("  Score: " + result.getHumanizationScore() + "%");
        System.out.println("  Modifications: " + result.getModifications());
    }
}
