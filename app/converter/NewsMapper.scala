package converter

import java.time.{Instant, LocalDateTime}
import java.util.TimeZone

import play.api.libs.json.{Format, JsValue, Json, Reads}
import play.api.libs.ws.WSResponse

class NewsMapper {

  def convertToDate(createdTime: Long): LocalDateTime = {
    LocalDateTime.ofInstant(Instant.ofEpochMilli(createdTime),
      TimeZone.getDefault.toZoneId)
  }

  implicit val newsDtoFormat: Format[NewsDto] = Json.format[NewsDto]
  implicit val listNewsDto: Reads[Seq[NewsDto]] = Reads.seq[NewsDto]

  def toRequests(news: WSResponse): Seq[NewsResource] = {
    news.body[JsValue].as[Seq[NewsDto]].map(dto => NewsResource(dto.id, dto.text, dto.source, dto.likes,
      dto.comments, dto.link, convertToDate(dto.createdTime)))
  }

}

case class NewsDto(id: Long, text: String, createdTime: Long, userId: Long, link: String, source: String, likes: Int,
                   comments: Int)
case class NewsResource(id: Long, description: String, source: String, likeCount: Int, commentCount: Int, link: String,
                        created: LocalDateTime)
