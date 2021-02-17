package example.com.ichnaea.rest

import example.com.ichnaea.core.services.ShowService
import example.com.ichnaea.models.UserRating
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class IchnaeaRest(
        private val showService: ShowService
) : Runnable {

    override fun run() {
        app.start(3000)
    }

    private val app = Javalin
        .create()
        .apply {
            // Unexpected exception: return HTTP 500
            exception(Exception::class.java) { e, _ ->
                logger.error(e) { "Internal server error" }
            }
            // On 404: return message
            error(404) { ctx -> ctx.json("not found") }
        }

    init {
        // Set up URL endpoints for the rest app
        app.routes {
            get("/") {
                it.result("Welcome to Ichnaea")
            }
            path("rest") {
                // Route to check whether the app is running
                // URL: /rest/health
                get("health") {
                    it.json("ok")
                }

                // V1
                path("v1") {
                    path("shows") {
                        // URL: /rest/v1/shows
                        get {
                            it.json(showService.fetchAll())
                        }
                        // URL: /rest/v1/shows/{:id}
                        get(":id") {
                            it.json(showService.fetch(it.pathParam("id").toInt()))
                        }
                    }
                    path("user") {
                        // URL: /rest/v1/user/{:id}/shows
                        get(":id/shows") {
                            it.json(showService.fetchShowUser(it.pathParam("id").toInt()))
                        }

                        post(":id/shows") {
                            showService.addUserRating(it.body<UserRating>())
                        }
                    }
                }
            }
        }
    }
}