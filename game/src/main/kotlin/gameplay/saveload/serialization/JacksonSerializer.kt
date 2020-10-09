package gameplay.saveload.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import gameplay.game.GameState

class JacksonSerializer: SudokuSerializer {
    override fun gameToString(gameState: GameState): String {
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(gameState)
    }
}