# Guide de Procédures

## Installation et Configuration

### Prérequis
- Java 17+ (OpenJDK ou Oracle JDK)
- Maven 3.8.0+

### Installation
```bash
cd /home/williams/Documents/dev_/humanizer
mvn clean install
```

### Build
```bash
mvn clean package
```

### Exécution
```bash
# Avec fichier JAR
java -jar target/text-humanizer-1.0.0-jar-with-dependencies.jar

# Depuis Maven
mvn exec:java -Dexec.mainClass="com.humanizer.HumanizerApp"
```

## Tests

### Exécuter tous les tests
```bash
mvn test
```

### Exécuter avec couverture
```bash
mvn verify
```

### Mode de développement (watch)
```bash
mvn -t watch
```

## Utilisation

### Humaniser un texte simple
```java
TextHumanizer humanizer = new TextHumanizer();
String original = "Je suis un texte généré par une intelligence artificielle.";
HumanizationResult result = humanizer.humanize(original);
System.out.println(result.getHumanizedText());
```

### Avec configuration personnalisée
```java
HumanizationConfig config = new HumanizerBuilder()
    .enableContractionsRule()
    .enableAlternativeWordsRule()
    .setVerbosity(VerbosityLevel.DETAILED)
    .build();

TextHumanizer humanizer = new TextHumanizer(config);
HumanizationResult result = humanizer.humanize(monTexte);
```

## Architecture Interne

### Flux Principal
1. **TextAnalyzer**: Analyse et tokenize le texte
2. **PatternDetector**: Identifie les patterns robotiques
3. **RulesEngine**: Applique les règles d'humanisation dans l'ordre
4. **TextHumanizer**: Orchestre l'ensemble + génère un rapport

### Ajouter une Nouvelle Règle
1. Créer une classe implémentant `HumanizerRule` dans `com.humanizer.rules`
2. Implémenter `apply(String text): String`
3. Implémenter `getName(): String`
4. Ajouter à la configuration du `TextHumanizer`
5. Écrire des tests dans `HumanizerRuleTest`

Exemple:
```java
public class VerbosityReductionRule implements HumanizerRule {
    @Override
    public String apply(String text) {
        // Implémentation
        return humanizedText;
    }
    
    @Override
    public String getName() {
        return "VerbosityReduction";
    }
}
```

## Déploiement

### Créer une release
```bash
mvn -B deploy
# (à configurer dans pom.xml pour repository)
```

### Intégration Continue (CI/CD)
- GitHub Actions / GitLab CI recommandés
- Commands: `mvn verify` pour validation
- Couverture minimale: 80%

## Troubleshooting

### Build échoue
```bash
mvn clean
mvn compile
```

### Tests échouent
```bash
mvn test -X
```

### Dépendances outdated
```bash
mvn versions:display-dependency-updates
```

## Maintenance

- Mettre à jour `project_context.md` après chaque sprint
- Tenir à jour `decisions.md` pour les décisions techniques
- Documenter les changements dans les commits avec clear messages
- Respecter le semantic versioning lors des releases
