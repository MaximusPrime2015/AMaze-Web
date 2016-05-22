package model.Answer;

/**
 *
 * @author Max
 */
public class MultiplayerAnswer implements ServerAnswer{
    private String gameName;
    private String mazeName;
    private GenerateAnswer myMaze;
    private GenerateAnswer othersMaze;

    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    
    public String getMazeName() {
        return this.mazeName;
    }

    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }
    
    public GenerateAnswer getYou() {
        return this.myMaze;
    }

    public void setYou(GenerateAnswer maze) {
        this.myMaze = maze;
    }
    
    public GenerateAnswer getOther() {
        return this.othersMaze;
    }

    public void setOther(GenerateAnswer maze) {
        this.othersMaze = maze;
    }

    /// <summary>
    /// Returns a <see cref="System.String" /> that represents this instance.
    /// </summary>
    /// <returns>
    /// A <see cref="System.String" /> that represents this instance.
    /// </returns>
    @Override
    public String toString() {
        return String.format("Multiplayer Game, game-name: {0}, maze name: {1}\n"
                + "Your maze: {2}\nOther player's maze: {3}", this.gameName,
                this.mazeName, this.myMaze, this.othersMaze);
    }
}
