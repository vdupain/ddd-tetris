package tetris.infrastucture.persistence.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import tetris.domain.game.Game;
import tetris.domain.game.GameRepository;
import tetris.domain.game.TetrisId;

public class InMemoryGameRepository implements GameRepository {
    private Map<TetrisId, Game> gameDb = new HashMap<TetrisId, Game>();

    @Override
    public void store(Game game) {
        gameDb.put(game.getTetrisId(), game);
    }

    @Override
    public Game find(TetrisId tetrisId) {
        return gameDb.get(tetrisId);
    }

    public TetrisId nextTetrisId() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new TetrisId(random.substring(0, random.indexOf("-")));
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<Game>(gameDb.values());
    }

    @Override
    public List<Game> findStartedGames() {
        List<Game> startedGames = new ArrayList<Game>();
        for (Game game : gameDb.values()) {
            if (game.isStarted()) {
                startedGames.add(game);
            }
        }
        return startedGames;
    }
}
