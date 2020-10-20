package saveload.json

import gameplay.game.GameState
import gameplay.saveload.GameSaver
import gameplay.saveload.SavedSudokuGame
import gameplay.saveload.serialization.JacksonSerializedSudokuGame
import gameplay.saveload.serialization.SerializationFormat
import gameplay.saveload.serialization.SerializationFormat.*
import org.springframework.stereotype.Component

@Component
class JsonGameSaver: GameSaver {
    override fun supportedFormat(): SerializationFormat = JSON
    override fun save(gameState: GameState): SavedSudokuGame =
        SavedSudokuGame(JSON, JacksonSerializedSudokuGame(gameState).asString())
}