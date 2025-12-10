import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "control_periods")
data class ControlPeriodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val startDate: Long,
    val endDate: Long? = null
)