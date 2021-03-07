@file:JvmName("IchnaeaApp")

package example.com.ichnaea.app

import example.com.ichnaea.core.services.ShowService
import example.com.ichnaea.core.services.UserService
import example.com.ichnaea.data.*
import example.com.ichnaea.rest.IchnaeaRest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection


fun main() {

    val tables = arrayOf(ShowTable, GenreTable, ShowGenreTable, TypeTable, UserTable, ShowUserTable)

    // Connect to the database and create the needed tables. Drop any existing data.
    val db = Database
            .connect(url = "jdbc:mysql://localhost:3306/ichnaea",
                    driver = "com.mysql.cj.jdbc.Driver",
                    user = "root",
                    password = "root")
            .also {
                TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
                transaction(it) {
                    addLogger(StdOutSqlLogger)
                    // Drop all existing tables to ensure a clean slate on each run
                    SchemaUtils.drop(*tables)
                    // Create all tables
                    SchemaUtils.create(*tables)
                }
            }

    // Set up data access layer.
    val dal = IchnaeaDal(db = db)

    // Insert example data in the database.
    setupInitialData(dal = dal)

    // Create core service
    val showService = ShowService(dal = dal)
    val userService = UserService(dal = dal)

    // Create REST web service
    IchnaeaRest(showService = showService, userService = userService).run()
}