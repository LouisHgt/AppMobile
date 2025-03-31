package com.filrouge.iut.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
    private String id;
    private String name;
    private String description;
    private String imageResource;
    private int level;
    private boolean isUnlocked;
    private boolean isCustom;

    public Card(String id, String name, String description, String imageResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.level = 0;
        this.isUnlocked = false;
        this.isCustom = false;
    }

    protected Card(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        imageResource = in.readString();
        level = in.readInt();
        isUnlocked = in.readByte() != 0;
        isCustom = in.readByte() != 0;
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageResource() { return imageResource; }
    public void setImageResource(String imageResource) { this.imageResource = imageResource; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public boolean isUnlocked() { return isUnlocked; }
    public void setUnlocked(boolean unlocked) { isUnlocked = unlocked; }

    public boolean isCustom() { return isCustom; }
    public void setCustom(boolean custom) { isCustom = custom; }

    public void levelUp() {
        if (level < 5) {
            level++;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageResource);
        dest.writeInt(level);
        dest.writeByte((byte) (isUnlocked ? 1 : 0));
        dest.writeByte((byte) (isCustom ? 1 : 0));
    }
}