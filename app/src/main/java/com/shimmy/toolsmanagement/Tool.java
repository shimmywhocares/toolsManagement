package com.shimmy.toolsmanagement;

public class Tool {
    private  id;
    private String name;
    private String manufacturer;
    private String dimensions;
    private String status;
    private String holder;
    private float hoursOfUsage ;
    private String imageUrl;
    private String shortDesc;
    private boolean isExpanded;



    public Tool(long id, String name, String manufacturer, String dimensions, String status, String holder, float hoursOfUsage,
                String imageUrl,
                String shortDesc) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.dimensions = dimensions;
        this.status = status;
        this.holder = holder;
        this.hoursOfUsage = hoursOfUsage;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
        isExpanded = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public float getHoursOfUsage() {
        return hoursOfUsage;
    }

    public void setHoursOfUsage(float hoursOfUsage) {
        this.hoursOfUsage = hoursOfUsage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", status='" + status + '\'' +
                ", holder='" + holder + '\'' +
                ", hoursOfUsage=" + hoursOfUsage +
                ", imageUrl='" + imageUrl + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                '}';
    }
}
