package es.uniovi.webservices.addService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Products")
public class Product {
    @Id
    private String id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private boolean manual;
    private int stock;
    private int favorites;

    public String getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}

	public boolean isManual() {
		return manual;
	}

	public int getStock() {
		return stock;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }
}
