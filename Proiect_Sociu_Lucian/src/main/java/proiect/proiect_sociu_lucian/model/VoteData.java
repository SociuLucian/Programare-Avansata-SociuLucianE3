package proiect.proiect_sociu_lucian.model;

import java.util.List;

public class VoteData {
    List<String> candidates;
    String timeLimit;
    int maxUsers;
    String timestamp;
    String voteCode;
    String topic;


    public VoteData(List<String> candidates, String timeLimit, int maxUsers, String timestamp, String topic) {
        this.candidates = candidates;
        this.timeLimit = timeLimit;
        this.maxUsers= maxUsers;
        this.timestamp = timestamp;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVoteCode() {
        return voteCode;
    }

    public void setVoteCode(String voteCode) {
        this.voteCode = voteCode;
    }
}
