package algorithms.Stack;

public class Event {
    private int id;
    private boolean isStart;
    private int time;

    public Event(String content) {
        String[] strs = content.split(":");
        this.id = Integer.valueOf(strs[0]);
        this.isStart = strs[1].equals("start");
        this.time = Integer.valueOf(strs[2]);
    }

    public int getId() {
        return this.id;
    }

    public boolean getIsStart() {
        return this.isStart;
    }

    public int getTime() {
        return this.time;
    }
}