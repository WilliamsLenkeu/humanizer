# 📚 Text Humanizer

Un outil puissant et extensible pour **humaniser les textes générés par IA** et améliorer les documents officiels. Transforme le langage robotique et formel en texte naturel et lisible.

## 🎯 Objectif

Rendre les textes générés par des modèles d'IA plus naturels, engageants et faciles à lire tout en **préservant le sens et l'intégrité du contenu**.

## ✨ Fonctionnalités Principales

- ✅ **Détection de Patterns Robotiques** : Identifie automatiquement les formules formelles et les patterns typiques de l'IA
- ✅ **Remplacement de Vocabulaire** : Remplace les termes trop formels par des alternatives naturelles
- ✅ **Restructuration de Phrases** : Divise les phrases longues en phrases courtes et lisibles
- ✅ **Contractions Naturelles** : Ajoute des contractions pour un ton plus conversationnel
- ✅ **Extensible** : Architecture modulaire permettant l'ajout facile de nouvelles règles
- ✅ **Score d'Humanisation** : Génère un score quantifiant l'amélioration (0-100)
- ✅ **Rapport Détaillé** : Affiche les règles appliquées et le nombre de modifications

## 🚀 Démarrage Rapide

### Prérequis
- Java 17+
- Maven 3.8.0+

### Installation

```bash
cd /home/williams/Documents/dev_/humanizer
mvn clean install
```

### Utilisation en Ligne de Commande

```bash
mvn exec:java -Dexec.mainClass="com.humanizer.HumanizerApp"
```

### Utilisation Programmatique

```java
// Humanizer simple
TextHumanizer humanizer = new TextHumanizer();
String original = "Je dois vous informer que votre demande a été acceptée.";
HumanizationResult result = humanizer.humanize(original);

System.out.println("Avant: " + result.getOriginalText());
System.out.println("Après: " + result.getHumanizedText());
System.out.println("Score: " + result.getHumanizationScore() + "%");
```

## 📦 Architecture

```
humanizer/
├── core/
│   ├── TextAnalyzer.java           # Analyse structurelle du texte
│   ├── PatternDetector.java        # Détection des patterns robotiques
│   └── HumanizationResultImpl.java  # Classe résultat
├── rules/
│   ├── HumanizerRule.java          # Interface pour les règles
│   ├── AlternativeWordsRule.java   # Remplace le vocabulaire
│   ├── SentenceStructureRule.java  # Réorganise les phrases
│   └── ContractionsRule.java       # Ajoute les contractions
├── TextHumanizer.java              # Moteur d'orchestration
└── HumanizerApp.java               # Point d'entrée CLI
```

## 🔧 Règles Implémentées

### 1. **AlternativeWordsRule**
Remplace les termes formels par des alternatives naturelles:
- `Je dois vous informer que` → `Je veux te dire que`
- `Suite à notre examen` → `Après vérification`
- `Par conséquent` → `Donc`
- `Cependant` → `Mais`
- ... et 25+ autres remplacements

### 2. **SentenceStructureRule**
Réorganise les phrases longues (>20 mots) en phrases courtes:
- Divise sur les conjonctions (`et`, `mais`, `donc`)
- Améliore la lisibilité et la clarté
- Préserve la cohérence

### 3. **ContractionsRule**
Ajoute des contractions naturelles:
- `à le` → `au`
- `de les` → `des`
- `il est` → `c'est`

## 📊 Exemple d'Utilisation

### Avant
```
Je dois vous informer que nous avons procédé à une analyse approfondie de votre 
situation. Suite à notre examen minutieux, il s'avère que l'ensemble des critères 
nécessaires ont été satisfaits. Par conséquent, nous sommes en mesure de confirmer 
que votre demande a été traitée conformément aux dispositions légales applicables.
```

### Après
```
Je veux te dire que nous avons analysé votre situation en profondeur. Après vérification 
minutieuse, il se trouve que tous les critères nécessaires peuvent être satisfaits. 
Donc, nous pouvons confirmer que votre demande a été traitée selon les règles en vigueur.
```

**Rapport**: ✅ Score: 65% | Modifications: 8 | Règles: 3

## 🧪 Tests

Exécuter les tests:
```bash
mvn test
```

Avec couverture:
```bash
mvn verify
```

**Couverture actuelle**: 80%+ (objectif MVP)

## 🛠️ Ajouter une Nouvelle Règle

1. Créer une classe implémentant `HumanizerRule`
2. Implémenter les méthodes `apply()`, `getName()`, `getModificationCount()`
3. Ajouter à la configuration du `TextHumanizer`
4. Écrire les tests unitaires

Exemple:
```java
public class CustomRule implements HumanizerRule {
    @Override
    public String apply(String text) {
        // Votre implémentation
        return text;
    }

    @Override
    public String getName() {
        return "CustomRule";
    }

    @Override
    public int getModificationCount() {
        return 0;
    }

    @Override
    public void resetModificationCount() {
    }
}
```

## 📈 Analyse de Texte

Analyser sans humaniser:
```java
var analysis = humanizer.analyze("Votre texte ici");
System.out.println("Score de formalité: " + analysis.get("formalityScore"));
System.out.println("Patterns détectés: " + analysis.get("patternCount"));
```

## 🗂️ Documentation

- [MVP](docs/mvp.md) - Spécifications et objectifs
- [Décisions](docs/decisions.md) - Journal des décisions techniques
- [Procédures](docs/procedures.md) - Guide d'installation et utilisation

## 🔄 Versions

- **1.0.0** (Actuelle) - MVP avec règles de base

## 📝 Roadmap

- [ ] Phase 2: API REST + Interface Web
- [ ] Phase 3: Support du français avancé (accords, conjugaisons)
- [ ] Phase 4: Machine Learning pour détection améliorée
- [ ] Phase 5: Support multi-langue

## 🤝 Contribution

Les contributions sont bienvenues! Consultez la documentation pour les standards du projet.

## 📄 Licence

MIT License

## 👨‍💻 Auteur

Développé avec ❤️ en français

---

**Prêt à humaniser votre texte?** Commencez avec:
```bash
mvn exec:java -Dexec.mainClass="com.humanizer.HumanizerApp"
```
