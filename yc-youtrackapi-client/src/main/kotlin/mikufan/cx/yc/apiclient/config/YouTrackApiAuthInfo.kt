package mikufan.cx.yc.apiclient.config

import java.net.URI

/**
 * @author CX无敌
 * 2022-12-07
 */
data class YouTrackApiAuthInfo(
  val baseURI: URI,
  val bearerToken: String,
)
