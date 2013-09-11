package tetris.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tetris.domain.battle.BattleId;
import tetris.domain.game.event.TetrisLineCompleted;

@Service
public class ApplicationEvents {

    @Autowired
    private PlayingBattleService playingBattleService;

    public void dispatchTetrisEvent(TetrisLineCompleted event) {
        BattleId battleId = playingBattleService.getBattle(event.getTetrisId());
        if (battleId != null) {
            playingBattleService.addPenaltyLine(battleId, event.getTetrisId(), event.getLineCount());
        }
    }

}
