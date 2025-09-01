# Guide Rapide d'Utilisation

## 🚀 Installation Rapide

```bash
# Cloner ou naviguer vers le projet
cd /home/williams/Documents/dev_/humanizer

# Compiler
mvn clean install

# Exécuter la démo
java -jar target/text-humanizer-1.0.0-jar-with-dependencies.jar
```

## 💻 Utilisation Programmatique

### Cas Simple
```java
import com.humanizer.TextHumanizer;
import com.humanizer.HumanizationResult;

// Créer une instance
TextHumanizer humanizer = new TextHumanizer();

// Humaniser un texte
String original = "Je dois vous informer que par conséquent.";
HumanizationResult result = humanizer.humanize(original);

// Afficher les résultats
System.out.println("Score: " + result.getHumanizationScore());
System.out.println("Résultat: " + result.getHumanizedText());
```

### Analyser sans Humaniser
```java
// Analyser la structure et déterminer les problèmes
var analysis = humanizer.analyze(texte);
System.out.println("Formalité: " + analysis.get("formalityScore"));
System.out.println("Patterns: " + analysis.get("detectedPatterns"));
```

### Règles Personnalisées
```java
// Créer une liste de règles spécifiques
List<HumanizerRule> regles = new ArrayList<>();
regles.add(new AlternativeWordsRule());
// Ajouter D'autres règles...

// Utiliser le humanizer personnalisé
TextHumanizer humanizer = new TextHumanizer(regles);
HumanizationResult result = humanizer.humanize(texte);
```

### Ajouter des Règles Dynamiquement
```java
TextHumanizer humanizer = new TextHumanizer();

// Ajouter une règle personnalisée
humanizer.addRule(new VotreNouvelleRule());

// Humaniser
HumanizationResult result = humanizer.humanize(texte);
```

## 📝 Exemple Complet

```java
import com.humanizer.*;
import com.humanizer.rules.*;
import java.util.*;

public class MonApplication {
    public static void main(String[] args) {
        // Texte formel à humaniser
        String texte = "Je dois vous informer que suite à notre examen " +
                      "minutieux, il s'avère que l'ensemble des critères " +
                      "nécessaires ont été satisfaits. Par conséquent, nous " +
                      "sommes en mesure de confirmer que votre demande a été " +
                      "traitée conformément aux dispositions légales applicables.";

        System.out.println("AVANT:");
        System.out.println(texte);

        // Humaniser
        TextHumanizer humanizer = new TextHumanizer();
        HumanizationResult result = humanizer.humanize(texte);

        System.out.println("\nAPRÈS:");
        System.out.println(result.getHumanizedText());

        System.out.println("\nRÉSULTAT:");
        System.out.println("Score: " + result.getHumanizationScore() + "%");
        System.out.println("Modifications: " + result.getModifications());
        System.out.println("Règles: " + result.getAppliedRules());
    }
}
```

## 🔧 Créer une Règle Personnalisée

Consulter `CustomRuleTemplate.java` pour un modèle complet avec exemples.

Étapes rapides:
1. Créer une classe implémentant `HumanizerRule`
2. Implémenter `apply()` avec votre logique
3. Implémenter les autres méthodes requises
4. Ajouter au humanizer: `humanizer.addRule(new MaReglenew MaReglenew MaReglenew MaReglenew());`

## 📊 Interpréter les Résultats

### Score d'Humanisation (0-100)
- **0-20**: Texte peu formel, peu d'amélioration possible
- **20-40**: Texte formal, amélioration modérée
- **40-60**: Texte très formal, amélioration significative
- **60-80**: Texte extrêmement formal, grande amélioration
- **80+**: Texte robotique, amélioration majeure

### Modifications
Le nombre de transformations appliquées. Plus élevé = plus d'améliorations.

### Règles Appliquées
Liste des règles qui ont effectué des changements, avec le nombre de modifications par règle.

## ⚙️ Configuration Maven

Le projet utilise Maven pour la compilation:
```bash
mvn clean compile      # Compiler
mvn test              # Exécuter les tests
mvn package           # Créer le JAR
mvn verify            # Build complet avec tests
```

## 📚 Documentation Supplémentaire

- [README.md](README.md) - Vue d'ensemble complète
- [docs/mvp.md](docs/mvp.md) - Spécifications fonctionnelles
- [docs/decisions.md](docs/decisions.md) - Décisions techniques
- [docs/procedures.md](docs/procedures.md) - Guides détaillés

## 🐛 Troubleshooting

**Le JAR ne se lance pas**
```bash
# Vérifier que Java 17+ est installé
java -version

# Recompiler
mvn clean package -DskipTests
```

**Tests qui échouent**
```bash
mvn clean test -Dmaven.test.failure.ignore=true
```

**Modifier les dépendances**
Éditer `pom.xml` et relancer:
```bash
mvn clean install
```

## 📧 Support

Pour des questions ou problèmes, consulter la documentation ou le code source dans `src/main/java`.

---

**Prêt? Commencez maintenant!**
```bash
java -jar target/text-humanizer-1.0.0-jar-with-dependencies.jar
```
