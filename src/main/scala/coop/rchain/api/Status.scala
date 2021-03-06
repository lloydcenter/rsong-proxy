package coop.rchain.api

import buildInfo.BuildInfo
import cats.effect._
import io.circe.Json
import org.http4s.HttpRoutes
import org.http4s.circe._
import io.circe.parser._
import org.http4s.dsl.Http4sDsl


class Status[F[_]: Sync] extends Http4sDsl[F] {

  val info = BuildInfo
  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root =>
      val p = parse(BuildInfo.toJson).getOrElse(Json.obj("status"-> Json.fromString("up")))
      Ok(p)
    case GET -> Root / "status" =>
      Ok(Json.obj("status"-> Json.fromString("up")))
  }
}
