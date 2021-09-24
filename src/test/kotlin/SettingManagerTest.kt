import development.kit.setting.SettingManager
import org.junit.Assert
import org.junit.Test
import java.time.temporal.ChronoUnit

class SettingManagerTest
{
    @Test
    fun `Should create a setting`()
    {
        val setting = SettingManager.createSetting("testSetting",1, "reservation_duration", ChronoUnit.HOURS)
        Assert.assertNotNull(setting)
    }

    @Test
    fun `Should update a setting`()
    {
        val setting = SettingManager.createSetting("testSetting",1, "reservation_duration", ChronoUnit.HOURS)
        Assert.assertNotNull(setting)
        val newSetting = SettingManager.updateSetting(2, setting.description, setting.representationUnit, setting)
        Assert.assertNotNull(newSetting)
        Assert.assertEquals(newSetting.value, 2)
        Assert.assertEquals(newSetting.description, setting.description)
        Assert.assertEquals(newSetting.representationUnit, setting.representationUnit)
    }
}