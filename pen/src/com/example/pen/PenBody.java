package com.example.pen;

import java.util.Objects;

public final class PenBody {
    private final String brand;
    private final String model;
    private final String material;

    public PenBody(String brand, String model, String material) {
        this.brand = Objects.requireNonNull(brand, "brand");
        this.model = Objects.requireNonNull(model, "model");
        this.material = Objects.requireNonNull(material, "material");
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getMaterial() {
        return material;
    }
}
