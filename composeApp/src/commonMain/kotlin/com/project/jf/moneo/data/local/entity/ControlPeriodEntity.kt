import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.jf.moneo.domain.model.ControlPeriod

@Entity(tableName = "control_periods")
data class ControlPeriodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val startDate: Long,
    val endDate: Long? = null
)

fun ControlPeriodEntity.toDomain(): ControlPeriod {
    return ControlPeriod(
        id = id,
        name = name,
        startDate = startDate,
        endDate = endDate
    )
}

fun ControlPeriod.toData(): ControlPeriodEntity {
    return ControlPeriodEntity(
        id = id,
        name = name,
        startDate = startDate,
        endDate = endDate
    )
}