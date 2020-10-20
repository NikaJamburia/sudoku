package saveload.json

import gameplay.game.GameState
import gameplay.saveload.GameLoader
import gameplay.saveload.SavedSudokuGame
import gameplay.saveload.deserialization.JacksonDeserializedSudokuGame
import gameplay.saveload.serialization.SerializationFormat
import gameplay.saveload.serialization.SerializationFormat.*
import org.springframework.stereotype.Component

@Component
class JsonGameLoader: GameLoader {
    override fun supportedFormat(): SerializationFormat = JSON

    override fun load(savedSudokuGame: SavedSudokuGame): GameState =
        JacksonDeserializedSudokuGame(savedSudokuGame.savedContent).state()

}