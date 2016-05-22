package model.Answer;

/**
 *
 * @author Max
 */
public class PlayAnswer implements ServerAnswer{
    private String gameName;
    private String move;


    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    
    public String getMove() {
        return this.move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    /// <summary>
    /// Returns a <see cref="System.String" /> that represents this instance.
    /// </summary>
    /// <returns>
    /// A <see cref="System.String" /> that represents this instance.
    /// </returns>
    @Override
    public String toString() {
        return String.format("in that game: {0}, the other player"
                + " has made the [{1}] move", this.gameName, this.move);
    }
}
