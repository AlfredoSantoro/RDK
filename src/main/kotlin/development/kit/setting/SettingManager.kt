package development.kit.setting

import java.time.temporal.ChronoUnit

object SettingManager
{
    fun createSetting(name: String, value: Int, desc: String, unit: ChronoUnit): Setting
    {
        return Setting(name, value, desc, unit)
    }

    fun updateSetting(value: Int, desc: String, unit: ChronoUnit, settingToUpdate: Setting): Setting
    {
        settingToUpdate.value = value
        settingToUpdate.description = desc
        settingToUpdate.representationUnit = unit
        return settingToUpdate
    }
}