package mikufan.cx.yc.core.ical.model

import java.time.Duration

/**
 * @author CX无敌
 * 2023-01-02
 */
data class AlarmSetting(
  /**
   * the field name of the period field to be used to calculate the relative alarm time,
   * can be `null`
   */
  val durationFieldName: String?,
  /**
   * This is only needed for [durationFieldName], as in YouTrack you can't specify negative duration
   */
  val shiftBasedOn: ShiftBasedOn = ShiftBasedOn.BEFORE_START,
  /**
   * This can be either a positive or negative duration.
   *
   * This field also makes sure an alarm is creatable.
   */
  val defaultShiftDuration: Duration,
)

enum class ShiftBasedOn(val isBefore: Boolean, val isBasedOnStart: Boolean) {
  BEFORE_START(true, true),
  AFTER_START(false, true),
  BEFORE_END(true, false),
  AFTER_END(false, false);
}
