package com.filrouge.iut.utils;

import com.filrouge.iut.models.Card;
import com.filrouge.iut.models.CardCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardManager {
    private static CardManager instance;
    private CardCollection collection;

    private CardManager() {
        this.collection = new CardCollection();
        initializeCards();
    }

    public static CardManager getInstance() {
        if (instance == null) {
            instance = new CardManager();
        }
        return instance;
    }

    public CardCollection getCollection() {
        return collection;
    }

    private void initializeCards() {
        // Ajouter quelques cartes prédéfinies pour le prototype
        collection.addCard(new Card("1", "Dragon Rouge", "Un dragon puissant et féroce", "card_dragon_red"));
        collection.addCard(new Card("2", "Elfe des Bois", "Un elfe agile et rusé", "card_elf_forest"));
        collection.addCard(new Card("3", "Golem de Pierre", "Une créature massive et résistante", "card_golem_stone"));
        collection.addCard(new Card("4", "Magicien Blanc", "Un maître des arcanes de la lumière", "card_wizard_white"));
        collection.addCard(new Card("5", "Nécromancien", "Un sorcier qui manipule les forces de la mort", "card_necromancer"));
        collection.addCard(new Card("6", "Assassin", "Un tueur discret et efficace", "card_assassin"));
        collection.addCard(new Card("7", "Chevalier d'Or", "Un guerrier noble et courageux", "card_knight_gold"));
        collection.addCard(new Card("8", "Sirène", "Une créature marine envoûtante", "card_mermaid"));
        collection.addCard(new Card("9", "Phénix", "Un oiseau de feu immortel", "card_phoenix"));
    }

    public List<Card> openPack() {
        List<Card> pack = new ArrayList<>();
        Random random = new Random();

        // Pour le prototype, on prend simplement 3 cartes aléatoires
        List<Card> allCards = collection.getCards();
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(allCards.size());
            Card selectedCard = allCards.get(index);

            // Si la carte est déjà débloquée, on l'améliore
            if (selectedCard.isUnlocked()) {
                selectedCard.levelUp();
            } else {
                selectedCard.setUnlocked(true);
            }

            pack.add(selectedCard);
        }

        return pack;
    }

    public void addCustomCard(Card card) {
        card.setCustom(true);
        card.setUnlocked(true);
        collection.addCard(card);
    }
}