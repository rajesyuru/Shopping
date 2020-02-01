package com.example.shopping;

public class Shopping {
    boolean done;
    String desc;

    public Shopping(boolean done, String desc) {
        this.done = done;
        this.desc = desc;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
