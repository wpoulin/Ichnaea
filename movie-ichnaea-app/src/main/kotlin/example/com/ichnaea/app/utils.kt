package example.com.ichnaea.app

import example.com.ichnaea.data.IchnaeaDal
import org.joda.time.DateTime

fun setupInitialData(dal: IchnaeaDal) {
    val war = dal.createGenre("War")
    val biography = dal.createGenre("Biography")
    val action = dal.createGenre("Action")
    val thriller = dal.createGenre("Thriller")

    val movieType = dal.createType("Movie")
    val showType = dal.createType("TV Show")

    val user1 = dal.createUser("user 1 - firstname", "user 1 - lastname")
    val user2 = dal.createUser("user 2 - firstname", "user 2 - lastname")

    if (movieType != null && showType != null) {
        val hacksawRidge = dal.createShow(
                "Hacksaw Ridge",
                "World War II American Army Medic Desmond T. Doss, who served during the Battle of Okinawa, refuses to kill people, and becomes the first man in American history to receive the Medal of Honor without firing a shot.",
                2016,
                139,
                showType = movieType
        )

        val bourneSupremacy = dal.createShow(
                "The Bourne Supremacy",
                "When Jason Bourne is framed for a CIA operation gone awry, he is forced to resume his former life as a trained assassin to survive.",
                2004,
                108,
                showType = movieType
        )

        if (war != null && biography != null && action != null && thriller != null) {
            if (hacksawRidge != null && bourneSupremacy != null) {
                dal.addGenre(hacksawRidge, war)
                dal.addGenre(hacksawRidge, biography)
                dal.addGenre(bourneSupremacy, action)
                dal.addGenre(bourneSupremacy, thriller)

                if (user1 != null && user2 != null) {
                    val datetimeNow = DateTime()
                    dal.addShow(user1, hacksawRidge, 3.7, datetimeNow)
                    dal.addShow(user1, bourneSupremacy, 4.2, datetimeNow)
                    dal.addShow(user2, hacksawRidge, 3.4, datetimeNow)
                }
            }
        }
    }
}