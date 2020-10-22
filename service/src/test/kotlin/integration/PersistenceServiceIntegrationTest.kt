package integration

import application.SudokuServiceApplication
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import service.PersistenceService

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [SudokuServiceApplication::class])
class PersistenceServiceIntegrationTest {

    @Autowired
    private lateinit var persistenceService: PersistenceService

    @Test
    fun ragaca() {

    }
}