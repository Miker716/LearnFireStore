package com.example.learnfirestore;

public class Names
{
    private String first;
    private String last;
    private int born;
    private String id;

    public Names(String first, String last, int born)
    {
        this.first = first;
        this.last = last;
        this.born = born;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Names()
    {
    }

    public String getFirst()
    {
        return first;
    }

    public void setFirst(String first)
    {
        this.first = first;
    }

    public String getLast()
    {
        return last;
    }

    public void setLast(String last)
    {
        this.last = last;
    }

    public int getBorn()
    {
        return born;
    }

    public void setBorn(int born)
    {
        this.born = born;
    }
}
