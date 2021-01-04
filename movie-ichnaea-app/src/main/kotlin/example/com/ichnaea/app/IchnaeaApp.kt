@file:JvmName("IchnaeaApp")

package example.com.ichnaea.app

import example.com.ichnaea.core.services.MovieService
import example.com.ichnaea.rest.IchnaeaRest
import example.com.ichnaea.data.IchnaeaDal
import example.com.ichnaea.data.MovieTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection


fun main() {

    val tables = arrayOf(MovieTable)

    // Connect to the database and create the needed tables. Drop any existing data.
    val db = Database
            .connect(url = "jdbc:mysql://localhost:3306/ichnaea",
                    driver = "com.mysql.jdbc.Driver",
                    user = "root",
                    password = "root")
            .also {
                TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
                transaction(it) {
                    addLogger(StdOutSqlLogger)
                        // Drop all existing tables to ensure a clean slate on each run
                        //SchemaUtils.drop(*tables)
                    // Create all tables
                    SchemaUtils.create(*tables)
                }
            }

    // Set up data access layer.
    val dal = IchnaeaDal(db = db)

    // Create core service
    val movieService = MovieService(dal = dal)

    // Create REST web service
    IchnaeaRest(movieService = movieService).run()
}