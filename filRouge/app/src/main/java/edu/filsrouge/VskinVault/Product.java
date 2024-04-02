package edu.filsrouge.VskinVault;

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
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public String getChapter() {
        return chapter;
    }

    public String getSeason() {
        return season;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getIcon() {
        return icon;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

}
