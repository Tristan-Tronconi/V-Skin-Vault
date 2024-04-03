package edu.filsrouge.VskinVault;

import java.util.Random;

/**
 * La classe Product représente un produit dans l'application VskinVault.
 * Chaque produit a un id, un nom, un type, une rareté, un chapitre,
 * une saison, une description, une image, une icône, une petite icône,
 * un prix et une note.
 */
public class Product {
    private String id;
    private String name;
    private String type;
    private String rarity;
    private String chapter;
    private String season;
    private String description;
    private String image;
    private String icon;
    private String smallIcon;

    private String price;

    private String rating;

    /**
     * Constructeur pour la classe Product.
     * @param id L'identifiant du produit.
     * @param name Le nom du produit.
     * @param type Le type du produit.
     * @param rarity La rareté du produit.
     * @param chapter Le chapitre de sortie du produit.
     * @param season La saison de sortie du produit.
     * @param description La description du produit.
     * @param image L'image du produit.
     * @param icon L'icône du produit.
     * @param smallIcon La petite icône du produit.
     */
    public Product(String id, String name, String type, String rarity, String chapter, String season, String description, String image, String icon, String smallIcon) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.chapter = chapter;
        this.season = season;
        this.description = description;
        this.image = image;
        this.icon = icon;
        this.smallIcon = smallIcon;
        this.price = calcPrice();
        this.rating = calcRating();
    }

    /**
     * Calcule le prix du produit.
     * @return Le prix du produit.
     */
    private String calcPrice() {
        switch (rarity) {
            case "Common":
                return "1";
            case "Uncommon":
                return "3";
            case "Rare":
                return "5";
            case "Epic":
                return "10";
            case "Legendary":
                return "25";
            default:
                return "0";
        }
    }

    /**
     * Calcule la note du produit.
     * @return La note du produit.
     */
    private String calcRating() {
        Random random = new Random();
        return String.valueOf(random.nextFloat()*2+3);
    }

    // Les getters et setters pour chaque champ de la classe Product

    /**
     * Récupère l'identifiant du produit.
     * @return L'identifiant du produit.
     */
    public String getId() {
        return id;
    }

    /**
     * Récupère le nom du produit.
     * @return Le nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère le type du produit.
     * @return Le type du produit.
     */
    public String getType() {
        return type;
    }

    /**
     * Récupère la rareté du produit.
     * @return La rareté du produit.
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Récupère le chapitre de sortie du produit.
     * @return Le chapitre de sortie du produit.
     */
    public String getChapter() {
        return chapter;
    }

    /**
     * Récupère la saison de sortie du produit.
     * @return La saison de sortie du produit.
     */
    public String getSeason() {
        return season;
    }

    /**
     * Récupère la description du produit.
     * @return La description du produit.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Récupère l'image du produit.
     * @return L'image du produit.
     */
    public String getImage() {
        return image;
    }

    /**
     * Récupère l'icône du produit.
     * @return L'icône du produit.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Récupère la petite icône du produit.
     * @return La petite icône du produit.
     */
    public String getSmallIcon() {
        return smallIcon;
    }

    /**
     * Récupère le prix du produit.
     * @return Le prix du produit.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Récupère la note du produit.
     * @return La note du produit.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Définit l'identifiant du produit.
     * @param id L'identifiant du produit.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Définit le nom du produit.
     * @param name Le nom du produit.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit le type du produit.
     * @param type Le type du produit.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Définit la rareté du produit.
     * @param rarity La rareté du produit.
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * Définit le chapitre de sortie du produit.
     * @param chapter Le chapitre de sortie du produit.
     */
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    /**
     * Définit la saison de sortie du produit.
     * @param season La saison de sortie du produit.
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Définit la description du produit.
     * @param description La description du produit.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Définit l'image du produit.
     * @param image L'image du produit.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Définit l'icône du produit.
     * @param icon L'icône du produit.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Définit la petite icône du produit.
     * @param smallIcon La petite icône du produit.
     */
    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    /**
     * Définit le prix du produit.
     * @param price Le prix du produit.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Définit la note du produit.
     * @param rating La note du produit.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }
}