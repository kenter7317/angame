package per.kenter7317.save

import com.github.houbb.git4j.core.JGitImpl
import org.eclipse.jgit.api.Git
import org.yaml.snakeyaml.Yaml

class SaveDataManager(private val connectedRepository: JGitImpl) {

    lateinit var git : Git

    fun createSaveDataRepository() {
        try {
             git = connectedRepository.init("./saveData")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun createSaveData() {
        Yaml()
    }

    fun loadSaveData(): SaveData {
        return SaveData()
    }

    fun saveSaveData(saveData: SaveData) {

    }

    fun deleteSaveData() {

    }

    fun closeSaveDataRepository() {

    }

}