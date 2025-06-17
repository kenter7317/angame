package per.kenter7317.save

import com.github.houbb.git4j.core.JGitImpl
import org.eclipse.jgit.api.Git
import org.yaml.snakeyaml.Yaml
import java.io.File

class SaveDataManager(private val connectedRepository: JGitImpl) {

    lateinit var git: Git

    fun createSaveDataRepository() {
        try {
            git = connectedRepository.init("./saveData")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun createSaveData(slot: Int) {
        val file = File("./saveData/saveData$slot.yml")
        if (!file.exists()) {
            file.createNewFile()
        }
        saveSaveData(SaveData(file))
    }

    fun loadSaveData(): List<SaveData> {
        val list = mutableListOf<SaveData>()
        return list
    }

    private fun saveSaveData(saveData: SaveData) {
        Yaml().dump(saveData, saveData.file.writer())
    }

    fun deleteSaveData(slot : Int) {
        val file = File("./saveData/saveData$slot.yml")
        if (file.exists()) {
            file.delete()
        }
    }

    fun closeSaveDataRepository() {

    }

    companion object {
        fun currentSaveData(): SaveData {
            val saveData = SaveData(File("./saveData/saveDataCurrent.yml"))

            return saveData
        }
    }

}