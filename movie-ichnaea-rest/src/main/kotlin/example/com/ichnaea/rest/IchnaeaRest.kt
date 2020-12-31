package example.com.ichnaea.rest

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class IchnaeaRest() : Runnable {

    override fun run() {
        app.start(3000)
    }

    // Set up Javalin rest app
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
                    path("movies") {
                        // URL: /rest/v1/movies
                        get {
                            it.json("v1/movies")
                        }

                        // URL: /rest/v1/movies/{:id}
                        get(":id") {
                            it.json("v1/movies/:id")
                        }
                    }
                }
            }
        }
    }
}