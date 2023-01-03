package mikufan.cx.yc.cliapp.component

import com.fasterxml.jackson.databind.node.ObjectNode
import mikufan.cx.yc.apiclient.yt.api.issues.IssuesApi
import mikufan.cx.yc.cliapp.config.DateTimeConfig
import mikufan.cx.yc.cliapp.config.SearchConfig
import mikufan.cx.yc.core.ical.util.YouTrackDefaultDateTime
import org.springframework.stereotype.Component

/**
 * @author CX无敌
 * 2023-01-01
 */
@Component
class IssuesGetter(
  private val issuesApi: IssuesApi,
  private val searchConfig: SearchConfig,
  private val dateTimeConfig: DateTimeConfig,
) {

  fun issuesIterator(): Iterator<ObjectNode> {
    val (youtrackFieldNames, customFieldNames) = getFields(dateTimeConfig)
    return issuesApi.getIssuesLazily(
      query = searchConfig.query,
      fields = listOf(
        "id",
        "idReadable",
        "summary",
        "description",
        "customFields(name,id,value(name,id))"
      ) + youtrackFieldNames,
      customFields = customFieldNames,
      pageSize = searchConfig.pageSize,
    )
  }

  internal fun getFields(dateTimeConfig: DateTimeConfig): Pair<List<String>, List<String>> {
    val youtrackFieldNames = mutableListOf<String>()
    val customFieldNames = mutableListOf<String>()
    dateTimeConfig.fieldNames.forEach { name ->
      if (YouTrackDefaultDateTime.isYouTrackDefaultDateTimeField(name)) {
        youtrackFieldNames.add(name)
      } else {
        customFieldNames.add(name)
      }
    }

    return youtrackFieldNames to customFieldNames
  }
}
