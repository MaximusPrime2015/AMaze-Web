package model.Answer;

/**
 *
 * @author Max
 */
public class GenerateAnswer implements ServerAnswer {

    private String name;
    private String maze;
    private MazePosition start;
    private MazePosition end;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaze() {
        return this.maze;
    }

    public void setMaze(String maze) {
        this.maze = maze;
    }

    public MazePosition getStart() {
        return this.start;
    }

    public void setStart(MazePosition start) {
        this.start = start;
    }

    public MazePosition getEnd() {
        return this.end;
    }

    public void setEnd(MazePosition end) {
        this.end = end;
    }
}
