public class AddFollowerRequest {
    private Long visitedUserId;
    private Long visitorId;

    // Getters and setters
    public Long getVisitedUserId() {
        return visitedUserId;
    }

    public void setVisitedUserId(Long visitedUserId) {
        this.visitedUserId = visitedUserId;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }
}
