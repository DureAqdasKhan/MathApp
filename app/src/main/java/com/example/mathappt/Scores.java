package com.example.mathappt;

public class Scores {

    public String id;
    public String type;
    public String score;
    public Scores()
    {

    }
    public Scores(String id, String type, String score)
    {
        this.type = type;
        this.id = id;
        this.score = score;
    }
    void setId(String id)
    {
        this.id=id;
    }
}
