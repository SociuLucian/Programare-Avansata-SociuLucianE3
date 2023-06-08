package proiect.proiect_sociu_lucian.model;

import java.util.Objects;

public class VoteView {
    private String topic;
    private String winner;
    private int nrVoturi;
    private int nrTotalVoturi;

    public VoteView(String topic,String winner, int nrTotalVoturi, int nrVoturi)
    {
        this.nrTotalVoturi = nrTotalVoturi;
        this.nrVoturi=nrVoturi;
        this.topic = topic;
        this.winner = winner;
    }

    public int getNrTotalVoturi() {
        return nrTotalVoturi;
    }

    public void setNrTotalVoturi(int nrTotalVoturi) {
        this.nrTotalVoturi = nrTotalVoturi;
    }

    public int getNrVoturi() {
        return nrVoturi;
    }

    public void setNrVoturi(int nrVoturi) {
        this.nrVoturi = nrVoturi;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteView)) return false;
        VoteView voteView = (VoteView) o;
        return nrVoturi == voteView.nrVoturi && nrTotalVoturi == voteView.nrTotalVoturi && Objects.equals(topic, voteView.topic) && Objects.equals(winner, voteView.winner);
    }

    @Override
    public String toString() {
        return "VoteView{" +
                "topic='" + topic + '\'' +
                ", Winner='" + winner + '\'' +
                ", nrVoturi=" + nrVoturi +
                ", nrTotalVoturi=" + nrTotalVoturi +
                '}';
    }
}
