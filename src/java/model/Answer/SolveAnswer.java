package model.Answer;

/**
 *
 * @author Max
 */
public class SolveAnswer implements ServerAnswer{

    private String name;
    private String solution;
    private MazePosition start;
    private MazePosition end;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public MazePosition getStart() {
        return this.start;
    }

    public void setStart(MazePosition start) {
        this.start = start;
    }
    
    public MazePosition getEnd(){
        return this.end;
    }

    public void setEnd(MazePosition end) {
        this.end = end;
    }

    /*
    @Override
    public String toString() {
        int rows = 0, cols = 0;
        int.TryParse(AppSettings.Settings["rows"]
        , out rows);
            int.TryParse(AppSettings.Settings["cols"]
        , out cols
        );
            string solDisplay = new MazeDrawer().getMazeToStr(this.sol,
                13, true, rows, cols);

        return string.Format("Solution for Maze: {0}\n"
                + "solution is: {1}\n"
                + "start: {2}\nend: {3}",
                this.name, solDisplay, this.start, this.end);
    }*/
}
