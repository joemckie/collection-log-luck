package com.peanubnutter.collectionlogluck.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.sql.Timestamp;

@Value
public class ObtainedCollectionItem {
    int id;
    @Nullable @NonFinal String name;
    int count;
    String date;

    public ObtainedCollectionItem(int id, int count) {
        this.id = id;
        this.count = count;
        this.date = null;
        this.name = null;
    }

    public ObtainedCollectionItem(int id, @Nonnull String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.date = null;
    }

    public ObtainedCollectionItem(int id, @Nonnull String name, int count, String date) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.date = date;
    }

    public Timestamp getDate()
    {
        if (this.date == null) {
            return null;
        }

        return Timestamp.valueOf(this.date);
    }
}