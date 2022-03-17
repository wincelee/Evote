package manu.apps.evote.classes;

public class Contestant {

    private int contestantId;
    private String contestantName;
    private String contestType;
    private int contestantPhoto;
    private String partyName;
    private int partyPhoto;

    private int voteCount;

    public Contestant() {

    }

    public Contestant(int contestantId, String contestantName, String contestType, int contestantPhoto, String partyName, int partyPhoto) {
        this.contestantId = contestantId;
        this.contestantName = contestantName;
        this.contestType = contestType;
        this.contestantPhoto = contestantPhoto;
        this.partyName = partyName;
        this.partyPhoto = partyPhoto;
    }

    public int getContestantId() {
        return contestantId;
    }

    public void setContestantId(int contestantId) {
        this.contestantId = contestantId;
    }

    public String getContestantName() {
        return contestantName;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    public int getContestantPhoto() {
        return contestantPhoto;
    }

    public void setContestantPhoto(int contestantPhoto) {
        this.contestantPhoto = contestantPhoto;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public int getPartyPhoto() {
        return partyPhoto;
    }

    public void setPartyPhoto(int partyPhoto) {
        this.partyPhoto = partyPhoto;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
