package tobiasjohansson.wigellpadel.models;

public class checkAvailability {

    private String status;
    private boolean available;

    public checkAvailability(String status, boolean available) {
        this.status = status;
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
