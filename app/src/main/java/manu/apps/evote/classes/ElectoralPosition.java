package manu.apps.evote.classes;

public class ElectoralPosition {

    private String positionName;
    private String bundleName;
    private int positionImage;

    public ElectoralPosition() {

    }

    public ElectoralPosition(String positionName, String bundleName, int positionImage) {
        this.positionName = positionName;
        this.bundleName = bundleName;
        this.positionImage = positionImage;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public int getPositionImage() {
        return positionImage;
    }

    public void setPositionImage(int positionImage) {
        this.positionImage = positionImage;
    }
}
